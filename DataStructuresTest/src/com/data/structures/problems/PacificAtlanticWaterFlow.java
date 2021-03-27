package com.data.structures.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * MEDIUM
 * @author Nelson Costa
 *
 */
public class PacificAtlanticWaterFlow {

	/**
	 * @intuition
	 * 		The intuition is to find every cell that connects with the cells
	 * 		in the boarder of the  matrix once those connect with the ocean.
	 * 
	 * 		every time I process a cell it gets marked with true.
	 * 
	 * 		I calculate the pacific and then the atlantic ocean
	 * 		
	 * 		and the answer lies in the intersection
	 * 
	 * @fail
	 * 
	 * @time
	 * 
	 * 
	 * @space
	 * 
	 */
    int [][] directions = {{-1, 0},{0,1},{1,0},{0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
      
         if (matrix == null || matrix.length == 0)
             return  new ArrayList<List<Integer>>();
         
         
         boolean [][] paci = new boolean[matrix.length][matrix[0].length];
         
         boolean [][] atlan = new boolean[matrix.length][matrix[0].length];
         
        //1 - percorrer rows pacificas
         for (int col = 0; col < matrix[0].length; col++)
         {
            search(matrix, paci , 0, col, -1);
            search(matrix, atlan, matrix.length - 1, col, -1);
         }

        //2 - percorrer rows atlanticas
         
         for (int row = 0; row < matrix.length; row++)
         {
            search(matrix, paci , row, 0, -1);
            search(matrix, atlan, row, matrix[0].length - 1, -1);
         }
         
         

        //3 - resultados
         List<List<Integer>> ans = new ArrayList<List<Integer>>();
         List<Integer> entry = new ArrayList<>();
         
         for (int r = 0; r < matrix.length; r++)
         {
             for (int c = 0; c < matrix[r].length;c++)
             {
                 if (paci[r][c]==atlan[r][c] && paci[r][c])
                 {
                     entry = new ArrayList<>();
                     entry.add(r);
                     entry.add(c);
                     ans.add(entry);
                 }
             }
         }
         
         
        //4 - retornar valores
         return ans;
     }
    
    private void search(int [][] matrix, boolean [][] ocean, int r, int c, int prev)
    {
        
        if (r < 0 || r >= ocean.length ||  c < 0 || c >= ocean[0].length || ocean[r][c] || matrix[r][c] < prev)
            return;
    
        ocean[r][c] = true;
        
        for(int [] dir : directions)
        {
            int row = dir[0];
            int col = dir[1];
            search(matrix, ocean, r + row, c + col, matrix[r][c]);
        }
    }
}
