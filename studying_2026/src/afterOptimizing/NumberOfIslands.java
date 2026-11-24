package afterOptimizing;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {

	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int numIslands(char[][] grid) {
		int islandCount = 0;
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		// Scan the entire grid for undiscovered islands
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					islandCount++;
					iterativeDFS(grid, visited, i, j);
				}
			}
		}
		return islandCount;
	}

	public static void iterativeDFS(char[][] grid, boolean[][] visited, int row, int col) {
		// Initialize stack and mark starting cell as visited
		Deque<int[]> neighbors = new ArrayDeque<>();
		neighbors.push(new int[] { row, col });
		visited[row][col] = true;
		// Define possible directions
		int[][] directions = { { 1, 0 }, // Down
				{ -1, 0 }, // Up
				{ 0, 1 }, // Right
				{ 0, -1 } // Left
		};
		// Explore the entire connected component
		while (!neighbors.isEmpty()) {
			int[] current = neighbors.pop();
			int currentRow = current[0];
			int currentCol = current[1];
			// Calculate neighboring coordinates
			for (int[] direction : directions) {
				int nextRow = currentRow + direction[0];
				int nextCol = currentCol + direction[1];
				// Check boundaries, land, and visited status
				if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length
						&& grid[nextRow][nextCol] == '1' && !visited[nextRow][nextCol]) {
					// Push valid neighbor and mark as visited
					neighbors.push(new int[] { nextRow, nextCol });
					visited[nextRow][nextCol] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		// Test Case 1: One connected island
		char[][] grid1 = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };

		// Test Case 2: Three separate islands
		char[][] grid2 = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
				{ '0', '0', '0', '1', '1' } };

		// Test Case 3: All water
		char[][] grid3 = { { '0', '0', '0', '0', '0' }, { '0', '0', '0', '0', '0' }, { '0', '0', '0', '0', '0' },
				{ '0', '0', '0', '0', '0' } };

		// Test Case 4: All land
		char[][] grid4 = { { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1' } };

		System.out.println(numIslands(grid1)); // 1
		System.out.println(numIslands(grid2)); // 3
		System.out.println(numIslands(grid3)); // 0
		System.out.println(numIslands(grid4)); // 1
	}
}

/*
 * PATTERN: Counting Connected Components (Grid DFS)
 *
 * RECOGNITION: - Given a 2D grid or matrix. - Asked to count distinct connected
 * regions, islands, clusters, or groups. - Connectivity is defined by
 * neighboring cells.
 *
 * APPROACH: 1. Treat the grid as an implicit graph. 2. Initialize a 2D visited
 * array. 3. Scan the entire grid. 4. When an unvisited, valid cell is
 * discovered: - Increment the component count. - Perform DFS to explore the
 * entire component. 5. Return the component count.
 *
 * KEY TECHNIQUES: - Stack for iterative DFS (LIFO). - Coordinate pairs to
 * represent vertices. - Directions array to calculate neighbors. - Boundary
 * checks before accessing cells. - Mark cells as visited immediately upon
 * pushing.
 *
 * WHY DFS OR BFS? We care about connectivity, not shortest paths or the order
 * in which cells are explored.
 *
 * Time: O(n * m) Space: O(n * m)
 */
