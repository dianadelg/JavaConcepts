package beforeOptimizing;
import shared.ListNode;

public class MergeTwoSortedLists {

	/*
	 * You are given the heads of two sorted linked lists list1 and list2.
		Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
		Return the head of the merged linked list.
		
		Merge in ascending order
		
	 */
	
	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		//given a list 2 3 6 7 9
		//and   a list 1 4 5
		//return 1 2 3 4 5 6 7
		//ALSO. If at least one is empty, return the other list (because if both empty, return one empty)
		
		ListNode ptr1 = list1;
		ListNode ptr2 = list2;
		ListNode solution = new ListNode(0,null);
		ListNode solutionPtr = solution; 
		
		if(ptr1 == null || ptr2 == null) {
			if(ptr1 == null) {
				return ptr2;
			}else {
				return ptr1;
			}
		}
		while(ptr1.next!=null && ptr2.next!=null) {
			//can we merge in one list?
			//else means still data
			if(ptr1.val>ptr2.val) {
				solutionPtr.next = new ListNode(ptr1.val, null);
				ptr1=ptr1.next;
			}else {
				//includes if values are equal
				solutionPtr.next = new ListNode(ptr1.val, null);
				ptr2=ptr2.next;
			}
		}
		//if next one is null, then return remaining other list
		if(ptr1.next==null) {
			//means still stuff for ptr2
			
		}else if(ptr2.next==null) {
			//means still stuff for ptr1
			
		}else {
			//return merged list
			
		}
		
		return null;
		
	}
	
	public static void main (String[] args) {
		ListNode number0 = new ListNode(9, null);
		ListNode number1 = new ListNode(7, number0);
		ListNode number2 = new ListNode(6, number1);
		ListNode number6 = new ListNode(3, number2);
		ListNode list1 = new ListNode(2, number6);
		traverseList(list1);
		System.out.println();
		ListNode number4 = new ListNode(5, null);
		ListNode number5 = new ListNode(4, number4);
		ListNode list2 = new ListNode(1, number5);
		traverseList(list2);
		System.out.println();
		ListNode list3 = null;
		System.out.println("-------------Merged List------------");
		traverseList(mergeTwoLists(list1, list2));
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val);
			ptr=ptr.next;
		}while(ptr != null);
	}
	
}
