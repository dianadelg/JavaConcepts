package shared.search;

import shared.dataStructures.TreeNode;

public class DFS {
	//Ahh so dfs are the recursive traversals
	
	 // ── Preorder: Root → Left → Right ──────────────────────────────
    public static void preorder(TreeNode node) {
        if (node == null) return;       // base case

        System.out.print(node.val + " "); // visit root FIRST
        preorder(node.left);              // then go left
        preorder(node.right);             // then go right
    }

    // ── Inorder: Left → Root → Right ───────────────────────────────
    public static void inorder(TreeNode node) {
        if (node == null) return;       // base case

        inorder(node.left);               // go all the way left first
        System.out.print(node.val + " "); // then visit root
        inorder(node.right);              // then go right
    }

    // ── Postorder: Left → Right → Root ─────────────────────────────
    public static void postorder(TreeNode node) {
        if (node == null) return;       // base case

        postorder(node.left);             // process left children first
        postorder(node.right);            // process right children first
        System.out.print(node.val + " "); // visit root LAST
    }

    public static void main(String[] args) {
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.print("Preorder:  ");
        preorder(root);   // → 1 2 4 5 3

        System.out.print("\nInorder:   ");
        inorder(root);    // → 4 2 5 1 3

        System.out.print("\nPostorder: ");
        postorder(root);  // → 4 5 2 3 1
    }

}
