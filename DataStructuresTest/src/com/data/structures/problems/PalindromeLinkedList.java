package com.data.structures.problems;

/**
 * 
 * https://leetcode.com/problems/palindrome-linked-list
 * EASY
 * @author Nelson Costa
 *
 */
public class PalindromeLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PalindromeLinkedList p = new PalindromeLinkedList();
		ListNode l1 = p.new ListNode(-129);
		ListNode l2 = p.new ListNode(9);
		ListNode l3 = p.new ListNode(9);
		ListNode l4 = p.new ListNode(-129);

		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		p.printList(l1);
		System.out.println(p.isPalindrome(l1));
	}

	/**
	 * INTUITION
	 * 
	 * IF RIGHTMOST DIGIT EQUALS TO LEFTMOST DIGIT THAN PALINDROME
	 * 
	 * @failed forgot to put floor, forgot to add the num while the loop was going (misjugment), forgot to update base10 in the loop; the last fail was the floor + 1 that was wrong...
	 * another fail; detected that Math.log10 of 0 is -infinity, which destroyed my results...
	 * identified misjudgement regarding adding value to itself, failed again because I didn't thin in the scenario of a linkedlist starting with 0.
	 * failed finally, this is not the direction, I should go with a string... solution.... negative numbers and zeros wasted my solution
	 * @time 	O(N) in iterating the linkedList, detected that I was using the wrong param in the first log10
	 * @space 	O(1) no growth in space according to input
	 * @param head
	 * @return
	 */
	public boolean isPalindrome0(ListNode head) {

		if (head == null)
			return true;

		int num = 0;
		//linkedList to int
		while (head != null) {
			num = num * (int) Math.pow(10,  getLog10(head.val) + 1) + head.val;
			head = head.next; 
		}

		System.out.println(num);

		//Verify Palindrome
		int base10;
		int rightMost;
		int leftMost;

		while (num > 0) {
			base10 = (int) Math.pow(10, Math.floor(Math.log10(num))); //get base 10 closest to num without surpassing it
			rightMost = num % 10; //Get rightMost Digit
			leftMost = (num - (num % base10))/base10; //Get LeftMost Digit

			if (rightMost != leftMost)
			{
				return false;
			}

			num = (num - (leftMost * base10))/10; //Remove rightMost and LeftMost Digits
		}

		return true;
	}

	private int getLog10(int val) {
		return val <= 0 ? 0 : (int) Math.floor(Math.log10(val));
	}

	/**
	 * @failed in -129 -129, this guys dont tell that the palindrome is in respect to numbers....
	 * 
	 * @time O(N)
	 * @space O(1)
	 * @param head
	 * @return
	 */
	public boolean isPalindrome1(ListNode head) {

		String s = "";
		while (head != null)
		{
			s += encode(head.val);
			head = head.next;
		}

		System.out.println(s);

		for (int i = 0, j = s.length() - 1; i < s.length(); i++, j--) {
			if (s.charAt(i) != s.charAt(j))
				return false;
		}

		return true;
	}

	/**
	 * 
	 * Runtime: 10 ms, faster than 6.50% of Java online submissions for Palindrome Linked List.
Memory Usage: 46.5 MB, less than 6.10% of Java online submissions for Palindrome Linked List.
	 * 
	 * @failed used != instead of not equals, split separator needed to be scaped...
	 * 
	 * @time (N)
	 * @space(N) => O(2N)
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head)
	{
		if (head == null)
			return true;

		StringBuilder s = new StringBuilder("");

		while (head != null)
		{
			s.append(Integer.toString(head.val));
			s.append("|");
			head = head.next;
		}

		s.deleteCharAt(s.length()-1);

		String [] array = s.toString().split("\\|");

		for (int i = 0, j = array.length - 1; i <= j; i++, j--)
		{
			if(!array[i].equals(array[j]))
				return false;
		}

		return true;
	}

	private String encode(int val) {
		return val < 0? "." + Math.abs(val) + "." : val + "";
	}

	private void printList(ListNode l) {
		while (l != null)
		{
			System.out.print(l.val);

			l = l.next;

			if (l != null)
				System.out.print(" -> ");
		}
		System.out.println();
	}

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class PalindromeLinkedListSolution {

	/**
	 * In place solution was the only solution possible to achieve space O(1)
	 * 
	 * INTUITION
	 * 
	 * break linkedlist in half, 
	 * reverse second half, 
	 * compare first item of each half
	 * reverse second half of the list back
	 * 
	 * Very Cleaver solution
	 * 
	 * @time O(N)
	 * @space (1)
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {

		if (head == null) return true;

		// Find the end of first half and reverse second half.
		ListNode firstHalfEnd = endOfFirstHalf(head);
		ListNode secondHalfStart = reverseList(firstHalfEnd.next);

		// Check whether or not there is a palindrome.
		ListNode p1 = head;
		ListNode p2 = secondHalfStart;
		boolean result = true;
		while (result && p2 != null) {
			if (p1.val != p2.val) result = false;
			p1 = p1.next;
			p2 = p2.next;
		}        

		// Restore the list and return the result.
		firstHalfEnd.next = reverseList(secondHalfStart);
		return result;
	}

	// Taken from https://leetcode.com/problems/reverse-linked-list/solution/
	private ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		return prev;
	}

	private ListNode endOfFirstHalf(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
}
