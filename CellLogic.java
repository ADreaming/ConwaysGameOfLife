package GameOfLife;

import java.util.ArrayList;
import java.util.Random;

public class CellLogic {
	
	protected static UpdatePack prepareUpdate(Cell[] cells, int gSize){
		int length = cells.length;
		
		Cell[] nextGen = new Cell[length];
		Cell cell = new Cell();
		int convertedCells = 0;
		int enemyCells = 0;
		int playerCells = 0;
		
		for (int i = 0; i < length; i++){
			ArrayList<Cell> neighbors = getNeighbors(cells, gSize, i);
			int liveNs = 0;
			int enemy = 0;
			int player = 0;
					
			for (Cell c: neighbors){
				if (c.isAlive()){
					liveNs++;
					
					if (c.getState() == Cell.PLAYER){
						player++;
						
						if (cells[i].getState() == Cell.ENEMY){
							convertedCells++;
						}
					} else{
						enemy++;
					}
				}
				
				if (cells[i].isAlive()){
					cell = new Cell(liveNs < 2? Cell.DEAD: liveNs > 3? Cell.DEAD: player >= enemy? Cell.PLAYER: Cell.ENEMY);
				} else{
					cell = new Cell(liveNs == 3? player >= enemy? Cell.PLAYER: Cell.ENEMY: Cell.DEAD);
				}
				
				
			}
			
			nextGen[i] = cell;
			if (cell.getState() == Cell.ENEMY){
				enemyCells++;
			}
			
			if (cell.getState() == Cell.PLAYER){
				playerCells++;
			}
		}
		
		return new UpdatePack(nextGen, convertedCells, enemyCells, playerCells);
	}

	private static ArrayList<Cell> getNeighbors(Cell[] cells, int gSize, int cell) {
		
		int length = cells.length;
		boolean topRow, leftCol, rightCol, botRow;
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		topRow = leftCol = rightCol = botRow = false;
		
		if (cell < gSize){
			topRow = true;
		} else if (cell >= length - gSize){
			botRow = true;
		}
		
		if (cell == 0 || cell%gSize == 0){
			leftCol = true;
		} else if ((cell+1)%gSize == 0){
			rightCol = true;
		}
		
		if (topRow && leftCol){
			neighbors.add(cells[cell + gSize * (gSize - 1) + (gSize - 1)]);//
			neighbors.add(cells[cell + gSize * (gSize - 1)]);//
			neighbors.add(cells[cell + gSize * (gSize - 1) + 1]);//
			neighbors.add(cells[cell + (gSize - 1)]);//
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell + gSize + (gSize - 1)]);//
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize + 1]);
			
		} else if (topRow && rightCol){
			neighbors.add(cells[cell + gSize * (gSize - 1) - 1]);
			neighbors.add(cells[cell + gSize * (gSize - 1)]);
			neighbors.add(cells[cell + gSize * (gSize - 1) - (gSize - 1)]);
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell - (gSize - 1)]);
			neighbors.add(cells[cell + gSize - 1]);
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize - (gSize - 1)]);
			
		} else if (botRow && leftCol){
			neighbors.add(cells[cell - gSize * (gSize - 1) + (gSize - 1)]);//
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize + 1]);
			neighbors.add(cells[cell + (gSize - 1)]);//
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell - gSize * (gSize - 1) + (gSize - 1)]);//
			neighbors.add(cells[cell - gSize * (gSize - 1)]);//
			neighbors.add(cells[cell - gSize * (gSize - 1) + 1]);//
			
		} else if (botRow && rightCol){
			neighbors.add(cells[cell - gSize - 1]);
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize - (gSize - 1)]);//
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell - (gSize - 1)]);//
			neighbors.add(cells[cell - gSize * (gSize - 1) - 1]);//
			neighbors.add(cells[cell - gSize * (gSize - 1)]);//
			neighbors.add(cells[cell - gSize * (gSize - 1) - (gSize - 1)]);//
			
		} else if (topRow){
			neighbors.add(cells[cell + gSize * (gSize - 1) - 1]);//
			neighbors.add(cells[cell + gSize * (gSize - 1)]);//
			neighbors.add(cells[cell + gSize * (gSize - 1) + 1]);//
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell + gSize - 1]);
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize + 1]);
			
		} else if (botRow){
			neighbors.add(cells[cell - gSize - 1]);
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize + 1]);
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell - gSize * (gSize - 1) - 1]);//
			neighbors.add(cells[cell - gSize * (gSize - 1)]);//
			neighbors.add(cells[cell - gSize * (gSize - 1) + 1]);//
			
		} else if (leftCol){
			neighbors.add(cells[cell - gSize + (gSize - 1)]);//
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize + 1]);
			neighbors.add(cells[cell + (gSize - 1)]);//
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell + gSize + (gSize - 1)]);//
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize + 1]);
			
		} else if (rightCol){
			neighbors.add(cells[cell - gSize - 1]);
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize - (gSize - 1)]);//
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell - (gSize - 1)]);//
			neighbors.add(cells[cell + gSize - 1]);
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize - (gSize - 1)]);//
			
		} else {
			neighbors.add(cells[cell - gSize - 1]);
			neighbors.add(cells[cell - gSize]);
			neighbors.add(cells[cell - gSize + 1]);
			neighbors.add(cells[cell - 1]);
			neighbors.add(cells[cell + 1]);
			neighbors.add(cells[cell + gSize - 1]);
			neighbors.add(cells[cell + gSize]);
			neighbors.add(cells[cell + gSize + 1]);
		}
		
		return neighbors;
	}

	protected static Cell[] createSeed(int gSize) {
		Cell[] generation = new Cell[gSize * gSize];
		
		Random r = new Random();
		for (int i = 0; i < generation.length; i++){
			generation[i] = new Cell(r.nextInt(5) == 0? 1: 0);
		}
		
		return generation;
	}
	
	protected static UpdatePack addCellsAt(int gSize, Cell[] generation, int cell, int toolMode){
		ToolPack tp = Tools.getToolPack(toolMode);
		int[] blueprint = tp.blueprint;
		int dimX = (int) tp.dim.getWidth();
		int dimY = (int) tp.dim.getHeight();
		
		int numcells = gSize * gSize;
		int[] gencells = new int[dimX * dimY];
		
		//Number of cells left before the row ends. [][]X[][][]? xRoom = 3
		int xRoom = 0;
		for (int i = 0; i < gSize; i++){
			if ((cell + i + 1)%(gSize) == 0){
				xRoom = i;
				break;
			}
		}
		
		for (int y = 0; y < dimY; y++){
			for (int x = 0; x < dimX; x++){
				boolean xWrap = x > xRoom;
				boolean yWrap = cell + y*gSize > numcells;
				
				int dX = 0;
				int dY = 0;

				if (xWrap){
					dX =  x - gSize;
				}
				
				if (yWrap){
					dY = y*gSize - numcells;
				}
				
				if (xWrap && yWrap){
					gencells[x + y*dimX] = cell + dX + dY;
				} else if (xWrap){
					gencells[x + y*dimX] = cell + dX + y*gSize;
				} else if (yWrap){
					gencells[x + y*dimX] = cell + x + dY;
				} else{
					gencells[x + y*dimX] = cell + x + y*gSize;
				}

			}
		}
		
		for (int i = 0; i < blueprint.length; i++){
			if(blueprint[i] == 1){
				int oldcell = gencells[i];
				if (!generation[oldcell].isAlive()){
					generation[oldcell] = new Cell(Cell.PLAYER);
				}
			}
		}
		
		int pc = 0;
		int ec = 0;
		
		for (int i = 0; i < generation.length; i++){
			if (generation[i].getState() == Cell.PLAYER){
				pc++;
			} else if (generation[i].getState() == Cell.ENEMY){
				ec++;
			}
		}
		
		return new UpdatePack(generation, 0, ec, pc);
	}

}

class UpdatePack{
	Cell[] ca;
	int cc, en, pl;
	
	public UpdatePack(){
		ca = null;
		cc = -1;
		en = -1;
		pl = -1;
	}
	
	public UpdatePack(Cell[] cells, int cc, int enemies, int players){
		this.ca = cells;
		this.cc = cc;
		this.en = enemies;
		this.pl = players;
	}
}
