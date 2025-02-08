package com.poc.loan_management.handler;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.loan_management.entity.LoanApplication;
import com.poc.loan_management.service.LoanService;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.Map;

@Component
public class LoanHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final LoanService loanService;
    private final ObjectMapper objectMapper;

    public LoanHandler(LoanService loanService) {
        this.loanService = loanService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String method= request.getHttpMethod();
        String path=request.getPath();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try{
            switch (method){
                case "POST":
                    LoanApplication loan = objectMapper.readValue(request.getBody(), LoanApplication.class);
                    LoanApplication savedLoan = loanService.saveLoan(loan);
                String responseBody = objectMapper.writeValueAsString(savedLoan);
                    response.setStatusCode(200);
                    response.setBody(responseBody);
                    break;
                case "GET":
                    Map<String, String> pathParameters = request.getPathParameters();
                    String loanId = pathParameters != null ? pathParameters.get("loanId") : null;
                  //  String loanId = path.substring(path.lastIndexOf("/")+1);
                    if(loanId!=null){
                        LoanApplication retrievedLoan = loanService.getLoan(loanId);
                        if(retrievedLoan!=null){
                            response.setStatusCode(200);
                            response.setBody(objectMapper.writeValueAsString(retrievedLoan));
                        }
                        else{
                            response.setStatusCode(404);
                            response.setBody("Loan not found");

                    }
                    }
                    else{
                        response.setStatusCode(400);
                        response.setBody("Loan ID not provided");
                    }
                    break;
                default:
                    response.setStatusCode(405);
                    response.setBody("Method Not Allowed");
                    break;
            }
        }   catch (Exception e) {
            response.setStatusCode(500);
            response.setBody("Internal Server Error: " + e.getMessage());
            e.printStackTrace();
        }
        return response;

    }
}
