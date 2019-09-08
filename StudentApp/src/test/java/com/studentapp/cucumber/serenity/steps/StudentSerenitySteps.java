package com.studentapp.cucumber.serenity.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("Getting the student information with firstName : {0}, lastName : {1}, email : {2}, programme : {3}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
			List<String> courses) {
		StudentClass stu=new StudentClass();
		stu.setFirstName(firstName);
		stu.setLastName(lastName);
		stu.setEmail(email);
		stu.setCourses(courses);
		stu.setProgramme(programme);

		return SerenityRest.rest().given().contentType(ContentType.JSON).when().body(stu).post().then();
	}
	
	@Step("Getting the student information with firstName : {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName){
		String p1="findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		return SerenityRest.rest().given()
				.when()
				.get("/list")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path(p1+firstName+p2);
	}
	
	@Step("Updating the student information with studentId: {0},firstName : {1}, lastName : {2}, email : {3}, programme : {4}, courses : {5}")
	public ValidatableResponse updateStudent(int studentId,String firstName, String lastName, String email, String programme,
			List<String> courses) {

		
		StudentClass stu=new StudentClass();
		
		stu.setFirstName(firstName);
		stu.setLastName(lastName);
		stu.setEmail(email);
		stu.setCourses(courses);
		stu.setProgramme(programme);
		
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(stu)
		.put("/"+studentId)
		.then()
		.log()
		.all();
		
	}
	
	@Step("Delete the student information with ID: {0}")
	public void deleteStudent(int studentId){
		SerenityRest
		.rest()
		.given()
		.when()
		.delete("/" +studentId);
		
		
	}
	
	@Step("Getting information of the student by studentId : {0}")
	public ValidatableResponse getStudentById(int studentId){
		return SerenityRest
				.rest()
				.given()
				.when()
				.get("/" +studentId).then();
	}
	
	
}
