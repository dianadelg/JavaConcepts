package beforeOptimizing;

import shared.ListNode;

public class RemoveNthNodeEnd {
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		return null;
	}
	
	public static void main (String[] args) {
		removeNthFromEnd(null, 2);
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val);
			ptr=ptr.next;
		}while(ptr != null);
	}

}
