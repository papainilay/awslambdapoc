package com.poc.loan_management.service;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Service;

@Service
public class SqsService {

    private final SqsClient sqsClient;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String queueUrl, String message) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}
