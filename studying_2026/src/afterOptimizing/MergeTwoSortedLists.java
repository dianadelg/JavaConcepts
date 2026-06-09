package afterOptimizing;
import shared.ListNode;

public class MergeTwoSortedLists {
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
		}
		
		return solution.next;
		
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
	
	/*
	 * MERGE TWO SORTED LISTS PATTERNS / THINGS TO REMEMBER
	 *
	 * -------------------------------------------------
	 * 1. CORE IDEA
	 * -------------------------------------------------
	 * Both input lists are already sorted.
	 *
	 * Therefore, at each step:
	 *
	 *     compare list1.val and list2.val
	 *
	 * Attach the smaller node to the result list.
	 *
	 * Move ONLY the pointer from the list you used.
	 *
	 * -------------------------------------------------
	 *
	 * 2. IMPORTANT MERGING PATTERN
	 * -------------------------------------------------
	 * VERY important insight:
	 *
	 * Each loop iteration should usually add:
	 *
	 *     ONE node
	 *
	 * NOT:
	 *
	 *     one from each list
	 *
	 * Why?
	 *
	 * Because after adding ONE node,
	 * you must compare again.
	 *
	 * Example:
	 *
	 *     list1 = [1, 10]
	 *     list2 = [2, 3, 4]
	 *
	 * If we blindly add:
	 *
	 *     1 then 2
	 *     OR 2 then 10
	 *
	 * without re-comparing,
	 * we can break sorting order.
	 *
	 * Correct pattern:
	 *
	 *     compare
	 *     attach smaller ONE
	 *     move that pointer
	 *     compare again
	 *
	 * -------------------------------------------------
	 *
	 * 3. DUMMY NODE PATTERN
	 * -------------------------------------------------
	 * Use a dummy node to avoid special-casing the first node.
	 *
	 *     ListNode dummy = new ListNode(0);
	 *     ListNode current = dummy;
	 *
	 * Then every insertion becomes identical:
	 *
	 *     current.next = chosenNode;
	 *     current = current.next;
	 *
	 * Return:
	 *
	 *     dummy.next
	 *
	 * because dummy is fake and not part of the answer.
	 *
	 * -------------------------------------------------
	 *
	 * 4. SPLICE EXISTING NODES
	 * -------------------------------------------------
	 * The prompt says:
	 *
	 *     "splicing together the nodes"
	 *
	 * Meaning:
	 *
	 *     reuse existing nodes
	 *
	 * Optimized:
	 *
	 *     current.next = list1;
	 *
	 * NOT:
	 *
	 *     new ListNode(list1.val)
	 *
	 * Reusing nodes gives:
	 *
	 *     O(1) extra space
	 *
	 * Creating brand-new nodes gives:
	 *
	 *     O(m + n) space
	 *
	 * because a whole new list is allocated.
	 *
	 * -------------------------------------------------
	 *
	 * 5. LOOP CONDITION
	 * -------------------------------------------------
	 * Use:
	 *
	 *     while(list1 != null && list2 != null)
	 *
	 * because comparison only makes sense while BOTH
	 * lists still have nodes.
	 *
	 * Once one list becomes null:
	 *
	 *     stop comparing
	 *
	 * and attach the remaining list directly.
	 *
	 * -------------------------------------------------
	 *
	 * 6. ATTACH REMAINDER
	 * -------------------------------------------------
	 * Once one list is exhausted,
	 * the remaining nodes in the other list are already
	 * sorted.
	 *
	 * Therefore:
	 *
	 *     current.next = remainingList
	 *
	 * No need to loop through manually.
	 *
	 * -------------------------------------------------
	 *
	 * 7. IMPORTANT POINTER LESSON
	 * -------------------------------------------------
	 * Variables like:
	 *
	 *     current
	 *     solutionPtr
	 *
	 * are POINTERS/REFERENCES to nodes.
	 *
	 * Example:
	 *
	 *     ListNode a = node;
	 *     ListNode b = node;
	 *
	 * BOTH point to same node.
	 *
	 * If you mutate the node:
	 *
	 *     a.next = something
	 *
	 * then:
	 *
	 *     b sees the change too
	 *
	 * because same underlying object.
	 *
	 * BUT:
	 *
	 *     a = newNode;
	 *
	 * only changes where:
	 *
	 *     a points
	 *
	 * NOT where:
	 *
	 *     b points
	 *
	 * -------------------------------------------------
	 *
	 * 8. EMPTY LIST REPRESENTATION
	 * -------------------------------------------------
	 * Empty linked list:
	 *
	 *     null
	 *
	 * NOT:
	 *
	 *     new ListNode()
	 *
	 * because:
	 *
	 *     new ListNode()
	 *
	 * creates a REAL node:
	 *
	 *     val = 0
	 *     next = null
	 *
	 * which represents:
	 *
	 *     [0]
	 *
	 * not:
	 *
	 *     []
	 *
	 * -------------------------------------------------
	 *
	 * 9. BIG-O
	 * -------------------------------------------------
	 * Let:
	 *
	 *     m = length of list1
	 *     n = length of list2
	 *
	 * Time:
	 *
	 *     O(m + n)
	 *
	 * because every node is visited/attached once.
	 *
	 * Optimized Space:
	 *
	 *     O(1)
	 *
	 * because only pointers are used and existing nodes
	 * are reused.
	 *
	 * Non-optimized approach:
	 *
	 *     O(m + n)
	 *
	 * if creating brand-new nodes for the merged list.
	 *
	 * -------------------------------------------------
	 *
	 * 10. INTERVIEW PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If problem says:
	 *
	 *     merge sorted linked lists
	 *     combine sorted structures
	 *     sorted merge
	 *
	 * think:
	 *
	 *     two pointers
	 *     dummy node
	 *     attach smaller
	 *     advance one pointer at a time
	 *     reuse existing nodes when possible
	 *
	 */
	
}
