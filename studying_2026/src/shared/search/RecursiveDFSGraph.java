package shared.search;

import java.util.ArrayList;
import java.util.List;

public class RecursiveDFSGraph {

//	The Recursive DFS Mental Checklist
//	1. CHECK — If the current vertex is our destination, return true.
//	2. MARK — Mark the current vertex as visited.
//	3. NEIGHBORS — Loop through the current vertex's neighbors.
//	4. RECURSE — For each unvisited neighbor, recursively call DFS.
//	5. RETURN TRUE — If any recursive call finds the destination, return true.
//	6. RETURN FALSE — If all reachable paths have been explored without success, return false.
	
	
	// important interview detail: recursive DFS can cause a StackOverflowError on very deep graphs in 
	// Java, whereas iterative DFS avoids that particular risk.
	// FOR THIS REASON, focus on iterative DFS for interviews

	// First, we must build the graph
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

		// Start recursive DFS
		return dfsRecursive(source, destination, graph, visited);
	}

	// Recursive DFS helper
	public static boolean dfsRecursive(int current, int destination, List<List<Integer>> graph, boolean[] visited) {

		// 1. CHECK
		if (current == destination) {
			return true;
		}

		// 2. MARK
		visited[current] = true;

		// 3. NEIGHBORS
		for (int neighbor : graph.get(current)) {

			// 4. RECURSE (only if unvisited)
			if (!visited[neighbor]) {

				// 5. RETURN TRUE if a path was found
				if (dfsRecursive(neighbor, destination, graph, visited)) {
					return true;
				}
			}
		}

		// 6. RETURN FALSE if no path was found
		return false;
	}
	
	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
		System.out.println(validPath(3, edges, 0, 2)); // should be true

		int[][] edges2 = { { 0, 1 }, { 1, 3 }, { 2, 0 } };
		System.out.println(validPath(3, edges, 2, 3)); // should be false
	}
	

}
