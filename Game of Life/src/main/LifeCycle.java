package main;

/**
 * @author Assaf
 *
 */
public class LifeCycle implements Cloneable{
	
	private boolean [][]matrixOfLife;
	private int numOfRows;
	private int numOfColumns;
	
	
	
	/**
	 * LifeCycle is a constructor which receives the dimensions of the matrix and creates a boolean matrix by those dimensions 
	 * @param numOfRows number of rows in matrix
	 * @param numOfColumns number of columns in matrix
	 */
	public LifeCycle(int numOfRows, int numOfColumns) {
		this.numOfColumns = numOfColumns;
		this.numOfRows = numOfRows;
		this.matrixOfLife = new boolean[numOfRows][numOfColumns];
	}
	
	
	
	/**
	 * copyCurrentState will copy the current matrix
	 * @return a 2D boolean matrix copy of the current matrix
	 */
	private boolean[][] copyCurrentState()
	{
		boolean[][] stateCopy = new boolean[numOfRows][numOfColumns];
		
		for (int row = 0; row < numOfRows; row++)
			for (int column = 0; column < numOfColumns; column++)
				stateCopy[row][column] = matrixOfLife[row][column];
		
		return stateCopy;
	}
	
	
	
	/**
	 * matrixOfLife initiates all cell as "false", creating a "Clean" matrix with no living cells
	 */
	public void clearMatrix()
	{
		for (int row = 0; row < matrixOfLife.length; row++)
			for (int column = 0; column < matrixOfLife[0].length; column++)
				matrixOfLife[row][column] = false;	
	}
	
	
	@Override
	protected Object clone()
	{
		LifeCycle lifeCycle = new LifeCycle(numOfRows, numOfColumns);
		
		lifeCycle.matrixOfLife = this.copyCurrentState();
		
		return matrixOfLife;
	}
	
	
	/**
	 * nextGeneration will determine each cell state and its neighbors state
	 * and will reverse the state or leave it be by the game's rules
	 */
	public void nextGeneration() {
		int cellCount = 0; //
		int row;
		int column;
		boolean [][] currentState = copyCurrentState();
		
		copyCurrentState();
		
		for (row = 0; row < numOfRows; row++) {
			for (column = 0; column < numOfColumns; column++) {
				
				if (row == 0 && column == 0) {
					if (currentState[row][column + 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
					if (currentState[row + 1][column + 1])
						cellCount++;
				} 
				
				else if (row == 0 && column == numOfColumns - 1) {
					if (currentState[row][column - 1])
						cellCount++;
					if (currentState[row + 1][column - 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
				} 
				
				else if (row == numOfRows - 1 && column == 0) {
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row - 1][column + 1])
						cellCount++;
					if (currentState[row][column + 1])
						cellCount++;
				} 
				
				else if (row == numOfRows - 1 && column == numOfColumns - 1) {
					if (currentState[row - 1][column - 1])
						cellCount++;
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row][column - 1])
						cellCount++;
				} 
				
				else if (row == 0 && column != numOfColumns - 1 && column != 0) {
					if (currentState[row][column - 1])
						cellCount++;
					if (currentState[row][column + 1])
						cellCount++;
					if (currentState[row + 1][column - 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
					if (currentState[row + 1][column + 1])
						cellCount++;
				} 
				
				else if (row == numOfRows - 1 && column != numOfColumns - 1 && column != 0) {
					if (currentState[row - 1][column - 1])
						cellCount++;
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row - 1][column + 1])
						cellCount++;
					if (currentState[row][column - 1])
						cellCount++;
					if (currentState[row][column + 1])
						cellCount++;
				} 
				
				else if (row != numOfRows - 1 && row != 0 && column == 0) {
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row - 1][column + 1])
						cellCount++;
					if (currentState[row][column + 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
					if (currentState[row + 1][column + 1])
						cellCount++;
				} 
				
				else if (row != numOfRows - 1 && row != 0 && column == numOfColumns - 1) {
					if (currentState[row - 1][column - 1])
						cellCount++;
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row][column - 1])
						cellCount++;
					if (currentState[row + 1][column - 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
				} 
				
				else {
					if (currentState[row - 1][column - 1])
						cellCount++;
					if (currentState[row - 1][column])
						cellCount++;
					if (currentState[row - 1][column + 1])
						cellCount++;
					if (currentState[row][column - 1])
						cellCount++;
					if (currentState[row][column + 1])
						cellCount++;
					if (currentState[row + 1][column - 1])
						cellCount++;
					if (currentState[row + 1][column])
						cellCount++;
					if (currentState[row + 1][column + 1])
						cellCount++;
				}
				if (currentState[row][column]) {
					if (cellCount == 0 || cellCount == 1 || cellCount >= 4) //under population or over population -> death of cell
						matrixOfLife[row][column] = false;
				} else {
					if (cellCount == 3) //reproduction -> rebirth of cell
						matrixOfLife[row][column] = true;
				}
				cellCount = 0; // zero 
			}
		}
	}
}
