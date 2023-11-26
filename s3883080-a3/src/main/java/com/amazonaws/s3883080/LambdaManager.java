package com.amazonaws.s3883080;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.Map;

public class LambdaManager {
    private DynamoDBMapper dynamoDBMapper;
    private String jsonBody;
    private Table table;

    public APIGatewayProxyResponseEvent getReceipts(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        initDynamoDB();

        String seller = apiGatewayProxyRequestEvent.getPathParameters().get("seller");
        Receipt receipt = dynamoDBMapper.load(Receipt.class, seller);
        if (receipt!=null) {
            jsonBody = jsonBody + JsonUtility.convertObjToString(receipt, context);
            context.getLogger().log("Getting receipt " + jsonBody);
            return createApiResponse(jsonBody, 200, JsonUtility.createHeaders());
        } else {
            jsonBody = "There are no receipts stored in our database";
            return createApiResponse(jsonBody, 400, JsonUtility.createHeaders());
        }
    }

    private void initDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        table = dynamoDB.getTable("receipts");
    }


    private APIGatewayProxyResponseEvent createApiResponse(String body, int statusCode, Map<String, String> headers){
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        apiGatewayProxyResponseEvent.setBody(body);
        apiGatewayProxyResponseEvent.setHeaders(headers);
        apiGatewayProxyResponseEvent.setStatusCode(statusCode);
        return apiGatewayProxyResponseEvent;
    }
}
