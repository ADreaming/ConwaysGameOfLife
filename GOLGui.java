package GameOfLife;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class GOLGui implements ActionListener, MouseListener{
	private final GameOfLife INSTANCE;
	private JFrame main;
	

	private int timeCounter = 0;
	private JLabel time;
	private JTextField enLiveCount;
	private JTextField plLiveCount;
	private JTextField plConvertedCount;
	private JButton toggle;
	private JButton inc1;
	private JButton inc10;
	
	private GameBoard gameBoard;
	private ArrayList<JButton> tools;
	private int toolMode;
	
	public GOLGui(GameOfLife instance){
		INSTANCE = instance;
		toolMode = Tools.MODE_PENCIL;
		createWindow();
	}
	
	public void start(UpdatePack up){
		if (INSTANCE.getGameMode() == GameOfLife.MODE_GAME){
			time.setText("180");
		} else{
			time.setText("0");
		}
		
		gameBoard.tick(up.ca);
	}
	
	protected void tick(UpdatePack up, boolean withTimeIncrement){
		int crement = INSTANCE.getGameMode() == GameOfLife.MODE_GAME? -1: 1;
		if (++timeCounter == 4){
			time.setText("" + (Integer.parseInt(time.getText()) + crement));
			timeCounter = 0;
		}
		
		enLiveCount.setText(up.en + "");
		plLiveCount.setText(up.pl + "");
		plConvertedCount.setText(Integer.parseInt(plConvertedCount.getText()) + up.cc + "");
	}
	
	private void createWindow(){
		main = new JFrame("The Game of Life");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JMenuBar menu = new JMenuBar();
				JMenu opt = new JMenu("Options");
					JMenu newG = new JMenu("New Game");
						JMenuItem gSmall = new JMenuItem("Small");
						gSmall.setActionCommand("gSmall");
						gSmall.addActionListener(this);
						newG.add(gSmall);

						JMenuItem gMed = new JMenuItem("Medium");
						gMed.setActionCommand("gMed");
						gMed.addActionListener(this);
						newG.add(gMed);

						JMenuItem gLarge = new JMenuItem("Large");
						gLarge.setActionCommand("gLarge");
						gLarge.addActionListener(this);
						newG.add(gLarge);
					opt.add(newG);
					JMenu newS = new JMenu("New Sandbox");
						JMenuItem sSmall = new JMenuItem("Small");
						sSmall.setActionCommand("sSmall");
						sSmall.addActionListener(this);
						newS.add(sSmall);
	
						JMenuItem sMed = new JMenuItem("Medium");
						sMed.setActionCommand("sMed");
						sMed.addActionListener(this);
						newS.add(sMed);
	
						JMenuItem sLarge = new JMenuItem("Large");
						sLarge.setActionCommand("sLarge");
						sLarge.addActionListener(this);
						newS.add(sLarge);
					opt.add(newS);
					JMenuItem exit = new JMenuItem("Exit");
						exit.setActionCommand("exit");
						exit.addActionListener(this);
					opt.add(exit);
				menu.add(opt);
				JMenu about = new JMenu("About");
					JMenuItem htp = new JMenuItem("How to Play");
						htp.setActionCommand("htp");
						htp.addActionListener(this);
					about.add(htp);
					
					JMenuItem about1 = new JMenuItem("About");
						about1.setActionCommand("about");
						about1.addActionListener(this);
					about.add(about1);
				menu.add(about);
			main.setJMenuBar(menu);
			
			JPanel mainArea = new JPanel();
			mainArea.setLayout(new BoxLayout(mainArea, BoxLayout.PAGE_AXIS));
			
				createRigidArea(mainArea, "X", Component.LEFT_ALIGNMENT);
			
				//VIEW PANEL
				JPanel viewPanel = new JPanel();
				viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.LINE_AXIS));

					createRigidArea(viewPanel, "Y", Component.TOP_ALIGNMENT);
					
					JPanel enemyInfo = new JPanel();
					enemyInfo.setPreferredSize(new Dimension(150, 150));
					enemyInfo.setMaximumSize(new Dimension(150, 150));
					enemyInfo.setAlignmentY(Component.TOP_ALIGNMENT);
					enemyInfo.setBackground(Color.WHITE);
						JLabel enLive = new JLabel("Enemy Live Cells:");
						enemyInfo.add(enLive);
						
						enLiveCount = new JTextField("0");
						enLiveCount.setColumns(5);
						enLiveCount.setEditable(false);
						enemyInfo.add(enLiveCount);
					viewPanel.add(enemyInfo);
					
					createRigidArea(viewPanel, "Y", Component.TOP_ALIGNMENT);
					
					gameBoard = new GameBoard(this.INSTANCE, INSTANCE.getSize());
					gameBoard.setPreferredSize(new Dimension(600, 600));
					gameBoard.setAlignmentY(Component.TOP_ALIGNMENT);
					gameBoard.setBackground(Color.WHITE);
					gameBoard.addMouseListener(this);
					viewPanel.add(gameBoard);
					
					createRigidArea(viewPanel, "Y", Component.TOP_ALIGNMENT);
					
					JPanel playerInfo = new JPanel();
					playerInfo.setPreferredSize(new Dimension(150, 150));
					playerInfo.setMaximumSize(new Dimension(150, 150));
					playerInfo.setAlignmentY(Component.TOP_ALIGNMENT);
					playerInfo.setBackground(Color.WHITE);
						JLabel plLive = new JLabel("Player Live Cells:");
						playerInfo.add(plLive);
						
						plLiveCount = new JTextField("0");
						plLiveCount.setColumns(5);
						plLiveCount.setEditable(false);
						playerInfo.add(plLiveCount);
						
						JLabel plConverted = new JLabel("Converted Cells:");
						playerInfo.add(plConverted);
						
						plConvertedCount = new JTextField("0");
						plConvertedCount.setColumns(5);
						plConvertedCount.setEditable(false);
						playerInfo.add(plConvertedCount);
					viewPanel.add(playerInfo);
					
					createRigidArea(viewPanel, "Y", Component.TOP_ALIGNMENT);
					
				mainArea.add(viewPanel);
			
				createRigidArea(mainArea, "X", Component.LEFT_ALIGNMENT);
				
				//CONTROL PANEL
				tools = new ArrayList<JButton>();
				JPanel contPanel = new JPanel();
				contPanel.setLayout(new BoxLayout(contPanel, BoxLayout.PAGE_AXIS));
				
					JPanel controls = new JPanel();
					controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));
						toggle = new JButton("Play");
						toggle.setActionCommand("togglePlay");
						toggle.addActionListener(this);
						controls.add(toggle);
						
						createRigidArea(controls, "X", Component.LEFT_ALIGNMENT);

						inc1 = new JButton("Increment 1");
						inc1.setEnabled(false);
						inc1.setActionCommand("inc1");
						inc1.addActionListener(this);
						controls.add(inc1);
						
						createRigidArea(controls, "X", Component.LEFT_ALIGNMENT);

						inc10 = new JButton("Increment 10");
						inc10.setEnabled(false);
						inc10.setActionCommand("inc10");
						inc10.addActionListener(this);
						controls.add(inc10);

						createRigidArea(controls, "X", Component.LEFT_ALIGNMENT);
						
						time = new JLabel("0000");
						controls.add(time);
					contPanel.add(controls);

					createRigidArea(contPanel, "Y", Component.LEFT_ALIGNMENT);
					
					JPanel blockTools = new JPanel();
					blockTools.setLayout(new BoxLayout(blockTools, BoxLayout.LINE_AXIS));
						JButton pencil = new JButton("Pencil");
						pencil.setActionCommand("pencil");
						pencil.addActionListener(this);
						pencil.setEnabled(false);
						blockTools.add(pencil);
						tools.add(pencil);
						
						JButton block = new JButton("Block");
						block.setActionCommand("block");
						block.addActionListener(this);
						block.setEnabled(true);
						blockTools.add(block);
						tools.add(block);
						
						JButton beehive = new JButton("Beehive");
						beehive.setActionCommand("beehive");
						beehive.addActionListener(this);
						beehive.setEnabled(true);
						blockTools.add(beehive);
						tools.add(beehive);
						
						JButton loaf = new JButton("Loaf");
						loaf.setActionCommand("loaf");
						loaf.addActionListener(this);
						loaf.setEnabled(true);
						blockTools.add(loaf);
						tools.add(loaf);
						
						JButton boat = new JButton("Boat");
						boat.setActionCommand("boat");
						boat.addActionListener(this);
						boat.setEnabled(true);
						blockTools.add(boat);
						tools.add(boat);
					contPanel.add(blockTools);
					
					JPanel oscTools = new JPanel();
					oscTools.setLayout(new BoxLayout(oscTools, BoxLayout.LINE_AXIS));
						JButton blinker = new JButton("Blinker");
						blinker.setActionCommand("blinker");
						blinker.addActionListener(this);
						blinker.setEnabled(true);
						oscTools.add(blinker);
						tools.add(blinker);
	
						JButton toad = new JButton("Toad");
						toad.setActionCommand("toad");
						toad.addActionListener(this);
						toad.setEnabled(true);
						oscTools.add(toad);
						tools.add(toad);
	
						JButton beacon = new JButton("Beacon");
						beacon.setActionCommand("beacon");
						beacon.addActionListener(this);
						beacon.setEnabled(true);
						oscTools.add(beacon);
						tools.add(beacon);
	
						JButton pulsar = new JButton("Pulsar");
						pulsar.setActionCommand("pulsar");
						pulsar.addActionListener(this);
						pulsar.setEnabled(true);
						oscTools.add(pulsar);
						tools.add(pulsar);
	
						JButton penta = new JButton("Pentadecathlon");
						penta.setActionCommand("penta");
						penta.addActionListener(this);
						penta.setEnabled(true);
						oscTools.add(penta);
						tools.add(penta);
					contPanel.add(oscTools);
					
					JPanel shipTools = new JPanel();
					shipTools.setLayout(new BoxLayout(shipTools, BoxLayout.LINE_AXIS));
						JButton glider = new JButton("Glider");
						glider.setActionCommand("glider");
						glider.addActionListener(this);
						glider.setEnabled(true);
						shipTools.add(glider);
						tools.add(glider);
	
						JButton ship = new JButton("Spaceship");
						ship.setActionCommand("ship");
						ship.addActionListener(this);
						ship.setEnabled(true);
						shipTools.add(ship);
						tools.add(ship);
					contPanel.add(shipTools);
				
				mainArea.add(contPanel);
				
			main.add(mainArea);

		main.pack();
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
	
	public int getTime(){
		return Integer.parseInt(time.getText());
	}
	
	public boolean isStarted(){
		return INSTANCE.isStarted();
	}
	
	public GameBoard getBoard(){
		return gameBoard;
	}
	
	public JFrame getMain(){
		return main;
	}
	
	private void createRigidArea(JPanel p, String axis, float alignment){
		JComponent box = (JComponent) Box.createRigidArea(new Dimension(10, 10));
		
		if (axis.equals("Y")){
			box.setAlignmentY(alignment);
		} else if (axis.equals("X")){
			box.setAlignmentX(alignment);
		}
		
		p.add(box);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "togglePlay":
			if(!isStarted()){
				INSTANCE.start();
				inc1.setEnabled(true);
				inc10.setEnabled(true);
				break;
			}
			
			if(INSTANCE.isPaused()){
				INSTANCE.start();
				toggle.setText("Plause");
				inc1.setEnabled(false);
				inc10.setEnabled(false);
			} else{
				INSTANCE.pause();
				toggle.setText("Play");
				inc1.setEnabled(true);
				inc10.setEnabled(true);
			}
			break;
		case "inc1":
			INSTANCE.inc(1);
			break;
		case "inc10":
			INSTANCE.inc(10);
			break;
		case "pencil":
			toolMode = Tools.MODE_PENCIL;
			toggleTools(Tools.MODE_PENCIL);
			break;
		case "block":
			toolMode = Tools.MODE_BLOCK;
			toggleTools(Tools.MODE_BLOCK);
			break;
		case "beehive":
			toolMode = Tools.MODE_BEEHIVE;
			toggleTools(Tools.MODE_BEEHIVE);
			break;
		case "loaf":
			toolMode = Tools.MODE_LOAF;
			toggleTools(Tools.MODE_LOAF);
			break;
		case "boat":
			toolMode = Tools.MODE_BOAT;
			toggleTools(Tools.MODE_BOAT);
			break;
		case "blinker":
			toolMode = Tools.MODE_BLINKER;
			toggleTools(Tools.MODE_BLINKER);
			break;
		case "toad":
			toolMode = Tools.MODE_TOAD;
			toggleTools(Tools.MODE_TOAD);
			break;
		case "beacon":
			toolMode = Tools.MODE_BEACON;
			toggleTools(Tools.MODE_BEACON);
			break;
		case "pulsar":
			toolMode = Tools.MODE_PULSAR;
			toggleTools(Tools.MODE_PULSAR);
			break;
		case "penta":
			toolMode = Tools.MODE_PENTA;
			toggleTools(Tools.MODE_PENTA);
			break;
		case "glider":
			toolMode = Tools.MODE_GLIDER;
			toggleTools(Tools.MODE_GLIDER);
			break;
		case "ship":
			toolMode = Tools.MODE_SHIP;
			toggleTools(Tools.MODE_SHIP);
			break;
			
		case "gSmall":
			INSTANCE.newGame(true, GameOfLife.MODE_GAME, GameOfLife.SIZESMALL);
			break;
		case "gMed":
			INSTANCE.newGame(true, GameOfLife.MODE_GAME, GameOfLife.SIZEMEDIUM);
			break;
		case "gLarge":
			INSTANCE.newGame(true, GameOfLife.MODE_GAME, GameOfLife.SIZELARGE);
			break;
		case "sSmall":
			INSTANCE.newGame(false, GameOfLife.MODE_SAND, GameOfLife.SIZESMALL);
			break;
		case "sMed":
			INSTANCE.newGame(false, GameOfLife.MODE_SAND, GameOfLife.SIZEMEDIUM);
			break;
		case "sLarge":
			INSTANCE.newGame(false, GameOfLife.MODE_SAND, GameOfLife.SIZELARGE);
			break;
		case "exit":
			main.dispose();
			break;
		case "htp":
			JOptionPane.showMessageDialog(main, "Welcome to the Game of Life!\r\n\r\nUse Sandbox Mode to play without competing against an enemy!\r\n\r\nIn Competitive Mode, use the Cell Tools (Pen through Spaceship) to place your own cells on the plane\r\nYour cells (green) will grow and overtake the enemy cells (red)!\r\nYou have 180 seconds to clear the field, otherwise you lose!\r\n\r\nPress Play to begin in Paused mode. Press Play again to start the timer!\r\nYou can pause at any time to add cells!");
			break;
		case "about":
			JOptionPane.showMessageDialog(main, "Made by Jasmine McNeil\r\nUses MVC organization (I swear!)\r\nVersion Infinity.0");
			break;
		default:
			break;
		}
		
	}
	
	private void toggleTools(int tool){
		for (JButton j: tools){
			j.setEnabled(true);
		}
		
		if (tool > -1){
			tools.get(tool).setEnabled(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (isStarted()){
			INSTANCE.handleCellRevive(new Point(arg0.getX(), arg0.getY()), toolMode);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
