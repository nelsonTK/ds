package com.data.structures.main;

import java.util.Random;

import com.data.structures.ds.HashMap1;

public class HashMap1Test {

	public static void main(String[] args) {
		System.out.println("----------Test 1------------");
		//		HashMap1<String,Integer> hm = new HashMap1<String,Integer>();
		//		hm.put("Pedro", 2);
		//		hm.put("Nelson", 1);
		//		hm.put("Rui", 3);
		//		hm.put("Luis", 4);
		//		hm.put("Andrian", 5);
		//		hm.put("Margarida", 6);
		//		hm.put("Margarid", 7);
		//		hm.put("Margari", 8);
		//
		//		System.out.println(hm.get("Nelson"));
		//		System.out.println(hm.get("Pedro"));
		//		System.out.println(hm.get("Rui"));
		//		System.out.println(hm.get("Luis"));
		//		System.out.println(hm.get("Andrian"));
		//		System.out.println(hm.get("Margarida"));
		//		System.out.println(hm.get("Margarid"));
		//		System.out.println(hm.get("Margari"));

		System.out.println("----------Test 2------------");
		HashMap1<String,Integer> hm2 = new HashMap1<String,Integer>();
		System.out.println("Size: " + hm2.getSize());
		hm2.put2("Pedro", 2);
		hm2.put2("Nelson", 1);
		hm2.put2("Rui", 3);
		hm2.put2("Luis", 4);
		System.out.println("Size: " + hm2.getSize());
		hm2.put2("Andrian", 5);
		hm2.put2("Margarida", 6);
		hm2.put2("Margarid", 7);
		hm2.put2("Margari", 8);
		hm2.put2(null, 9);
		hm2.put2("Tests continue", 10);
		hm2.put2("Ips Lorum11111111111111", 11);
		hm2.put2("I", 11);
		hm2.put2("I5", 12);
		hm2.put2("I6", 13);
		hm2.put2("I8", 14);
		hm2.put2("I9", 15);
		hm2.put2("I10", 16);
		System.out.println("Size: " + hm2.getSize());

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = "";
		for (int j = 0; j < 60; j++) {
			generatedString = random.ints(leftLimit, rightLimit + 1)
					.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
					.limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			
			hm2.put2(generatedString, random.nextInt() * 10);
		}

		
		System.out.println("GET");
		System.out.println(hm2.get("Nelson"));
		System.out.println(hm2.get("Pedro"));
		System.out.println(hm2.get("Rui"));
		System.out.println(hm2.get("Luis"));
		System.out.println(hm2.get("Andrian"));
		System.out.println(hm2.get("Margarida"));
		System.out.println(hm2.get("Margarid"));
		System.out.println(hm2.get("Margari"));
		System.out.println(hm2.get(null));
		System.out.println(hm2.get("Tests continue"));
		System.out.println(hm2.get("Ips Lorum11111111111111"));
		System.out.println(hm2.get("Nelson"));
		System.out.println(hm2.get("Nelson"));
		System.out.println(hm2.get("Nelson"));


		hm2.put2("Margari", 80);
		System.out.println(hm2.get("Margari"));
		System.out.println("Size: " + hm2.getSize());

	}

}
