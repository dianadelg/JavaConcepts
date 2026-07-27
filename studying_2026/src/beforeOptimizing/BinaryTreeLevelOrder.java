package beforeOptimizing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import shared.dataStructures.TreeNode;

public class BinaryTreeLevelOrder {
	
//	Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

	/*
	 * Example:
	 * Input: root = [3,9,20,null,null,15,7]
	   Output: [[3],[9,20],[15,7]]
	 */
	
	
	/*
	 * BFS: because it's LEVEL, not depth
	 * Do I naturally explore one branch completely first? (DFS) Or 
	 * Do I explore everything on one level first? (BFS)
	 * That's literally the distinction between DFS and BFS.
	 */
	
	/* Had to get help with the algo
	 * If tree is empty

		Return []
		
		Put root in queue
		
		While queue isn't empty
		
		    levelSize = queue.size()
		
		    Create new list
		
		    Repeat levelSize times
		
		        Remove one node
		
		        Add value to current level
		
		        Add left child
		
		        Add right child
		
		    Save current level
	 */
    public static List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> traversal = new ArrayList<>(); //this is the result list
    	Queue<TreeNode> queue = new LinkedList<>();
    	
    	//can have an empty tree (look at constraints)
    	if(root == null) {
    		return traversal;
    	}
    	
    	if(root.left == null && root.right == null) {
    		//means this is the only node
    		traversal.get(0).add(root.val);
    		return traversal;
    	}
    	
    	//put root in queue
    	queue.add(root);
    	
    	while(!queue.isEmpty()) {
    		int levelSize = queue.size();
    		
    		List<Integer> currentLevel = new ArrayList<>(); //this list is only for the current level
    		for(int i=0; i<levelSize; i++) {
    			TreeNode current = queue.poll();
    			currentLevel.add(current.val); //grab the value, and then check the children
    			queue.offer(current.left);
    			queue.offer(current.right);
    			traversal.add(currentLevel);
    		}
    	}
    	
    	//else means at least 2 nodes
    	return null;
    }
    
    public static void main (String[] args) {
    	// Constructing a sample binary tree:
        //          2
        //        /   \
        //       8     4
        //      / \     \
        //     3   7     1
        
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
    
//    Prompt says...	Think...
//    Level order	BFS + Queue
//    Shortest path (unweighted)	BFS
//    Closest / nearest node	BFS
//    Process one level at a time	BFS
//    Visit root before children	Preorder
//    Sorted order in a BST	Inorder
//    Delete/free/evaluate children before parent	Postorder
//    Explore one branch completely	DFS
}
