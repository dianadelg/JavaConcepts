package beforeOptimizing;
import shared.dataStructures.ListNode;

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
		
		if(ptr1 == null || ptr2 == null) { //preliminary check for empty list
			if(ptr1 == null) {
				return ptr2;
			}else {
				return ptr1;
			}
		}
		while(ptr1!=null || ptr2!=null) {
			// in optimized version, we compare while both exist. 
			//Once one runs out, stop comparing and attach the rest.
			//in this version, the loop keeps going even when one is already null
			//so we'd need extra conditions like ptr1==null and ptr2==null
			//whereas in the optimized version, the comparison will only ever work if both still have nodes
			//so not wrong, just not optimized
			
			
			//can we merge in one list?
			//else means still data
			if(ptr1==null) {
				solutionPtr.next = new ListNode(ptr2.val, null);
				solutionPtr=solutionPtr.next;
				ptr2=ptr2.next;
			}
			else if(ptr2==null) {
				solutionPtr.next = new ListNode(ptr1.val, null);
				solutionPtr=solutionPtr.next;
				ptr1=ptr1.next;
			}
			else if(ptr1.val>ptr2.val) {
				solutionPtr.next = new ListNode(ptr2.val, null);
				solutionPtr=solutionPtr.next;
				ptr2=ptr2.next;
				solutionPtr.next = new ListNode(ptr1.val, null); //THIS IS DANGEROUS ADDING TWO NODES AT ONCE
				//because what if we add
				/*
					list1 = [1, 10]
					list2 = [2, 3, 4]
					
					Code might do -- because 3<10 but we didn't check for 10 against 4
					
					1, 2, 3, 10, 4
					
					because it adds 10 before checking 4. 
				 */
				solutionPtr=solutionPtr.next;
				ptr1=ptr1.next;
			}else {
				solutionPtr.next = new ListNode(ptr1.val, null);
				solutionPtr=solutionPtr.next;
				ptr1=ptr1.next;
				solutionPtr.next = new ListNode(ptr2.val, null);
				solutionPtr=solutionPtr.next;
				ptr2=ptr2.next;
			}
			
			//I ALSO DIDN'T READ INSTRUCTIONS AGAIN...need to stop just jumping into code
			//I think I get excited lol
			//this is about SPLICING the nodes, not creating new ones
			//If I did that, would be O(1) space complexity because reusing nodes
			//and runtime still would've been O(m+n), since we have to visit each node
		}
		
		return solution.next;
		
		//runtime -- O(m+n), where m is the length of one list and n is the other, in the worst case, we would traverse all numbers in both lists
		//in the best case, one is null and we just return the other list
		//in this algorithm, we check every number until the end of the list
		
		//space complexity -- also O(m+n), where the list returned is m+n nodes/numbers
		
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
