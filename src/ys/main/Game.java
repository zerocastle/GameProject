package ys.main;

import javax.swing.JButton;

public class Game extends JButton implements Runnable{

	public Game() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setSize(50,50);
		this.setText("hellow");
		this.setVisible(true);
		
	}
	
	

}
