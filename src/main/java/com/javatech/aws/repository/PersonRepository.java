package com.javatech.aws.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.javatech.aws.entity.Person;


@Repository
public class PersonRepository {

    @Autowired
    private DynamoDBMapper mapper;
    public Person addPerson(Person person) {
        mapper.save(person);
        return person;
    }

    public Person findPersonByPersonId(String personId) {
        return mapper.load(Person.class, personId);
    }
    
    public PaginatedScanList<Person> findPersonAll() {
        return mapper.scan(Person.class,new DynamoDBScanExpression());
    }
    
    public String deletePerson(String personId) {
    	Person person = mapper.load(Person.class, personId);
        mapper.delete(person);
        return "person removed !!";
    }

    public String editPerson(Person person) {
        mapper.save(person, buildExpression(person));
        return "record updated ...";
    }


	
    private DynamoDBSaveExpression buildExpression(Person person) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }


}
