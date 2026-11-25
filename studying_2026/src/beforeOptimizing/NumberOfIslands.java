package beforeOptimizing;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {
	
	/*
	 * Given an m x n two-dimensional grid containing '1' (land) 
	 * and '0' (water), return the number of islands.
	 * 
	 * An island is surrounded by water and is formed by connecting 
	 * adjacent land cells horizontally or vertically. Diagonal 
	 * connections do not count.
	 * 
	 * You may assume all four edges of the grid are surrounded by 
	 * water.
	 * 
	 * Constraints
		- m == grid.length
		- n == grid[i].length
		- 1 ≤ m, n ≤ 300
		- grid[i][j] is either '0' or '1'.
	 */
	
	/*Observations*
	 * 
	 * Each land cell can be treated as a vertex, and each horizontal 
	 * or vertical connection between land cells can be treated as an edge.
	 *  -- unlike the other DFS problems we've done, the 
	 * edges aren't explicitly given to us
	 * 
	 * We can do either DFS or BFS again, because it's not about shortest path
	 * 
	 * Important distinction: We might check neighboring water cells, but we
	 * never add them to our stack or queue for exploration.
	 * 
	 * So. If we find our first island. How do we find the second one?
	 * WE NEED TO SCAN THE ENTIRE GRID. We only perform DFS on land
	 * 
	 * If it's water, skip it and move to the next cell in our loop.		
	   If it's land that we've already visited, skip it.
	   If it's unvisited land, start a DFS.
	 * 
	 */
	
	/*
	 * So I'm thinking about this. We will start by iterating from the top left corner
	 * If it is land --> We will start DFS. Mark cell visited. We will look up down left right for land. If no land, we skip
	 * 		go until we hit water or an edge of map (which is water)
	 * 		once dfs completed, we increase island count
	 * If it's not land, or if it's already been visited --> we skip
	 */
	
	
	/*
	 * Where this differs from FindIfPathExistsGraph:
	 * - vertex is no longer an int, it's now a row col pair (so node 1 --> now grid[i][j])
	 * - visited array is now 2D
	 * - neighbors no longer given to us as edges, now come from surrounding grid cells
	 * - stack now stores coordinates, not just an integer node
	 */
	
	public static int numIslands(char[][] grid) {
		//island count --> 0 at first
		int islandCount = 0;
		
		//create visited array -- instead of 1D like we'd normally use
		//do 2D to match our map
		//grid.length --> number of rows
		//grid[0].length --> number of columns
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		//next: scan the entire grid to discover new islands
		for(int i=0; i<grid.length; i++) {
			for(int j = 0; j<grid[i].length; j++) {
				if(grid[i][j]=='1' && !visited[i][j]) { //if land cell AND not visited
					//at this point, we have discovered a new island
					//we don't know how big it is, but we have discovered it!
					islandCount++;
					//do dfs
					//we need to specify source
					//we do not need to specify destination because we do not care if we reach
					//a particular vertex/node
					//we only stop exploring once our stack is empty
					iterativeDFS(grid, visited,i, j); //give it starting row and starting col as source
					//Whenever we push a vertex onto the stack, we immediately mark it as visited.
					//DO THIS ^^^ inside of DFS method
				}
			}
		}
		return islandCount;

	}
	
//	The outer numIslands() method is responsible for counting islands. The inner iterativeDFS() method is responsible for exploring them.
	// so we don't have to return true/false or anything like that. Or even a count, this gets handled in
	//our numIslands method
	public static void iterativeDFS(char[][]grid, boolean [][] visited, int row, int col) {
		//previously, we used Deque<Integer>, which worked because each vertex was a single int
		//we need to represent coordiantes, like int[] cell = {2,3}
		
		Deque<int[]> neighbors = new ArrayDeque<>();
		
		//now we want to push our start cell
		//this time, it's coordinates, not a single number
		neighbors.push(new int[]{row,col});
		
		//Whenever we push a vertex onto the stack, we immediately mark it as visited.
		visited[row][col]=true;
		
		//used later...see below
		int[][] directions = {
			    {1, 0},   // Down
			    {-1, 0},  // Up
			    {0, 1},   // Right
			    {0, -1}   // Left
		};
		
		//recall in findifpathexists: while neighbors are not empty
		//pop
		//explore neighbors
		//we will follow the same structure. just different stack type
		
		while(!neighbors.isEmpty()) {
			int[] current = neighbors.pop(); //remember, it's like
			//{1,2} to represent current cell
			int currentRow = current[0];
			int currentCol = current[1];

			//recall before:
//			for (int neighbor : graph.get(current)) {
//			    if (!visited[neighbor]) {
//			        neighbors.push(neighbor);
//			        visited[neighbor] = true;
//			    }
//			}
			
			//want to do something similar except before we push onto stack,
			//cell must be in boundaries of grid
			//cell must be land
			//cell can't be visited
			
			//we have yet to specify what nextRow/Col is.
			//Instead of writing out all of these specific coordinates +1, -1, etc:
			//directions array
			
			//DECLARE THIS OUTSIDE OF WHILE LOOP to avoid unnecessary array allocations
//			int[][] directions = {
//				    {1, 0},   // Down
//				    {-1, 0},  // Up
//				    {0, 1},   // Right
//				    {0, -1}   // Left
//			}; //each pair represents a position compared to the current cell we are at
			
			//instead of writing the same logic four times, we can loop through the directions array
			for(int[] direction : directions) {
				//THIS IS REPLACING OUR PREVIOUS: for (int neighbor : graph.get(current)) from findIfPathExists
				
				//calculate nextRow
				int nextRow = currentRow+direction[0];
				//calculate nextCol
				int nextCol = currentCol+direction[1];
				
			    // Check boundaries
					//For the boundary check: we can write one general check
					//that works for all four directions
					//nextRow >= 0
					//nextRow < numRows
					//nextCol >= 0
					//nextCol < numCols
			    // Check if land
			    // Check if unvisited
			    // Push and mark as visited
				if (nextRow >= 0 &&  nextRow < grid.length && nextCol >= 0 &&
						nextCol < grid[0].length && grid[nextRow][nextCol] == '1'  //I want to note the order you do this in matters, java evals from L to R. So if we checked visited[][] or == '1' and it was an invalid index -1, this would throw an error!!
						&& !visited[nextRow][nextCol]){
					//follow familiar pattern of pushing to stack
					//and marking as true
					neighbors.push(new int[]{nextRow,nextCol});
					visited[nextRow][nextCol] = true;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {

        // Test Case 1: Expected output = 1
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };

        // Test Case 2: Expected output = 3
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        
        // Test Case 3: Expected output = 0 (my edge case)
        char[][] grid3 = {
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        // Test Case 4: Expected output = 1 (my edge case. Giant island lol)
        char[][] grid4 = {
            {'1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1'}
        };
        
        System.out.println(numIslands(grid1)); //1
        System.out.println(numIslands(grid2)); //3
        System.out.println(numIslands(grid3)); //0
        System.out.println(numIslands(grid4)); //1
	}
	
	/*
	 * WOOT WOOT!!! this patten is called counting connected components in an implicit graph
	 * which!!! is applicable to many other graph and grid problems
	 */
	
	/*
	 * in numIslands
	 * Runtime: for loop --> O(n*m) where n is the number of rows and m is the number of cols
	 * Space: visited array --> size O(n*m)
	 * 
	 * in iterativeDFS
	 * Runtime: 
	 * - while neighbors!Empty --> could be O(n*m) time - the total number of cells pushed and popped throughout traversal
	 * - for direction loop --> O(4) --> constant time. But done for each neighbor
	 * 
	 * Space: 
	 * - neighbors stack could, at most be all cells so n*m
	 * - directions array is constant space --> O(1)
	 * 
	 */
	
	//Would time and space both be O(n*m)?
	
	//YAH, final notes:
	/*
	 * n = number of rows
	 * m = number of columns
	 *
	 * Time Complexity: O(n * m)
	 * - Initializing the visited array takes O(n * m).
	 * - The nested loops scan every cell: O(n * m).
	 * - Across all DFS calls, each land cell is pushed
	 *   and popped at most once because we mark cells
	 *   as visited immediately upon pushing.
	 * - For each explored cell, we check four directions,
	 *   which is constant time: O(1).
	 *
	 * Space Complexity: O(n * m)
	 * - Visited array: O(n * m).
	 * - DFS stack: O(n * m) in the worst case.
	 * - Directions array: O(1).
	 * - Local variables: O(1).
	 */
	
	//REMEMBER in iterative DFS: use LIFO/Stack. BFS is a queue

	/*
	 * POTENTIAL OPTIMIZATION: In-place modification
	 *
	 * If we are allowed to modify the original input grid,
	 * we can eliminate the separate visited array.
	 *
	 * Instead of marking a cell as visited:
	 *     visited[nextRow][nextCol] = true;
	 *
	 * We can turn the land cell into water:
	 *     grid[nextRow][nextCol] = '0';
	 *
	 * We must do this for both the starting cell and
	 * every neighboring cell immediately upon pushing
	 * them onto the stack.
	 *
	 * This simplifies our conditions because we only
	 * need to check whether a cell is land ('1').
	 * Previously visited land will now be water ('0'),
	 * preventing duplicate exploration and island counting.
	 *
	 * Time Complexity: O(n * m) -- unchanged
	 * Space Complexity: O(n * m) -- unchanged
	 *
	 * Although we eliminate the O(n * m) visited array,
	 * the DFS stack still requires O(n * m) space in
	 * the worst case.
	 *
	 * BENEFIT:
	 * - Reduces actual auxiliary memory usage.
	 * - Simplifies the implementation.
	 *
	 * TRADE-OFF:
	 * - Mutates the original input grid.
	 * - Cannot reuse the original grid afterward.
	 *
	 * INTERVIEW TIP:
	 * Always clarify whether modifying the input is
	 * permitted before implementing this optimization.
	 */
}
