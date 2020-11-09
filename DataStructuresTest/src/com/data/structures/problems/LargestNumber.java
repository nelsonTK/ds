package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.data.structures.problems.ds.LeetCodeExercise;

public class LargestNumber extends LeetCodeExercise{

	static LargestNumber l = new LargestNumber();
	public static void main(String[] args) {

		int [] nums = {4, 40, 7, 80, 8000, 8, 0, 4, 2, 6,5,33};
		nums = stringToArray("[111311, 1113]");
		nums = stringToArray("[3, 34]");
		System.out.println(l.largestNumber(nums));
	}
	
	// a lot of mistakes
	//wrong while comparision, I had || instead of &&, this caused division by zero to happen
	// a lot of confusion on reversing the order
	// fail for the case where one element is zero and the other is not, I only add one case
	// fail for zero
	public String largestNumber(int[] nums) {
		List<Integer> nList = new ArrayList<>();

		for (int i : nums)
		{
			nList.add(i);
		}

		Collections.sort(nList, (b, a) -> {

			int frontA = 0;
			int frontB = 0;
			int sizeA = (int) Math.pow(10, (int) Math.log10(a)); 
			int sizeB = (int) Math.pow(10, (int) Math.log10(b));
			
			if (a == 0 || b == 0)
				return Integer.compare(a, b);
			
			int initialFrontA = frontA = a/sizeA;
			int initialFrontB = frontA = a/sizeA;
			
			
			//System.out.println(a + " " + b);
			while (a > 0 && b > 0)
			{
				//System.out.println(a + " " + b);
				sizeA = (int) Math.pow(10, (int) Math.log10(a)); 
				sizeB = (int) Math.pow(10, (int) Math.log10(b));

				//System.out.println("size" + sizeA + " " + sizeB);
				//get front
				frontA = a/sizeA;
				frontB = b/sizeB;


				//System.out.println("front" + frontA + " " + frontB);
				//remove front
				a = a - (frontA * sizeA);
				b = b - (frontB * sizeB);

				//System.out.println("left" + a + " " + b);
				if (frontA != frontB)
					return Integer.compare(frontA, frontB);
			}

			//if they both end in zero we must resolve 3 vs 300000 situations, we prefer putting 3 because we can create a bigger number
			if (a == 0 && b == 0)
			{
				if (sizeA > sizeB)
					return -1;//a bigger
				else if(sizeA < sizeB)
					return 1;
				else
					return 0;
			} 
			else if (a != 0 && b == 0)
			{
				if (sizeA == sizeB)
					return 1;//a bigger
				else if(sizeA > sizeB)
					return -1;
				else
					return 1;
			}
			else //if (a == 0 && b != 0)
			{
				if (sizeA == sizeB)
					return -1;//a bigger
				else if(sizeA < sizeB)
					return 1;
				else
					return -1;
			}

		});

		StringBuilder sb = new StringBuilder("");

		for (int i : nList)
		{
			sb.append(i);
		}

		/*
		        List<String> list = new ArrayList<>();

		        for (int i = 0; i < nums.length;i++)
		        {
		            list.add(nums[i] + "");
		        }

		        Collections.sort(list, (a,b) -> b.compareTo(a));

		        StringBuilder sb = new StringBuilder("");

		        for(String s : list)
		        {
		            sb.append(s);
		        }*/

		return sb.toString();
	}
}
