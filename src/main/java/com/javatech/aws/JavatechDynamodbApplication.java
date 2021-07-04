package com.javatech.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.javatech.aws.entity.Person;
import com.javatech.aws.repository.PersonRepository;



@SpringBootApplication
@RestController
public class JavatechDynamodbApplication {
	
	@Autowired
    private PersonRepository repository;
    @PostMapping("/savePerson")
    public Person savePerson(@RequestBody Person person) {
        return repository.addPerson(person);
    }


    @GetMapping("/getPerson/{personId}")
    public Person findPerson(@PathVariable String personId) {
        return repository.findPersonByPersonId(personId);
    }

    @GetMapping("/getPerson")
    public PaginatedScanList<Person> findPersonAll() {
        return repository.findPersonAll();
    }
 
    @DeleteMapping("/deletePerson/{personId}")
    public String deletePerson(@PathVariable("personId") @RequestBody String personId) {
        return repository.deletePerson(personId);
    }

    @PutMapping("/editPerson")
    public String updatePerson(@RequestBody Person person) {
        return repository.editPerson(person);
    }

	public static void main(String[] args) {
		SpringApplication.run(JavatechDynamodbApplication.class, args);
	}

	  @RequestMapping("/")
	    @ResponseBody
	    public String hello() {
	        return "Api crud desenvolvido para processo seletivo Itaú !";
	    }
	 
}
