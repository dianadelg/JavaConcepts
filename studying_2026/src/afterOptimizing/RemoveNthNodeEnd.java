package afterOptimizing;

import shared.ListNode;

public class RemoveNthNodeEnd {
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		//new strategy: one fast and one slow pointer 
		//(one is n spaces behind the fast one?)
		ListNode dummy = new ListNode(0, head); //use to handle removing the head

	    ListNode fast = dummy;
	    ListNode slow = dummy;

	    for (int i = 0; i <= n; i++) { //this is how we move fast n steps ahead of slow
	    	//like I came up with
	        fast = fast.next;
	    }

	    while (fast != null) {
	        fast = fast.next;
	        slow = slow.next;
	    }//now move both until end of list, ptrs will be in the right place

	    slow.next = slow.next.next; //skip the nth node

	    return dummy.next; //return node after 0
	    
	    //Time: O(d)
	    //Space: O(1)

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
//		ListNode number10 = new ListNode(9, null);
//		traverseList(number10);
//		System.out.println("Removing nth node....");
//		traverseList(removeNthFromEnd(number10, 1));
		
		//case of removing the nth node which is the head
		ListNode number11 = new ListNode(9, null);
		ListNode number12 = new ListNode(7, number11);
		traverseList(number12);
		System.out.println("Removing nth node....");
		traverseList(removeNthFromEnd(number12, 2));
	}
	
	public static void traverseList(ListNode head) {
		ListNode ptr= head;
		do{
			System.out.print(ptr.val + "--");
			ptr=ptr.next;
		}while(ptr != null);
		System.out.println();
	}
	
	/*
	 * REMOVE NTH NODE FROM END OF LIST
	 * PATTERNS / THINGS TO REMEMBER
	 *
	 * -------------------------------------------------
	 * 1. MY FIRST INSTINCT
	 * -------------------------------------------------
	 * Two-pass solution:
	 *
	 *     Pass 1:
	 *         count length
	 *
	 *     Pass 2:
	 *         find (length - n)
	 *
	 *         remove node
	 *
	 * This is perfectly valid.
	 *
	 * Time:
	 *
	 *     O(d)
	 *
	 * because:
	 *
	 *     O(d) + O(d) = O(2d) = O(d)
	 *
	 * Space:
	 *
	 *     O(1)
	 *
	 * -------------------------------------------------
	 * 2. IMPORTANT EDGE CASE LESSON
	 * -------------------------------------------------
	 * Before optimizing:
	 *
	 * Ask:
	 *
	 *     What if list has one node?
	 *     What if removing the head?
	 *     What if removing the tail?
	 *
	 * Many linked list bugs come from forgetting
	 * edge cases.
	 *
	 * -------------------------------------------------
	 * 3. THE BIG INSIGHT I FOUND
	 * -------------------------------------------------
	 * I independently discovered:
	 *
	 *     keep one pointer ahead
	 *     keep another pointer behind
	 *
	 * If front pointer reaches end,
	 * back pointer is automatically positioned
	 * relative to the target.
	 *
	 * This is a VERY common interview pattern:
	 *
	 *     fixed-gap two pointers
	 *
	 * -------------------------------------------------
	 * 4. TWO POINTER GAP PATTERN
	 * -------------------------------------------------
	 * Move:
	 *
	 *     fast
	 *
	 * ahead by:
	 *
	 *     n + 1
	 *
	 * Then move:
	 *
	 *     fast
	 *     slow
	 *
	 * together.
	 *
	 * When:
	 *
	 *     fast == null
	 *
	 * slow is positioned:
	 *
	 *     immediately BEFORE
	 *     the node to remove
	 *
	 * -------------------------------------------------
	 * 5. WHY DUMMY NODE EXISTS
	 * -------------------------------------------------
	 * Without dummy:
	 *
	 *     1 -> 2
	 *
	 * removing head means:
	 *
	 *     there is no node before 1
	 *
	 * which makes deletion awkward.
	 *
	 * With dummy:
	 *
	 *     dummy -> 1 -> 2
	 *
	 * there is ALWAYS a node before
	 * the deletion target.
	 *
	 * This eliminates special cases.
	 *
	 * -------------------------------------------------
	 * 6. WHY n+1 STEPS?
	 * -------------------------------------------------
	 * We don't want slow to land ON
	 * the node being deleted.
	 *
	 * We want slow to land:
	 *
	 *     one node BEFORE it
	 *
	 * because deletion requires:
	 *
	 *     slow.next = slow.next.next
	 *
	 * Therefore:
	 *
	 *     fast moves n+1 ahead
	 *
	 * to maintain the correct gap.
	 *
	 * -------------------------------------------------
	 * 7. DUMMY NODE PATTERN AGAIN
	 * -------------------------------------------------
	 * Same pattern as:
	 *
	 *     Add Two Numbers
	 *     Merge Two Sorted Lists
	 *
	 * Dummy node:
	 *
	 *     simplifies edge cases
	 *     simplifies head deletion
	 *     gives stable return point
	 *
	 * Return:
	 *
	 *     dummy.next
	 *
	 * -------------------------------------------------
	 * 8. BIG-O
	 * -------------------------------------------------
	 * d = number of nodes
	 *
	 * Optimized:
	 *
	 *     Time: O(d)
	 *     Space: O(1)
	 *
	 * Two-pass:
	 *
	 *     Time: O(d)
	 *     Space: O(1)
	 *
	 * Optimized version is preferred because
	 * it completes in a single traversal.
	 *
	 * -------------------------------------------------
	 * 9. INTERVIEW PATTERN RECOGNITION
	 * -------------------------------------------------
	 * If problem says:
	 *
	 *     kth from end
	 *     nth from end
	 *     remove nth from end
	 *     find distance between nodes
	 *
	 * think:
	 *
	 *     fast/slow pointers
	 *     fixed-size gap
	 *     dummy node
	 *
	 */

}
