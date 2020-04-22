package com.csye6225.spring2020.courseservice.datamodel;

import java.lang.reflect.Field;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Registrar")
public class Registrar {
	private String offeringId;
	private String offeringType;
	private String department;
	private double unitPrice;
	
	public Registrar() {
		
	}
	
	public Registrar(String offeringId, String offeringType, String department, double unitPrice) {
		this.offeringId = offeringId;
		this.offeringType = offeringType;
		this.department = department;
		this.unitPrice = unitPrice;
	}
	
	@DynamoDBHashKey(attributeName="offeringId")
	public String getOfferingId() {
		return offeringId;
	}

	public void setOfferingId(String offeringId) {
		this.offeringId = offeringId;
	}

	@DynamoDBAttribute(attributeName="offeringType")
	public String getOfferingType() {
		return offeringType;
	}
	
	public void setOfferingType(String offeringType) {
		this.offeringType = offeringType;
	}

	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@DynamoDBAttribute(attributeName="unitPrice")
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void updateInfo(Registrar c) throws IllegalArgumentException, IllegalAccessException {
		for (Field f : getClass().getDeclaredFields()) {
			if (f.get(c) != null) {
				f.set(this, f.get(c));
			}
		}
	}

}
