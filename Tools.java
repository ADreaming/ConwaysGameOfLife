package GameOfLife;

import java.awt.Dimension;

public class Tools {
	public static final int MODE_PENCIL = 0;
	public static final int MODE_BLOCK = 1;
	public static final int MODE_BEEHIVE = 2;
	public static final int MODE_LOAF = 3;
	public static final int MODE_BOAT = 4;
	public static final int MODE_BLINKER = 5;
	public static final int MODE_TOAD = 6;
	public static final int MODE_BEACON = 7;
	public static final int MODE_PULSAR = 8;
	public static final int MODE_PENTA = 9;
	public static final int MODE_GLIDER = 10;
	public static final int MODE_SHIP = 11;
	
	private static final int[] PENCIL = {
		1
	};
	private static final int[] BLOCK =  {
		1, 1,
		1, 1
	};
	private static final int[] BEEHIVE =  {
		0, 1, 1, 0,
		1, 0, 0, 1,
		0, 1, 1, 0
	};
	private static final int[] LOAF =  {
		0, 1, 1, 0,
		1, 0, 0, 1,
		0, 1, 0, 1,
		0, 0, 1, 0
	};
	private static final int[] BOAT =  {
		1, 1, 0,
		1, 0, 1,
		0, 1, 0
	};
	private static final int[] BLINKER =  {
		0, 0, 0,
		1, 1, 1,
		0, 0, 0,
	};
	private static final int[] TOAD =  {
		0, 0, 0, 0,
		0, 1, 1, 1,
		1, 1, 1, 0,
		0, 0, 0, 0,
	};
	private static final int[] BEACON =  {
		1, 1, 0, 0,
		1, 1, 0, 0,
		0, 0, 1, 1,
		0, 0, 1, 1,
	};
	private static final int[] PULSAR =  {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	};
	private static final int[] PENTA =  {
		0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 1, 0, 0, 0, 0,
		0, 0, 0, 0, 1, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 1, 0, 0, 0, 0,
		0, 0, 0, 0, 1, 0, 0, 0, 0,
		0, 0, 0, 1, 1, 1, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0,
	};
	private static final int[] GLIDER =  {
		0, 0, 1,
		1, 0, 1,
		0, 1, 1,
	};
	private static final int[] SHIP =  {
		1, 0, 0, 1, 0,
		0, 0, 0, 0, 1,
		1, 0, 0, 0, 1,
		0, 1, 1, 1, 1
	};
	
	private static final Dimension PENCIL_DIM = new Dimension(1, 1);
	private static final Dimension BLOCK_DIM = new Dimension(2, 2);
	private static final Dimension BEEHIVE_DIM = new Dimension(4, 3);
	private static final Dimension LOAF_DIM = new Dimension(4, 4);
	private static final Dimension BOAT_DIM = new Dimension(3, 3);
	private static final Dimension BLINKER_DIM = new Dimension(3, 3);
	private static final Dimension TOAD_DIM = new Dimension(4, 4);
	private static final Dimension BEACON_DIM = new Dimension(4, 4);
	private static final Dimension PULSAR_DIM = new Dimension(15, 15);
	private static final Dimension PENTA_DIM = new Dimension(9, 16);
	private static final Dimension GLIDER_DIM = new Dimension(3, 3);
	private static final Dimension SHIP_DIM = new Dimension(5, 4);
	
	public static ToolPack getToolPack(int toolMode){
		switch (toolMode){
		case MODE_PENCIL:
			return new ToolPack(PENCIL, PENCIL_DIM);
		case MODE_BLOCK:
			return new ToolPack(BLOCK, BLOCK_DIM);
		case MODE_BEEHIVE:
			return new ToolPack(BEEHIVE, BEEHIVE_DIM);
		case MODE_LOAF:
			return new ToolPack(LOAF, LOAF_DIM);
		case MODE_BOAT:
			return new ToolPack(BOAT, BOAT_DIM);
		case MODE_BLINKER:
			return new ToolPack(BLINKER, BLINKER_DIM);
		case MODE_TOAD:
			return new ToolPack(TOAD, TOAD_DIM);
		case MODE_BEACON:
			return new ToolPack(BEACON, BEACON_DIM);
		case MODE_PULSAR:
			return new ToolPack(PULSAR, PULSAR_DIM);
		case MODE_PENTA:
			return new ToolPack(PENTA, PENTA_DIM);
		case MODE_GLIDER:
			return new ToolPack(GLIDER, GLIDER_DIM);
		case MODE_SHIP:
			return new ToolPack(SHIP, SHIP_DIM);
		default:
			return null;
		}
	}
}

class ToolPack{
	protected int[] blueprint;
	protected Dimension dim;
	
	public ToolPack(int[] blueprint, Dimension dim){
		this.blueprint = blueprint;
		this.dim = dim;
	}
}
