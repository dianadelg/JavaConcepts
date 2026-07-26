package afterOptimizing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
	//	Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
	
	//	Example 1:
	//	Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
	//	Output: [[1,6],[8,10],[15,18]]
	//	Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
	//	
	
	//    Example 2:
	//	Input: intervals = [[1,4],[4,5]]
	//	Output: [[1,5]]
	//	Explanation: Intervals [1,4] and [4,5] are considered overlapping.
	
	
	//Good representation of the problem, I really struggled to understand what this was asking
	//https://www.youtube.com/watch?v=dzNIPX7HY6A
	
	public static int[][] merge(int[][] intervals) {
	
		if (intervals.length <= 1) {
			return intervals; //this is a case based on constraints
		}
		
		//cannot assume intervals are in order
		//so we sort the entire array based off of the left index ([1, 2] --> sort by the 1
		Arrays.sort(intervals, Comparator.comparingInt(i->i[0])); //use first interval index to sort
		//so now all of the intervals that start together have been grouped together
		
		List<int[]> result = new ArrayList<>(); // holds final merged intervals
		int[] currentMergedInterval = intervals[0];
		result.add(currentMergedInterval); //add the first element
		//this will be where we start at either including or extending
		//important to note, result and newInterval refer to the same inner array
		//We do not have to remove [1,3] and add [1,5]. We directly modify the array already stored inside result
		
		//if your next interval's starting point is less than the previous interval's endind point, then
		//there is overlap, we can merge the intervals
		
		//ex [1, 3] [2, 4] [2, 6]
		//so 2 (start of next) is less than 3 (end of current) so we can merge
		//new interval becomes [1,4]
		//now [1,4] [2,6]
		//2 (start of next) is less than 4 (end of current) so we merge
		//[1, 6]
		
		for(int[] interval: intervals) {
			//during the first iteration, interval and currentMergedInterval[1] are the same value [1,3]
			if(interval[0] <= currentMergedInterval[1]) {
				//overlapping intervals, update end if needed
				currentMergedInterval[1]= Math.max(currentMergedInterval[1], interval[1]);
			}
			else {
				//disjoint intervals
				currentMergedInterval = interval;
				result.add(currentMergedInterval);
			}
	}
	return result.toArray(new int[result.size()][]);
	}
	
	//Time O(n log n) since you have to sort array
	//Space O(n) due to result array, at max none of the intervals overlap so will hold all n intervals
}

//NOTE: LeetCode considers touching intervals to overlap. So [1, 5] [5, 8] --> [1, 8]

/*
 * =========================================================
 * MERGE INTERVALS — PATTERN NOTES
 * =========================================================
 *
 * PROBLEM:
 *
 * Given a collection of intervals, combine every group
 * of intervals that overlaps.
 *
 * Example:
 *
 * [[1,3], [2,6], [8,10]]
 *
 * becomes:
 *
 * [[1,6], [8,10]]
 *
 *
 * =========================================================
 * UNDERSTANDING THE DATA
 * =========================================================
 *
 * int[][] intervals
 *
 * means:
 *
 * An array whose elements are int[] arrays.
 *
 * Example:
 *
 * intervals = [[1,3], [2,6], [8,10]]
 *
 * intervals[0]       -> [1,3]
 * intervals[1]       -> [2,6]
 * intervals[2]       -> [8,10]
 *
 * intervals[0][0]    -> 1
 * intervals[0][1]    -> 3
 *
 *
 * One interval has the type:
 *
 * int[] interval
 *
 * interval[0] = start
 * interval[1] = end
 *
 *
 * =========================================================
 * RECOGNITION SIGNALS
 * =========================================================
 *
 * Think "sort and merge intervals" when the problem involves:
 *
 * - overlapping ranges
 * - meeting times
 * - appointment windows
 * - schedules
 * - time periods
 * - numerical ranges
 * - combining connected segments
 *
 *
 * =========================================================
 * MAIN IDEA
 * =========================================================
 *
 * 1. Sort all intervals by starting value.
 *
 * 2. Begin with the first interval as the current
 *    merged interval.
 *
 * 3. Examine every next interval.
 *
 * 4. If it overlaps the current merged interval:
 *       extend the current ending if necessary.
 *
 * 5. If it does not overlap:
 *       begin a new merged interval and add it
 *       to the result.
 *
 *
 * =========================================================
 * WHY SORT FIRST?
 * =========================================================
 *
 * Before sorting:
 *
 * [[8,10], [1,3], [2,6]]
 *
 * Intervals that overlap may be far apart.
 *
 * After sorting by start:
 *
 * [[1,3], [2,6], [8,10]]
 *
 * Possible overlaps are now next to each other.
 *
 * This allows one left-to-right pass.
 *
 *
 * =========================================================
 * CENTRAL DECISION
 * =========================================================
 *
 * current interval:
 *
 * interval = [currentStart, currentEnd]
 *
 * working merged interval:
 *
 * newInterval = [mergedStart, mergedEnd]
 *
 * Overlap exists when:
 *
 * currentStart <= mergedEnd
 *
 * In code:
 *
 * interval[0] <= newInterval[1]
 *
 *
 * Example:
 *
 * newInterval = [1,3]
 * interval    = [2,6]
 *
 * Is:
 *
 * 2 <= 3?
 *
 * Yes, so they overlap.
 *
 *
 * =========================================================
 * MERGING
 * =========================================================
 *
 * The start does not need to change because intervals
 * were sorted by start.
 *
 * Only the ending may need to grow:
 *
 * newInterval[1] =
 *     Math.max(newInterval[1], interval[1]);
 *
 *
 * Example:
 *
 * [1,3] and [2,6]
 *
 * merged start = 1
 * merged end   = max(3,6) = 6
 *
 * result:
 *
 * [1,6]
 *
 *
 * Math.max() is necessary because the next interval
 * may be completely contained inside the current one.
 *
 * Example:
 *
 * [1,10] and [2,6]
 *
 * The result must remain:
 *
 * [1,10]
 *
 * It must not shrink to [1,6].
 *
 *
 * =========================================================
 * WHEN THERE IS NO OVERLAP
 * =========================================================
 *
 * Example:
 *
 * newInterval = [1,6]
 * interval    = [8,10]
 *
 * Check:
 *
 * 8 <= 6
 *
 * False.
 *
 * There is a gap, so [8,10] starts a new merged group.
 *
 * In code:
 *
 * newInterval = interval;
 * result.add(newInterval);
 *
 *
 * This does not delete the old interval from result.
 *
 * result already keeps a reference to the completed
 * interval [1,6].
 *
 *
 * =========================================================
 * VARIABLE MEANINGS
 * =========================================================
 *
 * intervals:
 *
 * All input intervals.
 *
 *
 * interval:
 *
 * The one interval currently being examined by the loop.
 *
 *
 * newInterval:
 *
 * The current merged group.
 *
 * It is the most recently added interval in result,
 * and it may still be extended.
 *
 * A clearer variable name would be:
 *
 * currentMergedInterval
 *
 *
 * result:
 *
 * The merged intervals found so far.
 *
 * It also contains the current merged interval.
 *
 *
 * =========================================================
 * FOR-EACH LOOP
 * =========================================================
 *
 * for (int[] interval : intervals)
 *
 * means:
 *
 * "For each int[] inside intervals,
 * temporarily call that inner array interval."
 *
 * Equivalent indexed loop:
 *
 * for (int i = 0; i < intervals.length; i++) {
 *     int[] interval = intervals[i];
 * }
 *
 *
 * =========================================================
 * IMPORTANT JAVA REFERENCE BEHAVIOR
 * =========================================================
 *
 * This line:
 *
 * int[] newInterval = intervals[0];
 *
 * does not copy the first interval.
 *
 * newInterval points to the same int[] object as
 * intervals[0].
 *
 *
 * Then:
 *
 * result.add(newInterval);
 *
 * adds a reference to that same array.
 *
 * Therefore:
 *
 * newInterval[1] = 6;
 *
 * changes the array that is already stored in result.
 *
 *
 * Conceptually:
 *
 * newInterval -----\
 *                   -> [1,6]
 * result.get(0) ---/
 *
 *
 * Reassigning the variable is different:
 *
 * newInterval = interval;
 *
 * This changes which array newInterval points to.
 *
 * It does not erase or modify the previous array
 * already stored in result.
 *
 *
 * =========================================================
 * INVARIANT
 * =========================================================
 *
 * After each iteration:
 *
 * result contains the correctly merged version of
 * every interval processed so far.
 *
 * newInterval points to the final interval in result.
 *
 * That final interval is the only one that might still
 * overlap with the next interval.
 *
 *
 * =========================================================
 * WHY ONLY COMPARE WITH THE CURRENT MERGED INTERVAL?
 * =========================================================
 *
 * Because the intervals are sorted by start.
 *
 * All earlier merged intervals are already finished.
 *
 * If the current interval does not overlap the last
 * merged interval, it cannot overlap an even earlier one.
 *
 *
 * =========================================================
 * ALGORITHM RHYTHM
 * =========================================================
 *
 * Sort
 *   ->
 * Start one merged group
 *   ->
 * Look at next interval
 *   ->
 * Overlap?
 *
 * YES:
 * Extend current merged group
 *
 * NO:
 * Start a new merged group
 *
 *
 * =========================================================
 * SAFE DECISION
 * =========================================================
 *
 * Because intervals are sorted:
 *
 * If the next start is greater than the current merged end,
 * the current merged interval is permanently finished.
 *
 * It can safely remain in the result.
 *
 *
 * =========================================================
 * COMPLEXITY
 * =========================================================
 *
 * Sorting:
 *
 * O(n log n)
 *
 * Loop through intervals:
 *
 * O(n)
 *
 * Total time:
 *
 * O(n log n)
 *
 * The sorting step dominates.
 *
 *
 * Result space:
 *
 * O(n)
 *
 * In the worst case, none of the intervals overlap,
 * so every interval appears in the answer.
 *
 *
 * =========================================================
 * COMMON PITFALLS
 * =========================================================
 *
 * 1. Forgetting to sort first.
 *
 * 2. Sorting by the end instead of the start.
 *
 * 3. Using:
 *
 *      interval[0] < newInterval[1]
 *
 *    instead of:
 *
 *      interval[0] <= newInterval[1]
 *
 *    Touching intervals such as [1,4] and [4,5]
 *    are merged in this problem.
 *
 * 4. Replacing the end directly:
 *
 *      newInterval[1] = interval[1];
 *
 *    This can accidentally shrink the merged interval.
 *    Use Math.max().
 *
 * 5. Adding every overlapping interval to result.
 *
 *    When intervals overlap, modify the current interval.
 *    Only add a new interval when there is a gap.
 *
 * 6. Writing the edge case incorrectly.
 *
 *    Correct:
 *
 *      if (intervals.length <= 1)
 *
 *    Incorrect:
 *
 *      if (intervals.length >= 1)
 *
 *
 * =========================================================
 * INTERVIEW EXPLANATION
 * =========================================================
 *
 * "I first sort the intervals by their start values.
 * This places potentially overlapping intervals beside
 * each other.
 *
 * I keep a current merged interval. For each next interval,
 * I compare its start with the current merged ending.
 *
 * If the next start is less than or equal to the current
 * end, the intervals overlap, so I extend the ending using
 * Math.max().
 *
 * Otherwise, the current merged interval is complete, and
 * I begin a new group.
 *
 * Sorting takes O(n log n), and the scan takes O(n), so the
 * total runtime is O(n log n)."
 *
 *
 * =========================================================
 * ONE-LINE MEMORY RULE
 * =========================================================
 *
 * Sort by start, then either:
 *
 * EXTEND the current interval
 *
 * or
 *
 * START a new interval.
 */

/*
 * =========================================================
 * MERGE INTERVALS
 * =========================================================
 *
 * Recognition:
 *
 * - overlapping ranges
 * - meeting times
 * - appointment windows
 * - schedules
 * - numerical ranges
 * - combine connected segments
 *
 *
 * =========================================================
 * THE MAIN IDEA
 * =========================================================
 *
 * Sort intervals by their starting value.
 *
 * Build ONE merged interval at a time.
 *
 * For each next interval:
 *
 *     Can it join my current merged interval?
 *
 * YES:
 *     Extend the ending.
 *
 * NO:
 *     Finish the current interval forever.
 *     Start a new merged interval.
 *
 *
 * =========================================================
 * CENTRAL DECISION
 * =========================================================
 *
 * Does the next interval overlap?
 *
 * interval[0] <= currentMergedEnd
 *
 * YES:
 *     currentMergedEnd =
 *         Math.max(currentMergedEnd, intervalEnd);
 *
 * NO:
 *     Begin a brand new merged interval.
 *
 *
 * =========================================================
 * INVARIANT
 * =========================================================
 *
 * Every interval already inside the result is COMPLETE.
 *
 * The only interval that can still change is the
 * current merged interval.
 *
 *
 * =========================================================
 * HOW THIS DIFFERS FROM OTHER PATTERNS
 * =========================================================
 *
 * Sliding Window
 * ----------------
 *
 * Question:
 *
 *     "What makes me move LEFT?"
 *
 * One window is constantly growing and shrinking.
 *
 * Window becomes invalid
 *          ↓
 * Move LEFT until valid again.
 *
 *
 * Kadane's Algorithm
 * -------------------
 *
 * Question:
 *
 *     "Does my previous sum help or hurt the future?"
 *
 * Previous sum helps
 *          ↓
 * Keep it.
 *
 * Previous sum hurts
 *          ↓
 * Restart.
 *
 *
 * Prefix Sum + HashMap
 * ---------------------
 *
 * Question:
 *
 *     "Can I make a safe local decision?"
 *
 * YES
 *     Sliding Window
 *
 * NO
 *     Store history in a HashMap.
 *
 *
 * Heap
 * -----
 *
 * Question:
 *
 *     "Can I safely throw something away?"
 *
 * YES
 *     Remove smallest/largest.
 *
 * Heap always maintains the best k candidates.
 *
 *
 * Merge Intervals
 * ----------------
 *
 * Question:
 *
 *     "Can this interval join my current group?"
 *
 * YES
 *     Extend the current interval.
 *
 * NO
 *     Finish this group forever.
 *     Start another one.
 *
 *
 * =========================================================
 * MENTAL MODEL
 * =========================================================
 *
 * Sliding Window
 *
 *      One moving window
 *
 *      ←────────────→
 *
 *      grows
 *      shrinks
 *      keeps moving
 *
 *
 * Merge Intervals
 *
 *      Finished groups
 *
 *      [1------6]
 *
 *              [8-----10]
 *
 *                       [15-----20]
 *
 * Once a group is finished,
 * it is NEVER modified again.
 *
 *
 * =========================================================
 * INTERVIEW EXPLANATION
 * =========================================================
 *
 * "I first sort the intervals by their starting values.
 *
 * Then I maintain one current merged interval.
 *
 * For each next interval, I check whether its start
 * overlaps the current merged ending.
 *
 * If it overlaps, I extend the ending.
 *
 * Otherwise, I know the current interval is complete,
 * so I add it to my answer and begin a new merged
 * interval.
 *
 * Sorting takes O(n log n), scanning takes O(n),
 * so the overall runtime is O(n log n)."
 *
 *
 * =========================================================
 * MEMORY RULE
 * =========================================================
 *
 * Sort.
 *
 * Then repeatedly ask:
 *
 *     "Can this interval join my current group?"
 *
 * If yes:
 *     Extend.
 *
 * If no:
 *     Finish the current group forever.
 *     Start another.
 */