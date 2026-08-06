package afterOptimizing;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

//		    You are given an m x n grid where each cell can have one of three values:

//			0 representing an empty cell,
//			1 representing a fresh orange, or
//			2 representing a rotten orange.
//			Every minute, any fresh orange that is 4-directionally adjacent to a 
//	        rotten orange becomes rotten.
//			Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.


	// Input: grid = [[2,1,1],
	// [1,1,0],
	// [0,1,1]] where 2 is rotting, 1 is fresh, 0 is none
	
	/* ALGO
	 * 
	 * Setup
			Create a queue that will store coordinates of rotten oranges.
			Scan every cell in the grid.
			If a cell contains a rotten orange, put its coordinate into the queue.
			If a cell contains a fresh orange, increase the fresh-orange count.
			
			All initially rotten oranges go into the queue before BFS begins because they all start spreading at the same time.
		
		Early case
			If there are no fresh oranges, return 0. Nothing needs to change.
		
		BFS
			While the queue is not empty and fresh oranges remain:
			Record the current queue size.
			That size tells us how many oranges are rotten at the start of this minute.
			Process exactly that many oranges.
			For each rotten orange in that minute:
			Remove its coordinate from the front of the queue.
			Check its four neighboring cells: up, down, left, and right.
			Ignore a neighbor if it is outside the grid.
			Ignore it if it is empty or already rotten.
			If it is fresh:
			change it from 1 to 2,
			decrease the fresh-orange count,
			add its coordinate to the queue.
			After all oranges from the current BFS layer have spread rot, increase minutes by one.
			Finish
		When BFS ends:
			If fresh oranges still remain, some were unreachable, so return -1.
	 * 
	 */

	public static int orangesRotting(int[][] grid) {
		Queue<int[]> queue = new LinkedList<>();

		int rows = grid.length;
		int cols = grid[0].length;
		int freshOranges = 0;

		/*
		 * Find every initial rotten orange.
		 *
		 * All of them enter the queue before BFS starts because they all spread rot
		 * simultaneously.
		 *
		 * Also count the fresh oranges so we can determine whether every fresh orange
		 * was eventually reached.
		 */
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {

				if (grid[row][col] == 2) {
					queue.offer(new int[] { row, col });
				} else if (grid[row][col] == 1) {
					freshOranges++;
				}
			}
		}

		// Nothing needs to rot.
		if (freshOranges == 0) {
			return 0;
		}

		int[][] directions = { { 1, 0 }, // down
				{ -1, 0 }, // up
				{ 0, 1 }, // right
				{ 0, -1 } // left
		};

		int minutes = 0;

		/*
		 * Each outer-loop iteration represents one minute.
		 *
		 * We only continue while: - there are rotten oranges available to spread rot,
		 * and - fresh oranges still remain.
		 */
		while (!queue.isEmpty() && freshOranges > 0) {
			int levelSize = queue.size();

			/*
			 * Process every orange that was rotten at the beginning of this minute.
			 *
			 * Any oranges added during this loop belong to the next minute.
			 */
			for (int i = 0; i < levelSize; i++) {
				int[] current = queue.poll();

				int row = current[0];
				int col = current[1];

				for (int[] direction : directions) {
					int newRow = row + direction[0];
					int newCol = col + direction[1];

					boolean inBounds = newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols;

					/*
					 * Only a fresh orange can become newly rotten.
					 *
					 * Empty cells and already-rotten oranges are ignored.
					 */
					if (inBounds && grid[newRow][newCol] == 1) {
						grid[newRow][newCol] = 2;
						freshOranges--;

						queue.offer(new int[] { newRow, newCol });
					}
				}
			}

			// One complete layer of spreading has occurred.
			minutes++;
		}

		/*
		 * If fresh oranges remain, they were unreachable.
		 */
		if (freshOranges > 0) {
		    return -1;
		}
		//otherwise return minutes
		return minutes;
	}

	// I think this is still happening iteratively. It's like we need all of the
	// rotting ones starting BFS in parallel
	// All initially rotten oranges start BFS in parallel. This is the multi-source
	// BFS part
	// and each BFS layer represents one minute, which is very similar to Binary
	// Tree Level Order traversal:

	/*
	 * Tree BFS: one queue layer = one tree level
	 * 
	 * Rotting Oranges: one queue layer = one minute
	 */

	/*
	 * Some notes: we do not need a visited set - once an orange rots, we already
	 * change it to be rotted, = 2. This acts as our visited marker because we later
	 * only process neighbors where their value == 1. So once a cell becomes 2, it
	 * cannot be added again. The nice part about grids is that it can record visited state itself via 1 and 2 (fresh vs rotted)
	 * and the 2 prevents from ever being added again
	 * 
	 * ALSO java arrays do not compare their contents in a
	 * hashset. So HashSet<int[]> treats items differently (as two different
	 * objects) even though their coordinates match. So this fails new int[] {1, 2}.equals(new int[] {1, 2})
	 * 
	 * simplest solution: track unvisited fresh oranges, and track already
	 * rotten/visited
	 * 
	 * Issue with my code was that I was not counting fresh oranges: - we want to
	 * add every rotten orange to the queue AND count every fresh orange WHY: so we
	 * can know whether every fresh orange was reached, and because a fresh orange
	 * may be isolated like [[2,0,1]] In this case, queue becomes empty but fresh
	 * orange never rots, so answer is -1
	 * 
	 * Another issue: empty queue DOES NOT always mean return zero. Consider [[1]]
	 * no rotting oranges so nothing in queue, but it is fresh, will never rot so
	 * return -1 Hence why we want to keep track of the number of fresh oranges. We
	 * can instead do where freshoranges == 0, return 0 instead of where queue ==
	 * empty
	 * 
	 * As discussed, minutes increase once per BFS level, not once per node since
	 * multiple oranges rot during the same minute. We can actually use the same
	 * levelSize concept from BFS
	 * 
	 * Instead of the four cases I had before, use a directions array (so do not
	 * have to check+1, -1, etc) Use int[][] directions = { {1,0} up {-1, 0} down
	 * {0, 1} right {0, -1} left }
	 * 
	 * and apply like int newRow = row + direction[0] newCol = col+direction[1]
	 * Doing this prevents boundary-check mistakes and removes repeated code
	 *
	 * 
	 */
	public static void main(String[] args) {
		int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println(orangesRotting(grid));
	}
	
	/*
	 * ============================================================
	 * ROTTING ORANGES
	 * MULTI-SOURCE BFS — NOTES / TAKEAWAYS / PATTERN
	 * ============================================================
	 *
	 *
	 * RECOGNITION
	 * -----------
	 * The problem gives:
	 *
	 *      - a GRID,
	 *      - spreading to neighboring cells,
	 *      - minimum time,
	 *      - all moves happen simultaneously.
	 *
	 * Think:
	 *
	 *      Multi-Source BFS
	 *
	 *
	 * WHY BFS?
	 * --------
	 * Every "wave" of rotten oranges spreads at the same time.
	 *
	 * We want to explore everything that can happen in:
	 *
	 *      Minute 1
	 *      Minute 2
	 *      Minute 3
	 *
	 * BFS naturally explores in layers.
	 *
	 *
	 * WHY MULTI-SOURCE BFS?
	 * ---------------------
	 * There is NOT just one starting node.
	 *
	 * Every rotten orange is already rotten at minute 0.
	 *
	 * Therefore:
	 *
	 *      Put ALL rotten oranges into the queue
	 *      BEFORE BFS starts.
	 *
	 * Example:
	 *
	 *      [2,1,1]
	 *      [1,1,1]
	 *      [1,1,2]
	 *
	 * Queue after setup:
	 *
	 *      [(0,0), (2,2)]
	 *
	 * Both oranges begin spreading at exactly the same time.
	 *
	 *
	 * WHERE DOES THE "PARALLEL" BEHAVIOR COME FROM?
	 * ---------------------------------------------
	 * BFS is NOT actually running on multiple threads.
	 *
	 * It SIMULATES everything happening simultaneously.
	 *
	 * This happens because:
	 *
	 * 1. ALL starting rotten oranges are added to ONE queue
	 *    before BFS begins.
	 *
	 * 2. We freeze the queue size:
	 *
	 *        int levelSize = queue.size();
	 *
	 *    before processing that minute.
	 *
	 * 3. We process exactly levelSize oranges.
	 *
	 * 4. Any newly rotten oranges are added to the BACK
	 *    of the queue.
	 *
	 * 5. Those newly rotten oranges DO NOT spread until
	 *    the NEXT outer-loop iteration.
	 *
	 * This is exactly what makes the spread happen
	 * "in parallel."
	 *
	 *
	 * WHY levelSize?
	 * --------------
	 * levelSize freezes the number of oranges that were
	 * already rotten at the START of this minute.
	 *
	 * Example:
	 *
	 * Queue:
	 *
	 *      [(0,0), (2,2)]
	 *
	 * levelSize = 2
	 *
	 * During processing we may add:
	 *
	 *      [(1,0), (0,1), (2,1)]
	 *
	 * But those oranges became rotten DURING this minute.
	 *
	 * They are NOT allowed to spread until the next minute.
	 *
	 *
	 * One queue layer
	 * =
	 * One minute.
	 *
	 *
	 * WHY DON'T WE PROCESS NEWLY ADDED ORANGES IMMEDIATELY?
	 * -----------------------------------------------------
	 * Because they weren't rotten at the beginning
	 * of the current minute.
	 *
	 * They only became rotten because another orange
	 * infected them.
	 *
	 * They must wait until the next minute before
	 * spreading further.
	 *
	 *
	 * WHAT DOES THE QUEUE STORE?
	 * --------------------------
	 * The queue stores coordinates:
	 *
	 *      int[] {row, col}
	 *
	 * NOT orange values.
	 *
	 * Example:
	 *
	 *      queue = [(0,0), (2,1)]
	 *
	 * Each coordinate tells us:
	 *
	 *      "Process the orange located here."
	 *
	 *
	 * WHY DON'T WE NEED A VISITED SET?
	 * --------------------------------
	 * The grid itself becomes our visited structure.
	 *
	 * Fresh / Unvisited:
	 *
	 *      grid[row][col] == 1
	 *
	 * Rotten / Already Visited:
	 *
	 *      grid[row][col] == 2
	 *
	 * Once a fresh orange becomes rotten:
	 *
	 *      grid[row][col] = 2;
	 *
	 * it can never be added again.
	 *
	 *
	 * WHY HashSet<int[]> DOESN'T WORK HERE
	 * ------------------------------------
	 * Arrays are objects.
	 *
	 * These are DIFFERENT objects:
	 *
	 *      new int[] {1,2}
	 *
	 *      new int[] {1,2}
	 *
	 * Even though they contain the same numbers,
	 * Java considers them different because they
	 * occupy different memory locations.
	 *
	 * Therefore:
	 *
	 *      HashSet<int[]>
	 *
	 * does NOT compare coordinates by value.
	 *
	 * Fortunately, this problem doesn't need a
	 * visited set because changing:
	 *
	 *      1 -> 2
	 *
	 * already marks a cell as visited.
	 *
	 *
	 * WHY COUNT FRESH ORANGES?
	 * ------------------------
	 * We must know whether every fresh orange
	 * eventually becomes rotten.
	 *
	 * Each time one rots:
	 *
	 *      freshOranges--;
	 *
	 * At the end:
	 *
	 *      freshOranges == 0
	 *          return minutes
	 *
	 *      freshOranges > 0
	 *          return -1
	 *
	 * because some oranges were unreachable.
	 *
	 *
	 * EMPTY QUEUE DOES NOT MEAN SUCCESS
	 * ---------------------------------
	 * Example:
	 *
	 *      [1]
	 *
	 * Queue:
	 *
	 *      empty
	 *
	 * Fresh oranges:
	 *
	 *      1
	 *
	 * No rotten orange can ever spread.
	 *
	 * Therefore:
	 *
	 *      return -1
	 *
	 *
	 * FINDING NEIGHBORS
	 * -----------------
	 * Instead of writing four separate if-statements,
	 * store the four possible movements.
	 *
	 *      {1,0}   down
	 *      {-1,0}  up
	 *      {0,1}   right
	 *      {0,-1}  left
	 *
	 * For each movement:
	 *
	 *      newRow = row + direction[0]
	 *      newCol = col + direction[1]
	 *
	 * Think:
	 *
	 *      "Start at my current position,
	 *       then move one square."
	 *
	 *
	 * BOUNDS CHECK
	 * ------------
	 * Always verify:
	 *
	 *      newRow >= 0
	 *      newRow < rows
	 *
	 *      newCol >= 0
	 *      newCol < cols
	 *
	 * Remember:
	 *
	 *      Index 0 IS valid.
	 *
	 *
	 * PLAIN-ENGLISH ALGORITHM
	 * -----------------------
	 * 1. Scan the entire grid.
	 *
	 * 2. Put EVERY rotten orange into the queue.
	 *
	 * 3. Count every fresh orange.
	 *
	 * 4. If there are no fresh oranges,
	 *    return 0.
	 *
	 * 5. While the queue isn't empty
	 *    AND fresh oranges remain:
	 *
	 *      - Freeze the queue size.
	 *
	 *      - Process exactly those oranges.
	 *
	 *      - For each orange:
	 *
	 *          Check its four neighbors.
	 *
	 *          If a neighbor is fresh:
	 *
	 *              Make it rotten.
	 *
	 *              Decrease fresh count.
	 *
	 *              Add its coordinates to the queue.
	 *
	 *      - One minute has passed.
	 *
	 * 6. If fresh oranges remain:
	 *
	 *      return -1
	 *
	 *    Otherwise:
	 *
	 *      return minutes.
	 *
	 *
	 * TREE BFS CONNECTION
	 * -------------------
	 * Binary Tree Level Order:
	 *
	 *      One queue layer
	 *      =
	 *      One tree level.
	 *
	 * Rotting Oranges:
	 *
	 *      One queue layer
	 *      =
	 *      One minute.
	 *
	 *
	 * EVERY-ITERATION DECISION
	 * ------------------------
	 * The algorithm asks:
	 *
	 *      "Which oranges were already rotten
	 *       at the START of this minute?"
	 *
	 * Those oranges get exactly ONE chance
	 * to spread.
	 *
	 * Newly rotten oranges must wait until
	 * the next minute.
	 *
	 *
	 * MEMORY RULE
	 * -----------
	 * Multi-source BFS:
	 *
	 *      Put ALL starting sources
	 *      into ONE queue.
	 *
	 * Freeze queue size.
	 *
	 * Process exactly that layer.
	 *
	 * New neighbors wait until
	 * the next layer.
	 *
	 * One queue layer
	 * =
	 * One minute.
	 *
	 *
	 * RUNTIME
	 * -------
	 * Every cell is visited at most once.
	 *
	 * Each visit checks four neighbors.
	 *
	 * Time:
	 *
	 *      O(rows × cols)
	 *
	 *
	 * SPACE
	 * -----
	 * In the worst case, the queue may contain
	 * nearly every cell.
	 *
	 * Space:
	 *
	 *      O(rows × cols)
	 */

}
