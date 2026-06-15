package afterOptimizing;
import shared.dataStructures.ListNode;

public class MergeTwoSortedLists {
	public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		
		ListNode temp = new ListNode(0,null); //create fake starter node, gives us an easy place to build a merged list
		//makes sense because where else would we know to start?
		ListNode current = temp;  //the pointer we move as we build the result
		
		while (list1 !=null && list2 != null) { //keep looping while both lists still have nodes
			//because we can only compare list1 and list2 while both still have nodes
			if(list1.val <= list2.val) {
				current.next = list1; //attach list1 node to the result. DO NOT create new node, reuse existing
				list1=list1.next; //move list1 forward since we just used the node
			}else {
				//means list2 value is smaller
				current.next = list2;
				list2=list2.next;
			}
			current = current.next;
			//move the result pointer forward
			//after attaching a node, current should point to the end of the merged list
			//so the next node can be attached after it
		}
		
		//after the loop, at least one of the lists is empty (how we break the loop condition)
		//since the leftover is already sorted, we can just attach the remainder of the rest of the remaining list:
		if (list1 != null) {
		    current.next = list1;
		} else {
		    current.next = list2;
		}
		//no need to keep comparing this way
		return temp.next; //return the head of the merged list, since we iterated through list1, list2, and current,
		//this is the only starting ptr we have
		
		
		//Time: O(m + n)
		//Space: O(1)
				
		//		Because:
		//
		//		every node from both lists is visited/attached at most once
		//		reuse existing nodes instead of creating new ones
		//		temp and current are just pointer variables
		//
		//		One tiny nuance: the dummy node is one extra node, but that is still constant space, so it stays O(1)
				
				
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
	    ListNode ptr = head;

	    while (ptr != null) {
	        System.out.print(ptr.val);
	        ptr = ptr.next;
	    }
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
	 * 3. WHY && IN THE LOOP CONDITION
	 * -------------------------------------------------
	 * Optimized solution uses:
	 *
	 *     while(list1 != null && list2 != null)
	 *
	 * because comparison only makes sense while BOTH
	 * lists still have nodes.
	 *
	 * Inside the loop we do:
	 *
	 *     list1.val <= list2.val
	 *
	 * which requires BOTH nodes to exist.
	 *
	 * Using:
	 *
	 *     ||
	 *
	 * is not necessarily wrong,
	 * but it forces extra branching:
	 *
	 *     if(list1 == null)
	 *     else if(list2 == null)
	 *
	 * The optimized approach says:
	 *
	 *     compare while BOTH exist
	 *     then attach remainder afterward
	 *
	 * which simplifies the logic.
	 *
	 * -------------------------------------------------
	 *
	 * 4. DUMMY NODE PATTERN
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
	 * 5. WHY current CAN MOVE
	 * -------------------------------------------------
	 * current is ONLY:
	 *
	 *     the moving tail of the merged list
	 *
	 * It is NOT:
	 *
	 *     the head of the merged list
	 *
	 * Example:
	 *
	 *     dummy -> 1 -> 2 -> 3
	 *                         ↑
	 *                      current
	 *
	 * current moves as we build.
	 *
	 * BUT:
	 *
	 *     dummy never moves
	 *
	 * Therefore:
	 *
	 *     dummy.next
	 *
	 * always points to the REAL head of the answer.
	 *
	 * This is the SAME dummy-node pattern used in:
	 *
	 *     Add Two Numbers
	 *
	 * -------------------------------------------------
	 *
	 * 6. WHY MOVING list1/list2 IS OKAY
	 * -------------------------------------------------
	 * This:
	 *
	 *     list1 = list1.next;
	 *
	 * does NOT delete nodes.
	 *
	 * It ONLY changes:
	 *
	 *     where the local variable points
	 *
	 * The original nodes still exist in memory.
	 *
	 * Since the goal is:
	 *
	 *     return merged list
	 *
	 * we do NOT need to preserve the original head
	 * references separately.
	 *
	 * -------------------------------------------------
	 *
	 * 7. POINTER / REFERENCE LESSON
	 * -------------------------------------------------
	 * Variables like:
	 *
	 *     current
	 *     list1
	 *     ptr1
	 *
	 * are REFERENCES to nodes.
	 *
	 * Example:
	 *
	 *     ListNode a = node;
	 *     ListNode b = node;
	 *
	 * BOTH point to same object.
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
	 * 8. SPLICE EXISTING NODES
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
	 * 9. ATTACH REMAINDER
	 * -------------------------------------------------
	 * Once one list becomes null,
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
	 * 10. EMPTY LIST REPRESENTATION
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
	 * 11. BIG-O
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
	 * 12. INTERVIEW PATTERN RECOGNITION
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