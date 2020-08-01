package com.data.structures.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.data.structures.problems.ds.LeetCodeExercise;

/**
 * https://leetcode.com/problems/number-of-distinct-islands
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class NumberofDistinctIslands extends LeetCodeExercise{

	static NumberofDistinctIslandsSolution2 n2 = new NumberofDistinctIslandsSolution2();
	public static void main(String[] args) {

		int [][] grid = stringToMatrix("[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]");
		n2.numDistinctIslands(grid);
	}
    /** 
     * @intuition
     * 		backtrack, generate code for island add code to set, the answer will be the size of the set.
     * 
     * 
     * @score
     * 		Runtime: 26 ms, faster than 16.54% of Java online submissions for Number of Distinct Islands.
	 *		Memory Usage: 53.7 MB, less than 5.23% of Java online submissions for Number of Distinct Islands.
	 *
	 *		Runtime: 14 ms, faster than 40.35% of Java online submissions for Number of Distinct Islands.
			Memory Usage: 41.5 MB, less than 5.23% of Java online submissions for Number of Distinct Islands.
	 *
     * @fail
     *    1) stackoverflowError, forgot to return if node is visited 
     *    2) failed, forgot to reset the rows and columns 
     *    3) I subtracted negative and positive number and it turnout to be adding, what a noob mistake
     *    
     * @time
     * 		O(r*c)
     * @space
     * 		O(r*c)
     *    
    **/
    public int numDistinctIslands(int[][] grid) {
        
        if (grid == null)
            return 0;
        
                
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        Set<String> islandCodes = new HashSet<>();
        String code; 
        for (int r = 0; r < grid.length; r++)
        {
            for(int c = 0; c < grid[0].length; c++)
            {
                if(!visited[r][c] && grid[r][c] == 1)
                {
                    
                    code = getIslandShape(r, c, new StringBuilder(""), grid, visited, -r, -c).toString();
                    
                    if (!islandCodes.contains(code))
                        islandCodes.add(code);  
                }
            }
        }
        
        return islandCodes.size();
    }
    
    
    private StringBuilder getIslandShape(int r, int c, StringBuilder curCode, int [][] grid, boolean [][] visited, int rDiscount, int cDiscount)
    {
        if (r < 0 || c < 0 || c >= grid[0].length || r >= grid.length || grid[r][c] == 0 || visited[r][c])
        {
            return curCode;
        }
        
        curCode.append((r + rDiscount) + "" + (c + cDiscount));
        visited[r][c] = true;
        
        curCode = getIslandShape(r - 1, c, curCode, grid, visited, rDiscount, cDiscount);
        curCode = getIslandShape(r, c + 1, curCode, grid, visited, rDiscount, cDiscount);
        curCode = getIslandShape(r + 1, c, curCode, grid, visited, rDiscount, cDiscount);
        curCode = getIslandShape(r, c - 1, curCode, grid, visited, rDiscount, cDiscount);
        
        return curCode;
    }
}



/*********************
* OTHERS SOLUTIONS
*********************/
/**
 * This a very similar solution to the previous but instead of calculating a code for the current coordinates,  the author creats some kind of path.
 * 
 * each direction exploration as a ID and so each pattern has it's unique direction exploration code.
 * 
 * we need to put the zero at the end else the code is not unique
 * 
 * @media
 * 		https://imgur.com/TqfrUYn (showing a case that fails without zeros at the end)
 * @author Nelson Costa
 *
 */
class NumberofDistinctIslandsSolution2 {
    int[][] grid;
    boolean[][] seen;
    ArrayList<Integer> shape;

    public void explore(int r, int c, int di) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add(di);
            explore(r+1, c, 1);
            explore(r-1, c, 2);
            explore(r, c+1, 3);
            explore(r, c-1, 4);
            shape.add(0);
        }
    }
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<ArrayList<Integer>>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new ArrayList<Integer>();
                explore(r, c, 0);
                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }
}


/**
 * This solution uses the same approach as mine but with a difference in how they create a unique code.
 * 
 * they use the formula:
 * 
 * 		row * 2 * column.length + column
 * 
 * I didnt remember how to apply this solution. 
 * 
 * they multiply by 2 to avoid collitions between positive and negative numbers in the columns.
 * 
 * (rows cannot be negative, only columns) 
 * 
 * after this they add the number to a set and add the set to a set of sets..
 * 
 * the rest is pretty much the same
 * 
 * the difficulty was how to get the code
 * 
 * 
 * @media 
 * 		https://imgur.com/9d6hNZ9 (draft about the math formula)
 * 
 * @author Nelson Costa
 *
 */
class NumberofDistinctIslandsSolution1 {
    int[][] grid;
    boolean[][] seen;
    Set<Integer> shape;
    
    public void explore(int r, int c, int r0, int c0) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add((r - r0) * 2 * grid[0].length + (c - c0));
            explore(r+1, c, r0, c0);
            explore(r-1, c, r0, c0);
            explore(r, c+1, r0, c0);
            explore(r, c-1, r0, c0);
        }
    }
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<HashSet<Integer>>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new HashSet<Integer>();
                explore(r, c, r, c);
                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }
}

/**
 * This is the top solution
 * 
 * It changes the input instead of using a seen boolean
 * 
 * to create a code it uses a string builder and uses a path as references:
 * 
 * it always starts with 0 and then depending on whether you move up down right or left you get a number, at the end of each not processing we add a 0.
 * 
 * 
 * @author Nelson Costa
 *
 */
class NumberofDistinctIslandsUnofficialSolution1 {
    private static int rows, cols;
    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        HashSet<String> set = new HashSet();
        StringBuilder sb;
        rows = grid.length;
        cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    dfs(grid, i, j, sb, '0');
                    if (sb != null) {
                        set.add(sb.toString());
                    }
                    
                }
            }
        }
        return set.size();
        
    }
    
    private void dfs(int[][] grid, int r, int c, StringBuilder sb, char dir) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != 1) {
            return;
        }
            
        sb.append(dir);
        grid[r][c] = 0;
        
        dfs(grid, r+1, c, sb, '1');
        dfs(grid, r-1, c, sb, '2');
        dfs(grid, r, c+1, sb, '3');
        dfs(grid, r, c-1, sb, '4');
        sb.append('0');
    }
}
