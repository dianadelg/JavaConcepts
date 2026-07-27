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
		Queue<TreeNode> queue = new LinkedList<>();

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

			for (int i = 0; i < levelSize; i++) {

				// Remove the next node from the front of the queue.
				TreeNode current = queue.poll();

				// Record its value in the current level.
				currentLevel.add(current.val);

				// Add existing children to the back of the queue.
				if (current.left != null) {
					queue.offer(current.left);
				}

				if (current.right != null) {
					queue.offer(current.right);
				}
			}

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
}
