package beforeOptimizing;

import java.util.ArrayList;
import java.util.List;

public class FindIfPathExistsGraph {
	
	/*
	 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
	 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] 
	 * denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at 
	 * most one edge, and no vertex has an edge to itself.

	   You want to determine if there is a valid path that exists from vertex source to vertex destination.

	   Given edges and the integers n, source, and destination, return true if there is a valid path from
	   source to destination, or false otherwise.
	   
	   EXAMPLE:
	   Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
	   Output: true
	   Explanation: There are two paths from vertex 0 to vertex 2:
		- 0 → 1 → 2
		- 0 → 2
	 */
	
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        //thinking we can do DFS
    	//Before implementing DFS, we need to turn these edges into an adjacency list.
    	
    	// Create our adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        // Initlialize an empty neighbor list for each island
        // Index 0 is used in this case and so we go to n islands so islands 0, 1, 2 for n=3 islands
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add bidirectional connections
        // Iterate through each edge
        for (int[] edge : edges) {
            // Add both directions of the connection
        	graph.get(edge[0]).add(edge[1]);
        	graph.get(edge[1]).add(edge[0]);
        }

        // Create our visited array
        // Track visited vertices
        boolean[] visited = new boolean[n];

        //Determine whether destination is reachable
//        for (int neighbor : graph.get(source)) {
//            if (!visited[neighbor]) {
            	return dfs(source, destination, graph, visited);
            	//we do source here, not neighbor
            	//because this is just calling dfs method with the source
            	//and then the dfs method does the neighbor traversal when it recursively calls DFS
//            }
            	//I originally put a for loop... see line 53
            	//The key is that we didn't actually remove the neighbor traversal. 
            	//We moved the responsibility for that traversal entirely into the DFS method.
            	
//            	1. You actually have two different methods doing two different jobs
//            	Method 1: validPath()
//            	Think of this as the setup method.
//
//            	It does three things:
//            	1. Builds the adjacency list.
//            	2. Creates the visited array.
//            	3. Tells DFS where to begin searching.
            	
//            	Method 2: dfs()
//            	This is the method that actually explores the graph.
//            	This is where your loop belongs. It's responsible for exploring neighbors, and 
//            	recursion allows it to keep exploring neighbors of neighbors.
            	
            	
            	//time and space complexity are both O(V + E) for this adjacency-list implementation, where V is the number of vertices and E is the number of edges.
//        }
    }
    
    //make helper method private
    public static boolean dfs( int current, int destination, List<List<Integer>> graph, boolean[] visited) {
    	//1. Check whether current == destination. BASE CASE
        if(current==destination) {
        	return true;
        }

        //2. Mark current as visited
        visited[current] = true;

        //3: Explore unvisited neighbors
        for (int neighbor : graph.get(current)){
        	if (!visited[neighbor]) {
                if(dfs(neighbor, destination, graph, visited)) {
                	return true;
                }
//               else {
//                	return false;
//                } //--> RECURSION!
                //NOT A PART OF IT BECAUSE what if we have other neighbors to explore? This would end it
        	}
        }
        //4: Return false if no path is found
        //because it means we searched all of the neighbors and no path exists
        return false; //it would actually go out here
        //it doesn't necessarily mean we've explored every vertex in the entire graph. 
        //It means we've explored every vertex reachable from our starting point without finding the destination
        
    }
    
    public static void main(String[] args) {
    	int[][] edges = {{0,1},{1,2},{2,0}};
    	System.out.println(validPath(3,edges,0, 2)); //should be true
    	
    	int[][] edges2 = {{0,1},{1,3},{2,0}};
    	System.out.println(validPath(3,edges,2,3)); //should be false
    }
    
//    This is the key concept:
//    	- false → Keep searching other possible paths.
//    	- true → We've found a path! Return immediately.
//    	- Exhausted all reachable paths → Return false.

}
