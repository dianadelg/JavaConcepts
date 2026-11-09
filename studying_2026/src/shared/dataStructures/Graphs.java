package shared.dataStructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    // Depth-First Search - Explore one path as deeply as possible before backtracking
    // DFS typically uses recursion (the call stack)
    public static void dfs(int island, List<List<Integer>> graph, boolean[] visited){

        // Mark the current island as visited
        visited[island] = true;

        // Print the island we're visiting
        System.out.println("Visiting island: " + island);

        // Loop through the current island's neighbors
//        Let's translate this line by line:
//
//        Line 1: Look at every neighbor of our current island.
//
//        Line 2: Check whether we've already explored that neighbor.
//
//        Line 3: If not, explore that neighbor using the same map and checklist.
        for (int neighbor : graph.get(island)) {
        	//for each neighbor in the current island's list of neighbors, execute
        	//graph.get(island) — Retrieves the list of neighbors for our current island
        	//so for each neighbor in the current island's list of neighbors
        	
        	//the code inside the loop
        	
            // Only explore unvisited neighbors
        	// AND explore immediately
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited); //--> RECURSION!
                //note we use neighbor instead of island (the current one we're on)
                //Explore this new neighbor island, but keep using the same map and checklist
                //REMEMBER:
//                neighbor → Where am I going next?
//         		  graph → What connections exist?
//           	  visited → Where have I already been?
            }
        }
    }
    
    // BFS - Explore all immediate neighbors first, then their neighbors, moving outward one level at a time.
    // BFS typically uses a queue - FIFO
    // Can write queue like: Queue<Integer> queue = new ArrayDeque<>();
    public static void bfs(int island, List<List<Integer>> graph, boolean[] visited) {
    // We'll use the same graph and visited array from our DFS exercise
    	//with BFS, we generally mark an island as visited when we add it to the queue, rather than when we remove it. 
    	//This prevents the same island from being added multiple times
    
    	//1. Create queue
    	Queue<Integer> queue = new ArrayDeque<>();
    	
    	//2. Mark starting island as visited
    	visited[island] = true;
    	
    	//3. Add that island to queue
    	queue.add(island);
    	
    	//4. Keep exploring while the queue isn't empty
    	//So this is where DFS would use recursion, here we 
    	//use a loop that continues as long as there are islands waiting in our queue
    
    	while(!queue.isEmpty()) {
            // 5. Get (remove) the next island from the queue and save it
    		int currentIsland = queue.poll();
    	    //removes and returns the element at the front of the queue
//    		Notice something important: we now have two island variables
//    		- island — The original starting island passed into bfs()
//    		- currentIsland — The island we're currently processing from the queue

            // 6. Explore its neighbors
    		for(int neighbor: graph.get(currentIsland)) {
                //Check whether neighbor is unvisited
    			

    			if(!visited[neighbor]) {
    				//if not visited, mark as visited and then add it to our queue
    				//so we can EXLPORE LATER
    				visited[neighbor]=true;
    	    		// 7. Add unvisited neighbors to the queue
    				queue.add(neighbor);
    			}
    			
    		}
    	}
    	
    	//One additional consideration: recursive DFS can cause a stack overflow 
    	//on extremely deep graphs, so iterative DFS or BFS may be safer when the 
    	//graph is very large.
    }
	

}
