package shared.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IterativeDFSGraph {
	
	//The iterative DFS mental checklist
	//1. Create a stack to track vertices we need to explore.
	//2. Push the source onto the stack and mark it visited.
	//3. While the stack isn't empty, keep searching.
	//4. Pop a vertex and check if it's our destination. If yes, return true.
	//5. Explore its neighbors — if unvisited, mark visited and push onto the stack.
	//6. Return false if the stack empties without finding the destination.
	
	
	//first, we must build the graph
	public static boolean validPath(int n, int[][] edges, int source, int destination) {

	    // Create adjacency list
	    List<List<Integer>> graph = new ArrayList<>();

	    // Initialize an empty neighbor list for each vertex
	    for (int i = 0; i < n; i++) {
	        graph.add(new ArrayList<>());
	    }

	    // Add bidirectional connections
	    for (int[] edge : edges) {
	        graph.get(edge[0]).add(edge[1]);
	        graph.get(edge[1]).add(edge[0]);
	    }

	    // Track visited vertices
	    boolean[] visited = new boolean[n];

	    // Start iterative DFS
	    return dfsIterative(source, destination, graph, visited);
	}
	
	public static boolean dfsIterative(int source,
	        int destination,
	        List<List<Integer>> graph,
	        boolean[] visited) {
		// 1. STACK
		Deque<Integer> stack = new ArrayDeque<>();

		// 2. PUSH SOURCE
		stack.push(source);
		visited[source] = true;

		// 3. WHILE stack isn't empty
		while (!stack.isEmpty()) {

		    // 4. POP and CHECK
		    int current = stack.pop();

		    if (current == destination) {
		        return true;
		    }

		    // 5. NEIGHBORS
		    for (int neighbor : graph.get(current)) {
		        if (!visited[neighbor]) {
		            visited[neighbor] = true;
		            stack.push(neighbor);
		        }
		    }
		}

		// 6. NOTHING FOUND
		return false;
	}
	
	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
		System.out.println(validPath(3, edges, 0, 2)); // should be true

		System.out.println(validPath(3, edges, 2, 3)); // should be false
	}

}
