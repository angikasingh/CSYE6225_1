package com.csye6225.spring2020.courseservice.datamodel;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {

	static AmazonDynamoDB dynamoDb;
	private static final String ACCESS_KEY = "AKIAT7MC3NKCCEIAFDRW";
	private static final String SECRET_KEY = "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";
	

	public static AmazonDynamoDB getClient() {
		if (dynamoDb == null) {
			System.setProperty("aws.accessKeyId", ACCESS_KEY);
			System.setProperty("aws.secretKey", SECRET_KEY);
			AWSCredentialsProvider credentialsProvider = new SystemPropertiesCredentialsProvider();
			credentialsProvider.getCredentials();

			dynamoDb = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion("us-west-2").build();
		}
		return dynamoDb;
	}
}
