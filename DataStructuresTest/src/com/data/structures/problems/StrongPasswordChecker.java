package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/strong-password-checker/
 * HARD
 * @author Nelson Costa
 *
 */
public class StrongPasswordChecker {
	/**
	 * 
		"..."
		"...."
		"....."
		""
		"aaaaaa"
		"aaaaaaaaaaaaaaaaaaaaa"
		"fhtayyaaaaaaaaaaaasfghhhg"
		"fhtayyaaaaaaaaaaaAsfghhhg"
		"fhtayyaaaaaaaaaaaAsfghhhgjjj"
		"fhtayyaaaaaaaaaaaasfghhhgjjj"
		"fhtayyaaaaaaaaaaaAsfghhhgjj"
		"fhtayyaaaaaaaaaaaAsfghhhgjjjsadakkkkklos"
		"...aA1"
		"1234567890123456Baaaaa"
		"..................!!!"
	 */
	static StrongPasswordChecker p = new StrongPasswordChecker();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ABABABABABABABABABAB1";
		s = "aaaaaaaaaaaaaaaaaaaaa";
		//		s = "fhtayyaaaaaaaaaaaAsfghhhg";
		//		s = "fhtayyaaaaaaaaaaaasfghhhg";
		s = "...";
		//		s = "";
		s = "1234567890123456Baaaaa";
//		s = "..................!!!";
//		s = "1Abababcaaaabababababa";
		System.out.println(p.strongPasswordChecker(s));




	}

	 /*********************************
	 * SOLUTION 1
	 ********************************/
	/** 
	 * 
	 * [WRONG]
	 * 
	 * 
	 * @fail   
	 * 1) lowercase logic inverted 2) missed a detail in inicialization of finalState 3) misread the description, skipped the digit constraint 4) missing joining the extra characters to remove 5) This solution is just wrong
	 * 
	 * 
	 * 
	 * 
	 **/
	public int strongPasswordChecker0(String s) {
		//6 characters min 20 max
		//1 uppercase, 1 lowercase
		//cannot have 3 repeated characters


		//cases
		/*   smaller or bigger and is wrong already
            smaller or bigger and is okay
            right size and is okay
            right size and is not okay
            empty
            string with only characters
		 */

		int repeated = 2;
		int caseCheck = 0;
		int size = 1;
		int digit = 3;

		int counter = 1;
		boolean digitMissing = true;
		boolean upperCase = true; //lowercase missing
		boolean lowerCase = true; //uppercase missing
		boolean [] repEnd = new boolean[s.length()]; //tree repeated characters end
		int [] finalState = new int[4];        
		//negative size means whe need to remove n characters and can't use them to for instance add new case char
		finalState[size] = s.length() > 20 ? 20 - s.length() : s.length() < 6? 6 - s.length(): 0; 
		finalState[caseCheck] = 2;
		finalState[digit] = 1;
		for (int i = 0; i < s.length(); i++)
		{
			//Check Repeated characters
			if (i - 1 >= 0 && s.charAt(i) == s.charAt(i - 1) && !repEnd[i - 1])
			{
				counter++;

				if (counter == 3)
				{
					finalState[repeated]++;
					repEnd[i] = true;
				}
			}
			else
			{
				counter = 1;  
			}

			if (digitMissing && Character.isDigit(s.charAt(i)))
			{
				digitMissing = false;
				finalState[digit]--;
			}

			//Check Case
			if (lowerCase || upperCase)
			{
				if (Character.isLowerCase(s.charAt(i)) && lowerCase)
				{ 
					lowerCase = false;
					finalState[caseCheck]--;
				}
				else if (Character.isUpperCase(s.charAt(i)) && upperCase)    
				{
					upperCase = false;
					finalState[caseCheck]--;
				}
			}
		}

		/*
        "aaAA11"
        ""
        "1111111111"
        "ABABABABABABABABABAB1"
		"1010101010aaaB10101010"
		"......"
		"...aA1"
		"fhtayyaaaaaaaaaaaAsfghhhgjjj"
		"fhtayyaaaaaaaaaaaasfghhhgjjj"
		"fhtayyaaaaaaaaaaaAsfghhhgjj"
		"fhtayyaaaaaaaaaaaAsfghhhgjjjsadakkkkklos"

        System.out.println(finalState[caseCheck]);
        System.out.println(finalState[size]);  
        System.out.println(finalState[repeated]); 
        System.out.println(finalState[digit]);  
        System.out.println("_____"); 
		 */
		if (finalState[caseCheck] > 0 || finalState[digit] > 0 )
		{
			if (finalState[size] >= 0)
			{
				int sizeAndRepeated = finalState[size] + finalState[repeated];

				if (sizeAndRepeated >= finalState[caseCheck] + finalState[digit])
					return sizeAndRepeated;
				else
					return finalState[caseCheck] + finalState[digit] ;
			}
			else
			{
				if (finalState[repeated] >= finalState[caseCheck] + finalState[digit])
				{
					return finalState[repeated] + (int) Math.abs(finalState[size]);
				}
				else
				{
					return (int) Math.abs(finalState[size]) + finalState[caseCheck] + finalState[digit];
				}
			}
		}


		return finalState[repeated] + (int) Math.abs(finalState[size]);
	}

	int del = 0;
	int upd = 1;
	int ins = 2;
	public int strongPasswordChecker1(String s) {

		int [] size = new int [3];
		int [] reps = new int [3];
		int [] nums = new int [3];
		int [] uplo = new int [3];
		nums[upd] = 1;
		uplo[upd] = 2;

		int passSize = s.length() < 6 ? 6 - s.length() : s.length() > 20 ? 20 - s.length() : 0;

		//workSize var
		if (passSize > 0)
			size[ins] = passSize;
		else if (passSize<0)
			size[del] = Math.abs(passSize);

		System.out.println("ins: " + size[ins] + " | del: " + size[del]);
		//work repeated characters
		int count = 0;
		int totalRepeated=0;
		char c ;
		boolean [] repeatedControl = new boolean[s.length()]; 
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);

			if (i - 1 >= 0 && c == s.charAt(i - 1) && !repeatedControl[i - 1])
			{
				count++;

				if (count == 3)
				{
					totalRepeated++;
					repeatedControl[i] = true;
				}

			}
			else
			{
				count = 1;
			}
		}

		int validRepeatedElements = 20/3 * 3;
		reps[del] = (totalRepeated % validRepeatedElements) / 3;
		reps[upd] = totalRepeated - reps[del];

		System.out.println("totalRepeated: " + totalRepeated);

		System.out.println("after reps");
		System.out.println("size ins " + size[ins]);
		System.out.println("size del " + size[del]);
		System.out.println("reps del " + reps[del]);
		System.out.println("reps upd " + reps[upd]);        
		System.out.println("nums upd " + nums[upd]);   
		System.out.println("uplo upd " + uplo[upd]);
		//work with numbers
		boolean missesNumbers = true;

		for (int i = 0; i < s.length(); i++)
		{
			if (Character.isDigit(s.charAt(i)))
			{
				missesNumbers = false;
				nums[upd]--;
				break;
			}
		}
		int discount = 0;
		if (nums[upd] != 0 && nums[upd] <= size[ins] + reps[upd])
		{
			discount--;
			//nums[upd] = 0;
		}
		else  if (nums[upd] != 0)
		{
			discount -= size[ins]+reps[upd];
		}


		System.out.println("after nums discount: " + discount);
		System.out.println("size ins " + size[ins]);
		System.out.println("size del " + size[del]);
		System.out.println("reps del " + reps[del]);
		System.out.println("reps upd " + reps[upd]);        
		System.out.println("nums upd " + nums[upd]);   
		System.out.println("uplo upd " + uplo[upd]);

		//work with upper and lower case
		boolean lowercaseMissing = true;
		boolean uppercaseMissing = true;
		for (int i = 0; i < s.length() && (lowercaseMissing || uppercaseMissing); i++)
		{
			c = s.charAt(i);

			if (Character.isLowerCase(c) && lowercaseMissing)
			{
				lowercaseMissing = false;
				uplo[upd]--;
			}
			else if (Character.isUpperCase(c) && uppercaseMissing)
			{
				uppercaseMissing = false;
				uplo[upd]--;
			}
		}
		if (uplo[upd] != 0 &&  uplo[upd] <= size[ins] + reps[upd] + discount)
		{
			discount-=uplo[upd];
		}
		else if (uplo[upd] != 0 && (size[ins] + reps[upd] + discount) != 0)
		{
			discount -= uplo[upd] - (size[ins] + reps[upd] + discount);
		}

		System.out.println("after uplo discount: " + discount);
		System.out.println("size ins " + size[ins]);
		System.out.println("size del " + size[del]);
		System.out.println("reps del " + reps[del]);
		System.out.println("reps upd " + reps[upd]);        
		System.out.println("nums upd " + nums[upd]);   
		System.out.println("uplo upd " + uplo[upd]);
		//max of size del and reps del + rest

		return discount + uplo[upd] +  nums[upd] + size[ins] + reps[upd] + Math.max(reps[del], size[del]);
	}





	 /*********************************
	 * SOLUTION 2
	 ********************************/
	/** 
	 * 
	 * [WRONG]
	 * 
	 * @intuition
	 * 		the focus was on the case where the word is too big
	 * 		I came up with the process to solve it which look okay but it had some bad assumptions and I could not recover from that
	 * 		Below you have the fundamental Idea and the subsequent fixes to try to solve this problem.
	 * 		It's Ugly
	 * 
	 * 
	 * 
	 * @fail
	 *    1) NullpointerException at the constructor
	 *    2) The formula to calculate remaining elements to test update is wrong
	 *    3) modifications born in the process was a step that was not needed in the facelift of the algorithm, 
	 *    the work was being done twice
	 *    3.1) The math was not completly right for the subtraction of the invalidTriple count after deleting
	 *    3.2) ceiling was too much, round is enough unless the number of elements changed are below 3 and not equals to 0;
	 *    4) The mathematics was still not okay, it was a little bit more fancier than I expected
	 *    4.1) formula wrong again, it was not modulus but division, the draft was right but code bad
	 *    4.2) another edge case, when elements deleted below or equals to the 3 (triplet) there is no calculation for the remaining
	 *    5) edge case "...", this behavior was not though in the solution desing, so this is why this question has so many dislikes.
	 *    	Insertions are allowed anywhere (https://leetcode.com/problems/strong-password-checker/discuss/544231/Possibly-incorrect-test-case%3A-%22...%22)
	 *    5.1) wrongly added "add" variable to <6 condition, I messed up the concept that I was applying. 
	 *    add variable is already included in total.
	 *    6) failed at a incredible edge case which is when after deleting stuff (>20 clase) in a sequence the remaining can still create a triplet
	 *    and in that case you need to solve it too. for this to happen there could be no letters to insert. thats why i didnt caught it.
	 *    	 "1234567890123456Baaaaa"
	 *    	I applied a fix changing the behavior when no MissingChars is found to start at the start of the sequence instead of the "optimal position"
	 *    7) another edge case that happens when nothin is left after deleting data
	 *    7.1) another case that happens is that i neeed to start the delete of nodes on different sites depending on the length of the delete variable
	 *    8) wrong, condition Hell Loop. When you make a change you fix something and broke something
	 **/
	public int strongPasswordChecker(String s) {
		//between 6 and most 20
		//should have one locase, hicase, one digit
		//cannot have 3 repeated characters

		//delete
		/*   check if we can delete the last char of a repeated series
            1) if can delete triplet, ou update. I think delete  spends less chars than updating. because if you update you still have to delete the N numbers..
            2) delete reps is not optimal, unless triplet size is equals to 3. if it is 4 we delete 1 and from 4 streak we go to 3 streak and still have to update one char. so it is a 1 vs 2 update.

            Delete can be optimal if there are 
            So delete can subtract on reps

            Looks like the optimal delete needs to 
            before deleting random characters

            insert/upd num and delete independentemente da ordem é igual


            Delete
                1) apagar grupos de 3 primeiro
                2) adicionar missing types (desconta dos reps)
                3) tentar apagar o max dos grupos de 3 e update do que sobrar a partir do ultimo atribuido
                    3.1) apos missing type, começar a apagar no index seguinte.
                    3.2) se não houver missing types, começar a apagar a partir do próprio index
                4) depois de fazer esse delete tenho de ver se tenho de fazer novo update, com os elementos que sobrarem

            criação de sequences
            "fhtayyaaaaaaaaaaaAsfghhhg" -> sequence (2) -> (11 size, 3 groups[pode ser uma lista com os indexes], start at index: n)
		 */


		int missingChars = getMissingAlphanumericConstraint(s);
		List<Sequence> seq = getRepeatedSequences(s);
		int invalidTripleCounts = getInvalidTripletCounts(seq);

		int size = s.length();
		int modificationsBornInTheProcess = 0;
		//too short
		if (size < 6)
		{
			//size First
			int add =  6 - size;
			int total = add;

			//Discounts
			//discount repeated
			if (invalidTripleCounts <= 4 && invalidTripleCounts != 0)
			{
				invalidTripleCounts--;
				add--;
				if(missingChars > 0)
					missingChars--;
				//dealing with missing Elements
				//discount missing Characters
				while(add > 0 && missingChars > 0)
				{
					add--;
					missingChars--;
				}
			}else
			{

				while(add > 0 && missingChars > 0)
				{
					add--;
					missingChars--;
				}

			}

			while (missingChars > 0)
			{
				missingChars--;
				total++;
			}

			return total + missingChars + invalidTripleCounts;

			//return Math.max(missingChars, 6 - size + invalidTripleCounts);
		}
		//too big
		else if (size > 20)
		{

			//1) apagar grupos de 3 primeiro
			int delete = size - 20;
			int total = 0;
			for (int i = 0; i < seq.size() && delete > 0; i++)
			{
				Sequence sequence = seq.get(i);
				if(sequence.size == 3)
				{
					delete--;
					total++;
					invalidTripleCounts--;
					//I could remove this items from the list already
				}
			}

			//2) adicionar missing types (desconta dos reps/sequences)

			for (int i = 0; i < seq.size() && (delete > 0|| missingChars > 0); i++)
			{
				Sequence sequence = seq.get(i);
				if(sequence.size != 3)
				{
					if (missingChars != 0) 
					{
						for (int j = 0; j < sequence.tripletEndIndexes.size();j++)
						{

							if (missingChars > 0)
							{
								missingChars--; //discount missing chars to repetitions
								invalidTripleCounts--;
								total++;
							}

							//We will start consuming deletes after
							if (missingChars == 0 && delete > 0)
							{
								//this could be one function
								int tracker = 0;
								int elementsDeleted = 0;
								int tripletIndex = sequence.tripletEndIndexes.get(j);
								for (int k = tripletIndex + 1; k <= sequence.getEnd() && delete != 0; k++)
								{
									delete--;
									total++;
									elementsDeleted++;
									tracker = k;
								}

								invalidTripleCounts -= elementsDeleted < 3 && elementsDeleted != 0 ? 1 : elementsDeleted / 3;

								//remaining of deleted elements that don't complete a triple
								int restOfDeleted = elementsDeleted % 3;


								int remainingElementsFromTripletSequence = sequence.getEnd() - tracker;
								/*************************************************************
									To figure out if I need to reduce the number of invalidTripleCounts 
									We need to check if the deletion of a element contributes to the reduction of invalid counts

									example:

										aaaaaaaaaaaaaaaaaaaaa (21 elements = 3 * 7)

										we need to delete 1.
										the algorithm will delete the index 6. (after updating to a number and a Capital)

										we now need to understand if the removing of that index 6 will affect 
										the total number of invalidTripleCounts.

										to do this we need to check if the number of triplets before the delete was larger than the number of triplets after the delete

										BEFORE DELETE = (modulo of elements Deleted + remaining elements to update) % 3

										AFTER DELETE = (remaining elements to update) % 3

										if before > after we need to decrease the counter


								 ************************************************************/
								int before = (restOfDeleted + remainingElementsFromTripletSequence) /3;
								int after = (remainingElementsFromTripletSequence) /3;
								if (before > after && elementsDeleted > 3)
								{
									invalidTripleCounts --;
								}
								break; //at this time the sequence was completed already
							}
							//if missing consumed
						}
					}
					else
					{
						//Missing is null we want to delete as much elements as possible, starting in a optimal position of the triplets
						//this could be one function
						if (sequence.tripletEndIndexes.size() > 0)
						{
							//condition to determine how to start
							
							if (sequence.getEnd() > sequence.tripletEndIndexes.get(0) + delete)
							{
								int tracker = 0;
								int elementsDeleted = 0;
								int tripletIndex = sequence.tripletEndIndexes.get(0);
								//							for (int k = sequence.start; k <= sequence.getEnd() && delete!=0;k++)
								for (int k = tripletIndex; k <= sequence.getEnd() && delete!=0;k++)
								{
									//I could do math instead...
									delete--;
									total++;
									elementsDeleted++;
									tracker = k;
								}

								//							invalidTripleCounts -= elementsDeleted < 3 && elementsDeleted != 0 ? 0 : elementsDeleted / 3;
								invalidTripleCounts -= elementsDeleted < 3 && elementsDeleted != 0 ? 1 : elementsDeleted / 3;

								//remaining of deleted elements that don't complete a triple
								int restOfDeleted = elementsDeleted % 3;


								int remainingElementsFromTripletSequence = sequence.getEnd() - tracker;

								int before = (restOfDeleted + remainingElementsFromTripletSequence) /3;
								int after = (remainingElementsFromTripletSequence) /3;
								if (before > after && elementsDeleted > 3)
								{
									invalidTripleCounts --;
								}
							}
							
							else
							{

								int tracker = 0;
								int elementsDeleted = 0;
								int tripletIndex = sequence.tripletEndIndexes.get(0);
								//							for (int k = sequence.start; k <= sequence.getEnd() && delete!=0;k++)
								for (int k = sequence.start; k <= sequence.getEnd() && delete!=0;k++)
								{
									//I could do math instead...
									delete--;
									total++;
									elementsDeleted++;
									tracker = k;
								}

								invalidTripleCounts -= elementsDeleted < 3 && elementsDeleted != 0 ? 0 : elementsDeleted / 3;
//								invalidTripleCounts -= elementsDeleted < 3 && elementsDeleted != 0 ? 1 : elementsDeleted / 3;

								//remaining of deleted elements that don't complete a triple
								int restOfDeleted = elementsDeleted % 3;


								int remainingElementsFromTripletSequence = sequence.getEnd() - tracker;

								int before = (restOfDeleted + remainingElementsFromTripletSequence) /3;
								int after = (remainingElementsFromTripletSequence) /3;
								if (before > after && elementsDeleted > 3)
								{
									invalidTripleCounts --;
								}	
							}
						}

					}
				}
			}
			/*
                3) tentar apagar o maximo de caracteres dos grupos de 3
                    e update do que sobrar desse grupo para ver se temos de fazer update 
                        (ou seja se for maior ou igual que 3)
                    3.1) apos missing type, começar a apagar no index seguinte.
                    3.2) se não houver missing types, começar a apagar a partir do próprio index                        
			 */

			return delete + total + missingChars + invalidTripleCounts;

			//                  return modificationsBornInTheProcess +  delete + total + missingChars + invalidTripleCounts;
			//missingChars that was not discounted in the reps

		}
		//right size
		else
		{
			return Math.max(missingChars, invalidTripleCounts);
		}
	}


	private int getMissingAlphanumericConstraint(String s)
	{
		int up = 1; //uppercase
		int lo = 1; //lowercase
		int nm = 1; //number

		//for (char c : s.toCharArray())
		for (int i = 0; i < s.length() && up + lo + nm != 0; i++)
		{
			char c = s.charAt(i);

			if (nm > 0 && Character.isDigit(c))
			{
				nm--;
			}
			else if (up > 0 && Character.isUpperCase(c))
			{
				up--;
			}
			else if (lo > 0 && Character.isLowerCase(c))
			{
				lo--;
			}
		}

		return up + lo + nm;
	}

	private List<Sequence> getRepeatedSequences(String s)
	{
		/*
        List<Sequence> ans = new ArrayList<>(
            (a, b) -> 
            Integer
            .compare(
                        a.tripletEndIndexes.length(), 
                        b.tripletEndIndexes.length()
                    )
        );
		 */
		List<Sequence> ans = new ArrayList<>();

		int counter = 0;
		Sequence seq = null;
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if(i  - 1 >= 0 && c == s.charAt(i - 1))
			{
				counter++;

				if (counter % 3 == 0)
				{
					if (seq == null)
					{
						seq = new Sequence(i);
						ans.add(seq);
					}
					else
					{
						seq.size++;
						seq.addTriplet(i);
					}
				}
				else if(seq != null)
				{
					seq.size++;
				}
			}
			else
			{
				counter = 1;

				if(seq != null)
					seq = null;
			}
		}

		return ans ;
	}

	private int getInvalidTripletCounts(List<Sequence> seq)
	{

		int count = 0;

		for (Sequence s : seq)
		{
			count += s.tripletEndIndexes.size();
		}

		return count;
	}

	class Sequence
	{
		int size;
		int start;
		List<Integer> tripletEndIndexes;

		public Sequence(int index)
		{
			tripletEndIndexes = new ArrayList<>();
			tripletEndIndexes.add(index);
			start = index - 3 + 1;
			size = 3;
		}

		public void addTriplet(int index){
			tripletEndIndexes.add(index);
		}

		public int getEnd()
		{
			return start + size - 1;
		}
	}
}
