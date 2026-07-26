package afterOptimizing;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElement {
	
//	Given an integer array nums and an integer k, return the kth largest element in the array.
//
//	Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//	Can you solve it without sorting?
	
	
	
	/* 
	 * Prev solution said max heap but I'll keep all of the numbers
	 * this time, I only need a min heap to keep k numbers
	 * 
	 * Why a min heap?
	 * Because the smallest of those k numbers is exactly the kth largest overall.

		Algo: add item. If > k items in min heap, remove the smallest with poll
	 */
    public static int findKthLargest(int[] nums, int k) {
    	PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Heap never stores more than k+1 elements (briefly +1 before we remove from queue). Space: O(k)

        //add each number from nums
         for(int num: nums) { //O(n)
        	 minHeap.add(num);
         	if(minHeap.size()>k) {
         		//remove smallest
         		minHeap.poll(); // O(log k) because the heap contains at most k+1 elements
         	}
         }
         /*
          * Example:
          *
          * nums = [2, 1, 3]
          * k = 2
          *
          * Add 2:
          * heap = [2]
          * size = 1
          * size > k? No
          *
          * Add 1:
          * heap = [1, 2]
          * size = 2
          * size > k? No
          *
          * Add 3:
          * heap contains [1, 2, 3]
          * size = 3
          * size > k? Yes
          *
          * Remove the smallest:
          * poll() removes 1
          *
          * heap now contains [2, 3]
          *
          * The heap contains the two largest values.
          * peek() returns the smallest of those values: 2.
          *
          * Therefore, 2 is the second-largest element.
          */
         
         //once we're here, we end up with the biggest k elements
         //inspect the smallest of those, as this is k
         return minHeap.peek(); //O(1)
         
         //space: O(k)
         //runtime: O(nlogk)
    }
    
    public static void main (String [] args) {
    	int [] nums = {3,2,1,5,6,4};
    	int k = 6; //check last element, should be 1
    	
    	System.out.println(findKthLargest(nums, k));
    	
    	int [] nums2 = {3,2,3,1,2,4,5,5,6};
    	int k2 = 4; //4
    	
    	System.out.println(findKthLargest(nums2, k2));
    	
    	//can there be negatives, yes
    	
    	int [] nums3 = {-3,-2,-3,-1,-2,-4,-5,-5,-6};
    	int k3 = 4; //-3
    	
    	System.out.println(findKthLargest(nums3, k3));
    	
    	
    	
    }
    
    /*
     * KTH LARGEST / TOP K PATTERN
     *
     * Goal:
     *
     * Don't sort everything.
     *
     * Only keep what you actually care about.
     *
     * Max Heap:
     *
     * Keep ALL elements.
     *
     * Remove largest k-1.
     *
     * Time:
     * O(n log n)
     *
     * Space:
     * O(n)
     *
     * -----------------------------
     *
     * Min Heap (Optimized)
     *
     * Keep ONLY the k largest elements.
     *
     * Whenever heap size > k:
     *
     * remove the smallest.
     *
     * Why is this safe?
     *
     * Because the smallest element can never
     * end up in the final top k largest values.
     * 
     * 
     *      
     * Heap invariant:
     *
     * The heap ALWAYS contains
     * the k largest numbers seen so far.
     *
     * Therefore,
     *
     * the smallest number in the heap
     * is exactly the kth largest overall.
     *
     * Time:
     * O(n log k)
     *
     * Space:
     * O(k)

     * Recognition:
     *
     * - kth largest
     * - kth smallest
     * - top k
     * - top k frequent
     * - maintain largest k elements
     *
     * Think:
     *
     * Heap
     *
     */

}
