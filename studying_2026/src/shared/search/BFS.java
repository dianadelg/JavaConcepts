package shared.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * BFS Algo in plain English:
 * Setup: Create an empty queue and a vi
 * sited list to keep track of nodes.
 * Start: Choose a starting node, mark it as visited, and put it into the queue.
 * Repeat: While the queue is not empty, take the first node out of the queue.
 * Explore: Look at all neighbor nodes of this current node. If a neighbor has not been 
 * 			visited yet, mark it as visited and put it into the back of the queue.
 * Finish: Stop when the queue is completely empty.
 */

public class BFS {
	public static void bfs(int start, Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
         // Example graph:
         // 1 - 2
         // 1 - 3
         // 2 - 4
         // 3 - 4
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(1, 4));
        graph.put(3, Arrays.asList(1, 4));
        graph.put(4, Arrays.asList(2, 3));

        bfs(1, graph);  // → 1 2 3 4
    }
    
    /*
     * BFS (Breadth First Search) Complexity
     * --------------------------------------
     * Time:
     *   Best:    O(1)      - start node is the target
     *   Average: O(V + E)  - visits some vertices and edges before finding target
     *   Worst:   O(V + E)  - visits every vertex (V) and every edge (E)
     *
     * Space:     O(V)      - queue and visited set hold at most V vertices
     */
    
    /*
     * When to Use BFS
     * ---------------
     * Best times to use:
     *   - Shortest path on unweighted graph  - BFS guarantees shortest path
     *   - Target is close to start node      - explores nearest nodes first
     *   - Level-by-level traversal needed    - naturally processes nodes layer by layer
     *
     * Worst times to use:
     *   - Memory is tight                    - queue can hold O(V) nodes at once
     *   - Graph is very wide                 - wide graphs balloon the queue size fast
     *   - Weighted shortest path needed      - use Dijkstra's instead
     */
}
