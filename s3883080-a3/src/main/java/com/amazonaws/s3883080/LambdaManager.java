package com.amazonaws.s3883080;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.Map;
import java.util.Properties;

public class LambdaManager {
    private DynamoDBMapper dynamoDBMapper;
    private String bodyForJsonItem;
    private Table table;

    public APIGatewayProxyResponseEvent getReceipts(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        startDynamoDB();

        String seller = apiGatewayProxyRequestEvent.getPathParameters().get("seller");
        Receipt receipt = dynamoDBMapper.load(Receipt.class, seller);
        if (receipt!=null) {
            bodyForJsonItem = bodyForJsonItem + JsonUtility.convertObjToString(receipt, context);
            context.getLogger().log("Getting receipt " + bodyForJsonItem);
            return generateApiResponse(bodyForJsonItem, 200, JsonUtility.generateHeaders());
        } else {
            bodyForJsonItem = "There are no receipts stored in our database";
            return generateApiResponse(bodyForJsonItem, 400, JsonUtility.generateHeaders());
        }
    }

    private void startDynamoDB() {
        AmazonCredentials amazonCredentials = new AmazonCredentials();
        Properties properties;
        properties = System.getProperties();
        properties.setProperty("aws.accessKeyId", amazonCredentials.getAccessKey());
        properties.setProperty("aws.secretKey", amazonCredentials.getSecretAccessKey());
        properties.setProperty("aws.sessionToken", amazonCredentials.getSessionToken());
        SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider = new SystemPropertiesCredentialsProvider();
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(systemPropertiesCredentialsProvider)
                .withRegion(Regions.US_EAST_1);
        DynamoDB dynamoDB = new DynamoDB(client);

        table = dynamoDB.getTable("receipts");
    }


    private APIGatewayProxyResponseEvent generateApiResponse(String body, int statusCode, Map<String, String> headers){
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        apiGatewayProxyResponseEvent.setBody(body);
        apiGatewayProxyResponseEvent.setHeaders(headers);
        apiGatewayProxyResponseEvent.setStatusCode(statusCode);
        return apiGatewayProxyResponseEvent;
    }
}
