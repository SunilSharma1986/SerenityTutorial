package com.studentapp.junit;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:8080/student/";
	}
	
	
	@Test
	public void getAllStudent(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	
	@Test
	public void getAllStudentFailScenario(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void getAllStudentPending(){
		
	}
	
	@Ignore
	@Test
	public void getAllStudentIgnore(){
		
	}
	
	
	@Test
	public void getAllStudentERROR(){
		System.out.println("This is an error " +(5/0));
		
	}

	
	@Manual
	public void getAllStudentManualTest(){
		
	}

}
