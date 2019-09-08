package com.studentapp.junit.studentsinfo;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.steps.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtil;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase{
	
	
	static String firstName="SMOKEUSER"+TestUtil.getRandomValue();
	static String lastName="SMOKEUSER"+TestUtil.getRandomValue();
	static String programme="ComputerScience";
	static String email=TestUtil.getRandomValue()+"xyz@gmail.com";
	static int studentId;
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This test method will create a new Student")
	@Test
	public void test001(){
		ArrayList<String> courses=new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
		
	
	}
	
	
	@Title("Verify if the student is added to the Application")
	@Test
	public void test002(){		
		HashMap<String, Object> values=steps.getStudentInfoByFirstName(firstName);
		
		assertThat(values,hasValue(firstName));
		
		studentId=(int) values.get("id");
		
	}
	
	
	
	
	@Title("Update the user information and verify updated informatoin")
	@Test
	public void test003(){
		String p1="findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		ArrayList<String> courses=new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		firstName=firstName+"_Updated";
		
		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
		
		HashMap<String, Object> value=steps.getStudentInfoByFirstName(firstName);
		
				System.out.println("The value is: " +value);
				
				assertThat(value, hasValue(firstName));
	}
	
	
	@Title("Delete the student and verify if the student is deleted")
	@Test
	public void test004(){
	
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
		

	}
}
