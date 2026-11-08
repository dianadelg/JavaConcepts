package shared.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Graphs {
	
	/*
	 * A graph is a collection of:
	 * 1. Vertices (nodes) -- the objects we're representing like islands
	 * 2. Edges -- the connections between objects. Like routes
	 * 
	 * Undirected Graphs -- Node A connects to Node B, and Node B connects to Node A
	 * 		Think of this like friends on Facebook -- if you add me, you and I are
	 * 		friends of each other
	 * Directed Graphs -- Node A points to B but B doesn't necessarily point to A
	 * 		Think of this like followers on Instagram. I follow Noah Kahan but he
	 * 		doesn't necessarily follow back (sadly)
	 * 
	 */
	
	/*
	 * A graph is a concept, BUT Java needs a way to store it in memory
	 * Adjacency list -- stores each node alongside its directly connected neighbors
	 * 
	 * So consider these routes
	 *  1 ↔ 2
		1 ↔ 3
		2 ↔ 4
		3 ↔ 5
		4 ↔ 6
		5 ↔ 6
		
		We can represent it like:
		Island 1    neighbors [2,3]
		Island 2    neighbors [1,4]
		Island 3    neighbors [1,5]...
	 */
	
	//This can be stored as
	
	public static void AdjList(int N) {
		//where N is the number of islands
		List<List<Integer>> graph = new ArrayList<>();
		//so a list of integer lists
		//where island 1 is represented as index 1, and the List<Integer> is the neighbors of island 1
		
		// Index 0 is unused because islands start at 1.
		for (int i = 0; i <= N; i++) {
		    graph.add(new ArrayList<>());
		}
		
		//Since bidirectional, here's how we can add the relationships
		
		//go to island 1's list of neighbors and add island 2
		//represents 1 <-> 2
		graph.get(1).add(2);
		
		//go to island 2's list of neighbors and add island 1
		//represents 1 <-> 3
		graph.get(2).add(1);
		
		//If not bidirectional, we would not need to add both
		
		
		
		/*Now we need our visited set -- because bidirectional, without tracking visiting nodes
		  a recursive traversal could have us going to island 1, then island 2, then
		  1, then 2, infinitely
		  
		  So to prevent this, we track which islands we've already explored
		*/
		
		boolean[] visited = new boolean[N+1];
		//we need N+1 because arrays start at 0 but we're corresponding index 
		//with island number. So we skip 0. And need 1, 2, 3, 4, 5, 6 (or N+1 indices)
		// also, java automatically initializes boolean array elements to false
		//so it'sl ike, all are unvisited
		
		/*
		 * When DFS first reaches island 2, we mark as visited
		 * And then if another route leads to island 2, we check if visited 
		 */
		
		//when we visit island 2, for example:
		visited[2] = true;
		
		//before exploring it again:
		if(!visited[2]) {
			//explore island2
		}
		
		/*
		 * Another option is using a HashSet<Integer>
		 * But using the array gives us the advantage of using the island number
		 * as an index
		 * 
		 * Set<Integer> visited = new HashSet<>();

			// Visit Island 3
			visited.add(3);
			
			// Check whether we've visited Island 3
			if (!visited.contains(3)) {
			    // Explore Island 3
				}
				
			We could use this when islands have arbitary identifiers like
			103030, 13284, 339028 OR strings like Maco Island, Nantucket, Catalina Island
			Here, an array index would waste a lot of memory, and the hash set only
			stores the IDs we actually visit
			
			Both structures provide constant-time membership checks in typical use, but:
			- A boolean array has guaranteed O(1) indexed access and no hashing overhead.
			- A HashSet has average O(1) lookup and insertion, but requires hashing and more memory per entry.
		 */
		
		//Now look at DFS

	}
	
	public static void main(String[] args) {

        // We have 4 islands, numbered 1 through 4
        int n = 4;

        // Create our adjacency list
        List<List<Integer>> graph = new ArrayList<>();

        // Create an empty neighbor list for each island
        // Index 0 is unused
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        // Add bidirectional connections
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 2, 4);

        // Create our visited array
        boolean[] visited = new boolean[n + 1];

        // Start DFS at Island 1
        dfs(1, graph, visited);
    }

    // Adds a bidirectional connection between two islands
    public static void addEdge(
    	//used to make code cleaner but this is the graph.get(1).add(2) piece
        List<List<Integer>> graph,
        int island1,
        int island2
    ) {
        graph.get(island1).add(island2);
        graph.get(island2).add(island1);
    }

    // Depth-First Search
    public static void dfs(
        int island,
        List<List<Integer>> graph,
        boolean[] visited
    ) {

        // Mark the current island as visited
        visited[island] = true;

        // Print the island we're visiting
        System.out.println("Visiting island: " + island);

        // Loop through the current island's neighbors
        for (int neighbor : graph.get(island)) {
        	//for each neighbor in the current island's list of neighbors, execute
        	//the code inside the loop
        	
            // Only explore unvisited neighbors
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited); //--> RECURSION!
            }
        }
    }
	

}
