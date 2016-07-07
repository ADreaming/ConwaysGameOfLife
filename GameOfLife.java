package GameOfLife;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameOfLife implements ActionListener{
	public static final int SIZESMALL = 40;
	public static final int SIZEMEDIUM = 60;
	public static final int SIZELARGE = 120;
	public static final int MODE_GAME = 0;
	public static final int MODE_SAND = 1;
	
	private Timer timer;
	private GOLGui gui;
	private GameBoard gameBoard;
	private int gameSize;
	private int gameMode;
	
	private Cell[] currentGen;
	private boolean isStarted;
	private boolean isPaused;
	
	
	public GameOfLife(){
		gameSize = SIZEMEDIUM;
		gameMode = MODE_GAME;
		isStarted = false;
		isPaused = true;
		gui = new GOLGui(this);
		gameBoard = gui.getBoard();
		currentGen = null;

		timer = new Timer(250, this);
		timer.setActionCommand("tick");
	}
	
	public void start(){
		if (isStarted){
			timer.start();
			isPaused = false;
		} else{
			isStarted = true;
			
			Cell[] starter;
			
			if (gameMode == MODE_GAME){
				currentGen = CellLogic.createSeed(gameSize);
				starter = currentGen;
			} else{
				currentGen = new Cell[gameSize * gameSize];
				starter = currentGen;
				
				for (int i = 0; i < starter.length; i++){
					starter[i] = new Cell(Cell.DEAD);
				}
				
			}
			
			UpdatePack up = new UpdatePack(starter, 0, 0, 0);
			gui.start(up);
			
		}
	}
	
	public void pause(){
		timer.stop();
		isPaused = true;
	}
	
	public void inc(int i){
		for (int inc = 0; inc < i; inc++){
			UpdatePack up = CellLogic.prepareUpdate(currentGen, gameSize);
			currentGen = up.ca;
			gui.tick(up, true);
		}
	}

	public void handleCellRevive(Point point, int toolMode) {
		UpdatePack up = CellLogic.addCellsAt(gameSize, currentGen, gameBoard.getCellAt(point), toolMode);
		gui.tick(up, false);
		gameBoard.tick(up.ca);
		
	}
	
	public boolean isStarted(){
		return isStarted;
	}
	
	public boolean isPaused(){
		return isPaused;
	}
	
	public int getSize(){
		return gameSize;
	}
	
	public void setSize(int size){
		gameSize = size;
	}
	
	public int getGameMode(){
		return gameMode;
	}
	
	public void setGameMode(int mode){
		gameMode = mode;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("tick")){
			UpdatePack up = CellLogic.prepareUpdate(currentGen, gameSize);
			currentGen = up.ca;
			gui.tick(up, true);
			gameBoard.tick(up.ca);
			
			if(gameMode == MODE_GAME){
				int i = gui.getTime();
				
				if(up.en == 0){
					timer.stop();
					gameBoard.displayWin();
				} else if(i == 0){
					timer.stop();
					gameBoard.displayLoss();
				}
			}
		}
	}

	public void newGame(boolean isGame, int gameMode, int gameSize) {
		timer.stop();
		gui.getMain().dispose();
		
		this.gameSize = gameSize;
		this.gameMode = gameMode;
		isStarted = false;
		isPaused = true;
		gui = new GOLGui(this);
		gameBoard = gui.getBoard();
		currentGen = null;
	}
	
	public static void main(String[] args){
		new GameOfLife();
	}
}
