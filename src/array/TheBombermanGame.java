package array;

import java.util.Scanner;

public class TheBombermanGame {
	public static void main(String[] args) {
		TheBombermanGame obj = new TheBombermanGame();
		System.out.println("Enter");
		Scanner rcn = new Scanner(System.in);
        int r = rcn.nextInt();
        int c = rcn.nextInt();
        int n = rcn.nextInt();
        String[] grid = new String[r];
        
        for (int i = 0; i < r; i++) {
            String gridItem = rcn.next();//input should be either (.) or (O).
            if (gridItem.length() == c) {
            	grid[i] = gridItem;
            } else {
            	System.out.println("Invalid input");
            	return;
            }
        }
        String[] result = obj.bomberMan(n, grid);
        obj.printArr(result, "***********");
        rcn.close();
	}
	
	private void printArr(String[] result, String n) {
		System.out.println("--------------"+n+"-----------------");
		for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
	}

	private String[] bomberMan(int n, String[] grid) {
		int rowSize = grid.length;
		int colSize = grid[0].length();
		for (int i = 1; i <= n; i+=3) {
			//******************First second start
			if (i <= n) {
				//In first second do nothing
				printArr(grid, i+"");
				//******************Second second start
				if ((i+1) <= n) {
					if ((i+2) > n) {// if 2nd second is last fill O's in String[].
						StringBuilder tempStr;
						for (int j = 0; j < rowSize; j++) {
							tempStr = new StringBuilder(grid[j]);
							for (int k = 0; k < colSize; k++)
								tempStr.setCharAt(k, 'O');
							grid[j] = tempStr.toString();
						}
					}
					printArr(grid, (i+1)+"");
					//******************Third second start
					if ((i+2) <= n) {
						doBlast(grid, rowSize, colSize);
						printArr(grid, (i+2)+"");
					}
					//******************Third second end
				}
				//******************Second second end
			}
			//******************First second end
		}
		return grid;
	}
	
	/*
6 7 3
.......
...O...
....O..
.......
OO.....
OO.....
-----
OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO

	 */
	private void doBlast(String[] grid, int rowSize, int colSize) {
		boolean[][] blasted = new boolean[rowSize][colSize];
		for (int i = 0; i < rowSize; i++) {
			StringBuilder tempStr = new StringBuilder(grid[i]);
			for (int j = 0; j < colSize; j++) {
				//both if & elseif conditions are for not blasted indirectly.
				if (tempStr.charAt(j) == 'O') {
					blasted[i][j] = true;
					tempStr.setCharAt(j, '.');//blasting current cell
					if (!(i-1 < 0 || i-1 >= rowSize || j < 0 || j >= colSize) && (grid[i-1].charAt(j) != 'O' || blasted[i-1][j]))// top
						grid[i-1] = blastCordinates(i-1, j, new StringBuilder(grid[i-1]), blasted).toString();
					if (!(i < 0 || i >= rowSize || j-1 < 0 || j-1 >= colSize) && (grid[i].charAt(j-1) != 'O' || blasted[i][j-1]))// left
						tempStr = blastCordinates(i, j-1, tempStr, blasted);
					if (!(i+1 < 0 || i+1 >= rowSize || j < 0 || j >= colSize) && (grid[i+1].charAt(j) != 'O' || blasted[i+1][j]))// bottom
						grid[i+1] = blastCordinates(i+1, j, new StringBuilder(grid[i+1]), blasted).toString();
					if (!(i < 0 || i >= rowSize || j+1 < 0 || j+1 >= colSize) && (grid[i].charAt(j+1) != 'O' || blasted[i][j+1]))// right
						tempStr = blastCordinates(i, j+1, tempStr, blasted);
				} else if (!blasted[i][j]) {
					tempStr.setCharAt(j, 'O');//fill with 'O'
					blasted[i][j] = true;
				}
			}
			grid[i] = tempStr.toString();
		}
	}

	private StringBuilder blastCordinates(int i, int j, StringBuilder rowStr, boolean[][] blasted) {
		blasted[i][j] = true;
		rowStr.setCharAt(j, '.');
		return rowStr;
	}
}
