package shared.algos;

import shared.dataStructures.TreeNode;

public class TreeTraversal {
	public static void inOrderTraversal(TreeNode root) {
		if (root==null) {
			return;
		}
		
		//Traverse Left, then print value, then traverse right
		//https://www.youtube.com/watch?v=WZulSX1plzE
		
		inOrderTraversal(root.left);
		System.out.print(root.val+ " --> ");
		inOrderTraversal(root.right);
	}
	
	public static void preOrderTraversal(TreeNode root) {
		if (root==null) {
			return;
		}
		
		//Traverse Left, then print value, then traverse right
		//https://www.youtube.com/watch?v=WZulSX1plzE
		System.out.print(root.val+ " --> ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	public static void postOrderTraversal(TreeNode root) {
		if (root==null) {
			return;
		}
		
		//Traverse Left, then print value, then traverse right
		//https://www.youtube.com/watch?v=WZulSX1plzE
		
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(root.val+ " --> ");
	}
	
	
	
	public static void main(String[] args) {
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

        System.out.println("In-Order Traversal Result:");
        inOrderTraversal(root);
        System.out.println("null");
        
        System.out.println("Pre-Order Traversal Result:");
        preOrderTraversal(root);
        System.out.println("null");
        
        System.out.println("Post-Order Traversal Result:");
        postOrderTraversal(root);
        System.out.println("null");
	}
}
