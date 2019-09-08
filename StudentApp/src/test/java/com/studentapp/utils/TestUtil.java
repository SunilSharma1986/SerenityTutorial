package com.studentapp.utils;

import java.util.Random;

public class TestUtil {

	
	public static String getRandomValue(){
		Random random=new Random();
		int radomInt=random.nextInt(10000);
		
		return Integer.toString(radomInt);
	}
}
