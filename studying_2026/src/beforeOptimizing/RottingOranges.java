package beforeOptimizing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RottingOranges {

//	    You are given an m x n grid where each cell can have one of three values:
	
//		0 representing an empty cell,
//		1 representing a fresh orange, or
//		2 representing a rotten orange.
//		Every minute, any fresh orange that is 4-directionally adjacent to a 
//      rotten orange becomes rotten.
//
//		Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
	
	
// okay so BFS on a grid, multisource	
    public static int orangesRotting(int[][] grid) {

        
        //Input: grid = [[2,1,1],
    	//				 [1,1,0],
    	//				 [0,1,1]] where 2 is rotting, 1 is fresh, 0 is none

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
        
    	//setup
    	Queue<int[]> queue = new LinkedList<>(); //we want to store the pair of values
        Set<int[]> visited = new HashSet<>();
        
        //look for rotting oranges. There will be multiple starts
        for(int i=0; i< grid.length; i++) {
        	for(int j=0; j<grid[i].length; j++) {
        		System.out.println(grid[i][j]);
        		if(grid[i][j] == 2) {
        			//means rotting orange
        			int [] start = {i,j};
        	        queue.add(start); //add every rotting orange 
        		}
        	}
        }
        //at this point, queue should contain all coordinates where a 2 is located
//        for(int[] pair : queue) {
//        	System.out.println(pair[0]+","+pair[1]);
//        }
        
        if(queue.isEmpty()) {
        	//means no rotting oranges, return 0, oranges will never rot
        	return 0;
        }
        
        //Start: Choose a starting node, mark it as visited, and put it into the queue. -- Confused since mulitple starts
      //  * Repeat: While the queue is not empty, take the first node out of the queue.
        
        //Explore: Look at all neighbor nodes of this current node. If a neighbor has not been 
        //			visited yet, mark it as visited and put it into the back of the queue.
        
        //Input: grid = [[2,1,1],
    	//				 [1,1,0],
    	//				 [0,1,1]] where 2 is rotting, 1 is fresh, 0 is none
        
        int minutes = 0; //how many minutes to rot
        while (!queue.isEmpty()) {
            int[] node = queue.poll(); //0,0
            System.out.println(node[0]+","+node[1]); //current node
            
            //we need to get neighbors. It's going to be anything +1 -1 horizontally
            //and +1 -1 vertically
            
            	//neighbor exists to the right, check as visited, if not, add to visited and queue
            if(node[0]+1>0 && node[0]+1<grid.length) { //row
            	//means valid neighbor
            	//check if 1 or 2 and add to queue and rot count
            	int[] neighbor = {node[0]+1, node[1]};
              if (!visited.contains(neighbor)&& grid[neighbor[0]][neighbor[1]] == 1) {
            	  //means wil rot, mark it as rot
            	  grid[neighbor[0]][neighbor[1]] = 2;
            	  visited.add(neighbor);
            	  queue.add(neighbor);
              }
            }
            
            if(node[0]-1>0 && node[0]-1<grid.length) {
            	//valid node
            	//means valid neighbor
            	//check if 1 or 2 and add to queue and rot count
            	int[] neighbor = {node[0]-1, node[1]};
              if (!visited.contains(neighbor)&& grid[neighbor[0]][neighbor[1]] == 1) {
            	  //means wil rot, mark it as rot
            	  grid[neighbor[0]][neighbor[1]] = 2;
            	  visited.add(neighbor);
            	  queue.add(neighbor);
              }
            }
            
            if(node[1]+1>0 && node[1]+1<grid[0].length) { //col
            	//means valid node
            	//means valid neighbor
            	//check if 1 or 2 and add to queue and rot count
            	int[] neighbor = {node[0], node[1]+1};
              if (!visited.contains(neighbor)&& grid[neighbor[0]][neighbor[1]] == 1) {
            	  //means wil rot, mark it as rot
            	  grid[neighbor[0]][neighbor[1]] = 2;
            	  visited.add(neighbor);
            	  queue.add(neighbor);
              }
            }
            
            if(node[1]-1>0 && node[1]-1<grid[0].length) { //col
            	//means valid node
            	//means valid neighbor
            	//check if 1 or 2 and add to queue and rot count
            	int[] neighbor = {node[0], node[1]-1};
              if (!visited.contains(neighbor)&& grid[neighbor[0]][neighbor[1]] == 1) {
            	  //means wil rot, mark it as rot
            	  grid[neighbor[0]][neighbor[1]] = 2;
            	  visited.add(neighbor);
            	  queue.add(neighbor);
              }
            }
            	
            minutes++;
          }
//      }
        
      //  * Explore: Look at all neighbor nodes of this current node. If a neighbor has not been 
      //  * 			visited yet, mark it as visited and put it into the back of the queue.
      //  * Finish: Stop when the queue is completely empty.
        
        return minutes;
    }
    
    //I think this is still happening iteratively. It's like we need all of the rotting ones starting BFS in parallel
    
    public static void main(String[] args) {
    	int [][] grid = {{2,1,1},{1,1,0},{0,1,1}};
    	System.out.println(orangesRotting(grid));
    }
}
