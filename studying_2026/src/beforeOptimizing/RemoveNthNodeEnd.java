package beforeOptimizing;

import shared.ListNode;

public class RemoveNthNodeEnd {
	
	/*
	 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
	 * 
	 *  Input: head = [1,2,3,4,5], n = 2
		Output: [1,2,3,5]
		Example 2:
		
		Input: head = [1], n = 1
		Output: []
		Example 3:
		
		Input: head = [1,2], n = 1
		Output: [1]
		
		The number of nodes in the list is sz.
		1 <= sz <= 30
		0 <= Node.val <= 100
		1 <= n <= sz
	 */
	
	/*
	 * 	Assumptions I made based on constraints: 
		N is a valid index in the list, and is a positive number
		N is >= 1, and is <= the size of the list (so if list is size 5
		      and 5th element from end removed, remove first element)
		The value of the node is between 0 and 100, inclusive      
		List will always have at least one node in it, and will have <= 30 nodes in it
	 */
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		//first thought -- do in two passes, traverse to get count/length of list
		//then calculate length - n
		//then use ptr to get to n-1 and move ptr.next to remove node
		
		if(head.next == null) {
			//one element, return empty list
			return null;
		}
		
		int length = 0; // Space O(1)
		ListNode count = head; //Space O(1)
		
		
		while(count != null) { // Runtime O(n) where n is the number of nodes
			length++;
			count=count.next;
		}
		
		//now use that and the difference to get the nth node
		count = head; //reuse count
		
		int i=0;
		while(i!=(length-n-1)) { //need to check on list size of 1, n= 1
			count=count.next;
			i++;
		}
		//once out of here, check value
		//System.out.println("Current node: " + count.val);
		count.next = count.next.next;
		
		return head;
	}
	
	public static void main (String[] args) {
//		ListNode number0 = new ListNode(9, null);
//		ListNode number1 = new ListNode(7, number0);
//		ListNode number2 = new ListNode(6, number1);
//		ListNode number6 = new ListNode(3, number2);
//		ListNode list1 = new ListNode(2, number6);
//		traverseList(list1);
//		System.out.println("Removing nth node....");
//		traverseList(removeNthFromEnd(list1, 2));
		
		//case of length =1
		ListNode number10 = new ListNode(9, null);
		traverseList(number10);
		System.out.println("Removing nth node....");
		traverseList(removeNthFromEnd(number10, 1));
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val + "--");
			ptr=ptr.next;
		}while(ptr != null);
		System.out.println();
	}

}
