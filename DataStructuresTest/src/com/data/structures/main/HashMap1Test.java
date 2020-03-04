package com.data.structures.main;

import java.util.HashMap;

import com.data.structures.ds.HashMap1;

public class HashMap1Test {

	public static void main(String[] args) {
		System.out.println("----------Test 1------------");
		HashMap1<String,Integer> hm = new HashMap1<String,Integer>();
		hm.put("Pedro", 2);
		hm.put("Nelson", 1);
		hm.put("Rui", 3);
		hm.put("Luis", 4);
		hm.put("Andrian", 5);
		hm.put("Margarida", 6);
		hm.put("Margarid", 7);
		hm.put("Margari", 8);

		System.out.println(hm.get("Nelson"));
		System.out.println(hm.get("Pedro"));
		System.out.println(hm.get("Rui"));
		System.out.println(hm.get("Luis"));
		System.out.println(hm.get("Andrian"));
		System.out.println(hm.get("Margarida"));
		System.out.println(hm.get("Margarid"));
		System.out.println(hm.get("Margari"));
		
		System.out.println("----------Test 2------------");
		HashMap1<String,Integer> hm2 = new HashMap1<String,Integer>();
		hm2.put2("Pedro", 2);
		hm2.put2("Nelson", 1);
		hm2.put2("Rui", 3);
		hm2.put2("Luis", 4);
		hm2.put2("Andrian", 5);
		hm2.put2("Margarida", 6);
		hm2.put2("Margarid", 7);
		hm2.put2("Margari", 8);
		hm2.put2(null, 9);

		System.out.println("GET");
		System.out.println(hm2.get2("Nelson"));
		System.out.println(hm2.get2("Pedro"));
		System.out.println(hm2.get2("Rui"));
		System.out.println(hm2.get2("Luis"));
		System.out.println(hm2.get2("Andrian"));
		System.out.println(hm2.get2("Margarida"));
		System.out.println(hm2.get2("Margarid"));
		System.out.println(hm2.get2("Margari"));
		System.out.println(hm2.get2(null));

		hm2.put2("Margari", 80);
		System.out.println(hm2.get2("Margari"));
		
	}

}
