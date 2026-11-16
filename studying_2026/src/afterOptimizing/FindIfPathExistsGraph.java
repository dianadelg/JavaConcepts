package afterOptimizing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindIfPathExistsGraph {

	/*
	 * There is a bi-directional graph with n vertices, where each vertex is labeled
	 * from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D
	 * integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional
	 * edge between vertex ui and vertex vi. Every vertex pair is connected by at
	 * most one edge, and no vertex has an edge to itself.
	 * 
	 * You want to determine if there is a valid path that exists from vertex source
	 * to vertex destination.
	 * 
	 * Given edges and the integers n, source, and destination, return true if there
	 * is a valid path from source to destination, or false otherwise.
	 * 
	 * EXAMPLE: Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination =
	 * 2 Output: true Explanation: There are two paths from vertex 0 to vertex 2: -
	 * 0 → 1 → 2 - 0 → 2
	 */

	/*
	 * caveat for Java interviews: a recursive DFS can overflow the call stack on
	 * very deep graphs. An iterative DFS or BFS avoids that particular risk.
	 * 
	 * Recursive DFS: Each dfs() call creates a new Java call-stack frame. On a
	 * sufficiently deep graph, too many frames can cause StackOverflowError.
	 * 
	 * Iterative DFS: We use a Stack-like data structure ourselves. In modern Java,
	 * Deque<Integer> with ArrayDeque is preferred over the older Stack<Integer>
	 * class.
	 * 
	 * Both use LIFO: Last In, First Out.
	 */

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

		// Start iterative DFS -- THIS IS THE ONLY CHANGE FROM OUR RECURSIVE METHOD!!
		return dfsIterative(source, destination, graph, visited);
	}

	// make helper method private
	// and new method for iterative using stack
	private static boolean dfsIterative(int source, int destination, List<List<Integer>> graph, boolean[] visited) {

		// 1. Create our stack
		Deque<Integer> stack = new ArrayDeque<>();
		// stack stores vertices to explore

		// 2. Start searching from source
		stack.push(source);
		visited[source] = true;

		// 3. Continue while there are vertices to explore
		while (!stack.isEmpty()) {

			// Remove the most recently added vertex
			int current = stack.pop();

			// 4. Base case: destination found
			if (current == destination) {
				return true;
			}

			// 5. Explore neighbors
			for (int neighbor : graph.get(current)) {

				if (!visited[neighbor]) {
					visited[neighbor] = true;
					// mark vertices visited when pushing them onto the stack, so we don't push the
					// same vertex repeatedly
					stack.push(neighbor);
				}
			}
		}

		// 6. No path found
		return false;

		// Notice that we never call dfsIterative() from inside itself. We just keep
		// pushing and popping vertices until we find
		// the destination or run out of vertices to explore
	}

	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
		System.out.println(validPath(3, edges, 0, 2)); // should be true

		int[][] edges2 = { { 0, 1 }, { 1, 3 }, { 2, 0 } };
		System.out.println(validPath(3, edges2, 2, 3)); // should be false
	}

	// In recursive DFS, we explicitly define a base case, But we also have a
	// failure condition after exploring all of the neighbors

	// In iterative DFS, we don't technically have a recursive base case, because
	// we're not using recursion.
	//Instead, we have:
	//	- An early success condition: current == destination.
	//	- A loop termination condition: stack.isEmpty().
	//	- A failure return: return false after the loop.
	
	
	//Suppose the destination is found while other vertices are still on the stack.
	//Should we wait until the stack is empty before returning true?
	//No! We've already answered the question. Returning immediately avoids unnecessary work.

}
