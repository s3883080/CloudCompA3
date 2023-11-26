package com.amazonaws.s3883080;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class MyLambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        LambdaManager lambdaManager = new LambdaManager();
        switch (apiGatewayProxyRequestEvent.getHttpMethod()) {
            case "GET":
                return lambdaManager.getReceipts(apiGatewayProxyRequestEvent, context);
            default:
                throw new Error("Receipts not found");
        }
    }
}