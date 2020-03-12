package com.data.structures.utils;

import java.util.ArrayList;
import java.util.Random;

public class StringHelper {


	public static ArrayList<String> generateData(int itemsNumber) {
		ArrayList<String> list = new ArrayList<String>();
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		String generatedString = "";
		for (int j = 0; j < itemsNumber; j++) {
			generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
					.limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			
			list.add(generatedString);
		}
		
		return list;
	}
}
