package com.boot;

import javax.annotation.Resource;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;
import com.boot.model.Shipwreck;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Resource 
	private AmazonDynamoDB amazonDynamoDB;

	@Resource
	private DynamoDBMapper dynamoDBMapper;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		if (! doesTableExist(amazonDynamoDB, "Shipwreck")) {
			CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Shipwreck.class);
			// Table provision throughput is still required since it cannot be specified in your POJO
			req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
			// Fire off the CreateTableRequest using the low-level client
			amazonDynamoDB.createTable(req);
			System.out.println("Table created");
		}
	}

	public static boolean doesTableExist(AmazonDynamoDB dynamo, String tableName) {
	    try {
	        TableDescription table = dynamo.describeTable(new DescribeTableRequest(tableName))
	                .getTable();
	        return TableStatus.ACTIVE.toString().equals(table.getTableStatus());
	    } catch (ResourceNotFoundException rnfe) {
	        return false;
	    }
	}
}
