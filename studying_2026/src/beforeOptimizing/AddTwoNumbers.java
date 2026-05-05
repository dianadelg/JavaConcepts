package beforeOptimizing;

public class AddTwoNumbers {
	
	/*
	 * You are given two non-empty linked lists representing two 
	 * non-negative integers. The digits are stored in reverse order, 
	 * and each of their nodes contains a single digit. 
	 * Add the two numbers and return the sum as a linked list.
	   You may assume the two numbers do not contain any leading zero, 
	   except the number 0 itself.
	   
	   It is guaranteed that the list represents a number that does 
	   not have leading zeros.
	   
	 */
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
		// thinking: create string, add element to front of string as we traverse
		// then convert string
		ListNode ptr1 = l1;
		ListNode ptr2 = l2;
		String number1 = "";
		String number2 = "";
		while(ptr1 != null) {
			number1 = ptr1.val + number1;
			ptr1=ptr1.next;
		} // O(n)
		
		while(ptr2 != null) {
			number2 = ptr2.val + number2;
			ptr2=ptr2.next;
		} // O(m)
		
		String result = Integer.toString((Integer.parseInt(number1) + Integer.parseInt(number2))); // O(1)
		
		System.out.print(result);		
		//now take and input as List

		ListNode ptr;
		for(int i=result.length()-1; i>0; i--) {
			ListNode temp = new ListNode(Integer.parseInt(String.valueOf(result.charAt(i))), null);
			
		}
		//fix this
		
		
		
		return null;
	}
	
	/*
	 *  Input: l1 = [2,4,3], l2 = [5,6,4]
		Output: [7,0,8]
		Explanation: 342 + 465 = 807.
	 */
	
	public static void main(String[] args) {
		ListNode number1 = new ListNode(3, null);
		ListNode number2 = new ListNode(4, number1);
		ListNode list1 = new ListNode(2, number2);
		//traverseList(list1);
		//System.out.println();
		ListNode number4 = new ListNode(4, null);
		ListNode number5 = new ListNode(6, number4);
		ListNode list2 = new ListNode(5, number5);
		//traverseList(list2);
		traverseList(addTwoNumbers(list1, list2));
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val);
			ptr=ptr.next;
		}while(ptr != null);
	}
	

}
