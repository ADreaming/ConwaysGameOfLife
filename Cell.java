package GameOfLife;

public class Cell {
	static final int DEAD = 0;
	static final int ENEMY = 1;
	static final int PLAYER = 2;
	private final int state;
	
	public Cell(){
		state = DEAD;
	}
	
	public Cell(int state){
		this.state = state;
	}
	
	public int getState(){
		return state;
	}
	
	public boolean isAlive(){
		return state != 0;
	}
}
