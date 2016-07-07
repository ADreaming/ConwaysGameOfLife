package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ActionListener {
	private static final int ACTIVE = 0;
	private static final int WON = 1;
	private static final int LOST = 2;
	
	private GameOfLife INSTANCE;
	private int gSize;
	private Point[] gridCells;
	private Cell[] generation;
	private int endCount = 0;
	private int gameState = 0;
	private Timer timer = new Timer(100, this);
	
	public GameBoard(GameOfLife gol, int size){
		INSTANCE = gol;
		gSize = size;
		generation = null;
		gameState = 0;
	}
	
	protected void tick(Cell[] newGen){
		generation = newGen;
		paintComponent(getGraphics());
	}


	public int getCellAt(Point coords) {
		int cellSize = 600/gSize;
		
		int xCell = (int) Math.ceil((double) coords.x/cellSize) - 1;
		int yCell = (int) Math.ceil((double) coords.y/cellSize) - 1;
		
		int cell = gSize * yCell + xCell;
		
		return cell;
	}
	
	public void displayWin(){
		gameState = 1;
		timer.start();
	}
	
	public void displayLoss(){
		gameState = 2;
		timer.start();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		gridCells = new Point[gSize * gSize];
		int cellSize = 600/gSize;
		int count = 0;
		
		for (int i = 0; i < gSize; i++){//height
			for (int j = 0; j < gSize; j++){//width
				gridCells[count] = new Point(j * 600/gSize, i * 600/gSize);
				count++;
			}
		}
		
		if (!INSTANCE.isStarted()){
			g.setColor(Color.LIGHT_GRAY);
			for (int i = 0; i < gridCells.length; i++){
				if (i%2 == 0){
					g.fillRect(gridCells[i].x, gridCells[i].y, cellSize, cellSize);
				}
			}
			
			return;
		}
		
		for(int i = 0; i < generation.length; i ++){
			Cell cell = generation[i];
			
			if (cell.isAlive()){
				g.setColor(cell.getState() == Cell.ENEMY? Color.RED: Color.GREEN);
				g.fillRect(gridCells[i].x, gridCells[i].y, cellSize, cellSize);
			}
		}
		
		if (gameState == ACTIVE){
			return;
		}
		
		g.setColor( gameState == WON? Color.GREEN: Color.RED);
		
		if (endCount < 60){
			g.fillRect(600/2 - 5 - endCount * 5, 600/2 - 5 - endCount * 5, endCount * 10 + 10, endCount * 10 + 10);
			
			endCount++;
		} else{
			g.fillRect(0, 0, 600, 600);
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		paintComponent(this.getGraphics());
	}

}
