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
		//Think of this as: main index graph.get(x) --> get a node
		//And then the list that you get returned --> [1, 2, ...] are the neighbors of that node
		List<List<Integer>> graph = new ArrayList<>();

		// Initialize an empty neighbor list for each vertex
		//We start with []. We need to add the inner lists for the connections to each node
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
			//We do this O(V) times --> put n empty lists in it (Same as saying O(n) times in this case
		}

		// Add bidirectional connections
		// if we see [0,1]
		// Since this is bidirectional, we need to do [0->1] and [1->0]
		// If it wasn't, we'd just do [0->1]
		for (int[] edge : edges) { //iterating through the list of lists [[1,2],[3,4],[1,4]]
			//so for each inner list, get the index 0 node and index 1 node and vice versa
			//these are NOT values. They are positions in the inner list
			graph.get(edge[0]).add(edge[1]); 
			graph.get(edge[1]).add(edge[0]);
			
			//we iterate through O(E) edges
			
			//So. Building the adjacency list: O(V) + O(E) --> O(V+E)
//			Writing O(V + E) tells the interviewer:
//
//			"My algorithm performs work proportional to the number "
//			+ "of vertices AND the number of edges."
//
//		    Writing O(max(V, E)) is also mathematically valid, but 
//		    it hides that useful distinction
		}

		// Track visited vertices
		boolean[] visited = new boolean[n];
		//java automatically defaults everything to false
		//so everything right now is unvisited
		//and we can use each index as a direct mapping to the node we're checking
		//NOTE: could use a set if the node number isn't corresponding to the node itself 
			// ex: two nodes identified as 1093 and 1929. Would waste space

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
		visited[source] = true; //visited doesn't necessarily mean we've finished exploring a node. It means we've discovered it

		// 3. Continue while there are nodes to explore.
		//Stop when there are no more nodes to visit
		while (!stack.isEmpty()) { //O(V times in worst case)

			// Remove the most recently added node
			int current = stack.pop();

			// 4. Base case: destination found
			if (current == destination) {
				return true;
			}

			// 5. Explore neighbors
			for (int neighbor : graph.get(current)) {//this is happening for every neighbor
				//say we had [0,1][1,3] --> this is two Es
				//but we have 4 relationships: 0 -> 1, 1 -> 0, 1 -> 3, 3 -> 1
				//So we're visiting O(2E) neighbors or O(E)

				if (!visited[neighbor]) {
					visited[neighbor] = true;
					// mark vertices visited when pushing them onto the stack, so we don't push the
					// same vertex repeatedly
					stack.push(neighbor);
				}
			}
		}
//		Initialize adjacency list	O(V)
//		Populate adjacency list	O(2E)
//		DFS: process vertices	O(V)
//		DFS: examine neighbors	O(2E)
		//So the whole time complexity: O(2V+E+2E) --> O(V+3E) --> O(V+E)

		// 6. No path found
		return false;

		// Notice that we never call dfsIterative() from inside itself. We just keep
		// pushing and popping vertices until we find
		// the destination or run out of vertices to explore
	}
	
	
	//space complexity:
	//graph --> is a list of size n, but then has inner arrays with the arrays summing to 2E edges (E edges but bidirectional so 2*E)
	//stack --> in worst case, all n nodes/V vetices (same thing)
	//visited --> in worst case, all n nodes/V vetices (same thing)
	//So space is O(V+E)

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
