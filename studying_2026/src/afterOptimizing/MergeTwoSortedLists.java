package afterOptimizing;
import shared.ListNode;

public class MergeTwoSortedLists {
	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		
		return null; //TO DO
		
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
		System.out.println();
		
		
		//case 2
		ListNode numbers0 = new ListNode(6, null);
		ListNode numbers1 = new ListNode(5, numbers0);
		ListNode numbers2 = new ListNode(4, numbers1);
		ListNode numbers6 = new ListNode(3, numbers2);
		ListNode lists1 = new ListNode(3, numbers6);
		traverseList(lists1);
		System.out.println();
		ListNode numbers4 = new ListNode(5, null);
		ListNode numbers5 = new ListNode(4, numbers4);
		ListNode lists2 = new ListNode(1, numbers5);
		traverseList(lists2);
		System.out.println();
		System.out.println("-------------Merged List------------");
		traverseList(mergeTwoLists(lists1, lists2));
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val);
			ptr=ptr.next;
		}while(ptr != null);
	}
	
}
