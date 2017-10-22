package com.boot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.boot.model.Shipwreck;

@RestController
@RequestMapping("api/v1")
public class ShipController {
	
	@Resource 
	private AmazonDynamoDB amazonDynamoDB;
	
	@Resource
	private DynamoDBMapper dynamoDBMapper;
	
	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> getList() {
		return dynamoDBMapper.scan(Shipwreck.class, new DynamoDBScanExpression());
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public void create(@RequestBody Shipwreck shipwreck) {
		dynamoDBMapper.save(shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable long id) {
		return dynamoDBMapper.load(Shipwreck.class, id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck oldShipwreck = dynamoDBMapper.load(Shipwreck.class, id);
		BeanUtils.copyProperties(shipwreck, oldShipwreck);
		dynamoDBMapper.save(shipwreck);
	}

	// can also return void/object
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		Shipwreck shipwreck = dynamoDBMapper.load(Shipwreck.class, id);
		dynamoDBMapper.delete(shipwreck);
	}
}
