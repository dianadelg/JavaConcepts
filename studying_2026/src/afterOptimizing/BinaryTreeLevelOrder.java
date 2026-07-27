package afterOptimizing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import shared.dataStructures.TreeNode;

public class BinaryTreeLevelOrder {

//	Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

	/*
	 * Example: Input: root = [3,9,20,null,null,15,7] Output: [[3],[9,20],[15,7]]
	 */

	/*
	 * BFS: because it's LEVEL, not depth Do I naturally explore one branch
	 * completely first? (DFS) Or Do I explore everything on one level first? (BFS)
	 * That's literally the distinction between DFS and BFS.
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
		/*
		 * Recognition:
		 *
		 * The problem says LEVEL ORDER.
		 *
		 * DFS explores one branch deeply. BFS explores every node on one level before
		 * moving to the next.
		 *
		 * Therefore: Level by level -> BFS -> Queue
		 */

		// Stores the final list of levels.
		List<List<Integer>> traversal = new ArrayList<>();

		// An empty tree has no levels.
		if (root == null) {
			return traversal;
		}

		// Stores nodes waiting to be processed.
		Queue<TreeNode> queue = new LinkedList<>(); //queue maintains FIFO
	    /*
	     * Queue is used because BFS processes nodes in FIFO order:
	     * first added = first processed.
	     *
	     * Older nodes from the current level stay in front.
	     * Newly discovered children go to the back.
	     */

		// Start BFS with the root.
		queue.offer(root); 

		while (!queue.isEmpty()) {

			/*
			 * Freeze the number of nodes currently in the queue. These nodes belong to the
			 * current level.
			 *
			 * Children added during this loop belong to the next level.
			 */
			int levelSize = queue.size();

			// Stores only the values from the current level.
			List<Integer> currentLevel = new ArrayList<>();
			//is this the inner list of the levels that will get added to the master list

			for (int i = 0; i < levelSize; i++) {

				// Remove the next node from the front of the queue.
				TreeNode current = queue.poll();
				
	            /*
	             * This value belongs to the current level,
	             * so it goes into currentLevel.
	             */

				// Record its value in the current level.
				currentLevel.add(current.val);
				
	            /*
	             * These children belong to the next level.
	             *
	             * We are NOT adding them to currentLevel.
	             * We are putting them in the queue so they can be processed later.
	             */

				// Add existing children to the back of the queue.
				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}
			
	        /*
	         * The entire current level is complete.
	         * Add the inner list to the master list.
	         */

			// Save the completed level after processing every node in it.
			traversal.add(currentLevel);
		}

		return traversal;
	}

	public static void main(String[] args) {
		// Constructing a sample binary tree:
		// 2
		// / \
		// 8 4
		// / \ \
		// 3 7 1

		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(8);
		root.right = new TreeNode(4);

		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);

		root.right.right = new TreeNode(1);

		List<List<Integer>> result = levelOrder(root);
		System.out.println("Level-Order Traversal Result:");
		System.out.println(result);
	}
	
	//	INNER LOOP
	//	Processes individual nodes in one level.
	//
	//	OUTER LOOP
	//	Moves from one completed level to the next.
	
	/*
	 * ================================
	 * TAKEAWAYS / MENTAL MODEL
	 * ================================
	 *
	 * RECOGNITION
	 * -----------
	 * - The problem says "LEVEL ORDER."
	 * - Ask yourself:
	 *      "Am I visiting one level at a time, or one branch at a time?"
	 * - One branch completely -> DFS
	 * - One level completely -> BFS
	 * - Therefore:
	 *      Level Order -> BFS -> Queue
	 *
	 *
	 * WHY A QUEUE?
	 * ------------
	 * - A Queue is FIFO (First In, First Out).
	 * - The first node discovered should be the first node processed.
	 * - Current-level nodes stay at the front.
	 * - Newly discovered children are added to the back.
	 * - This naturally processes nodes level by level.
	 *
	 * Queue stores TreeNodes, NOT values.
	 * We need access to:
	 *      current.val
	 *      current.left
	 *      current.right
	 *
	 *
	 * WHAT EACH DATA STRUCTURE DOES
	 * -----------------------------
	 * Queue<TreeNode>
	 *      Stores nodes waiting to be processed.
	 *
	 * List<Integer> currentLevel
	 *      Stores the values from ONE level.
	 *
	 * List<List<Integer>> traversal
	 *      Stores every completed level.
	 *
	 *
	 * WHY DO WE CAPTURE queue.size()?
	 * -------------------------------
	 * int levelSize = queue.size();
	 *
	 * This freezes how many nodes belong to the current level BEFORE
	 * we start adding children.
	 *
	 * Even if new children are added while processing,
	 * they belong to the NEXT level.
	 *
	 * Example:
	 *
	 * Queue:
	 *      [3,4]
	 *
	 * levelSize = 2
	 *
	 * While processing:
	 *
	 * remove 3
	 * add 7
	 *
	 * Queue becomes:
	 *      [4,7]
	 *
	 * We DO NOT process 7 yet because
	 * levelSize was already frozen at 2.
	 *
	 *
	 * WHY DO WE CHECK current.left / current.right?
	 * ---------------------------------------------
	 * We are NOT adding children to the current level.
	 *
	 * We are scheduling them for future processing.
	 *
	 * currentLevel stores values for THIS level.
	 *
	 * queue stores nodes for FUTURE levels.
	 *
	 * Example:
	 *
	 * currentLevel = [3]
	 *
	 * queue = [4]
	 *
	 * current.left = 7
	 *
	 * queue becomes:
	 *      [4,7]
	 *
	 * 7 waits until the next outer iteration.
	 *
	 *
	 * OUTER LOOP vs INNER LOOP
	 * ------------------------
	 *
	 * while (!queue.isEmpty())
	 *
	 *      Processes ONE ENTIRE LEVEL.
	 *
	 * for (levelSize)
	 *
	 *      Processes every node ON THAT LEVEL.
	 *
	 *
	 * BFS INVARIANT
	 * -------------
	 * At the start of every while-loop,
	 * every node currently in the queue belongs to the level
	 * we are about to process.
	 *
	 *
	 * MEMORY RULE
	 * -----------
	 * Queue holds TreeNodes waiting to be visited.
	 *
	 * currentLevel holds values for ONE level.
	 *
	 * traversal holds ALL completed levels.
	 *
	 *
	 * MY PLAIN ENGLISH ALGORITHM
	 * --------------------------
	 *
	 * 1. If the tree is empty, return [].
	 *
	 * 2. Put the root node into the queue.
	 *
	 * 3. While the queue isn't empty:
	 *
	 *      - Capture the current queue size.
	 *        This tells us exactly how many nodes belong
	 *        to this level.
	 *
	 *      - Create a new currentLevel list.
	 *
	 *      - Repeat levelSize times:
	 *
	 *          Remove one node from the front.
	 *
	 *          Add its value to currentLevel.
	 *
	 *          If its left child exists,
	 *          add that NODE to the queue.
	 *
	 *          If its right child exists,
	 *          add that NODE to the queue.
	 *
	 *      - Once we've processed levelSize nodes,
	 *        we've finished this level.
	 *
	 *      - Add currentLevel to traversal.
	 *
	 * 4. Repeat until the queue is empty.
	 *
	 *
	 * BIGGEST REALIZATION
	 * -------------------
	 * We DO NOT follow every left child downward.
	 *
	 * We ONLY add the immediate children of the node
	 * currently being processed.
	 *
	 * Those children wait in the queue until every node
	 * on the current level has been processed.
	 *
	 * That's why BFS visits:
	 *
	 * Level 1
	 * ↓
	 * Level 2
	 * ↓
	 * Level 3
	 *
	 * instead of following one branch all the way down.
	 */
	
}
