package com.poc.loan_management.service;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import org.springframework.stereotype.Service;

@Service
public class SnsService {

    private final SnsClient snsClient;

    public SnsService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void publishMessage(String topicArn, String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();
        snsClient.publish(request);
    }
}
