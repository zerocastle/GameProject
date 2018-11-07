package ys.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainScreen extends JFrame implements ActionListener {
	// private ImageIcon backgroundImage = new ImageIcon("images/background.png");
	private BufferedImage backgroundImage2 = null;
	private Image iconImage = null;

	JTextField score = null;
	JButton start = null;
	JButton stop = null;
	JButton again = null;

	public MainScreen() {
		// TODO Auto-generated constructor stub
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.iconImage = kit.getImage("images/icon.png");
		this.setIconImage(iconImage);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("번개피하기");
		setResizable(false);
		Container cont = getContentPane();
		Font style1 = new Font("돋움", Font.PLAIN, 11);
		Font style2 = new Font("돋움", Font.PLAIN, 40);
		// 점수
		score = new JTextField("000", 5);
		score.setFont(style2);
		score.setBounds(1100, 10, 100, 60);
		// 시작
		start = new JButton("시작");
		start.setFont(style1);
		start.addActionListener(this);
		start.setBounds(1100, 80, 100, 60);
		// 멈춤
		stop = new JButton("멈춤");
		stop.setFont(style1);
		// stop.addMouseListener(this);
		stop.setBounds(1100, 150, 100, 60);
		// 다시 시작
		again = new JButton("다시시작");
		again.setFont(style1);
		// again.addMouseListener(this);
		again.setBounds(1100, 220, 100, 60);

		// 매인
		JLabel mainLabel = new MainLabel();
		mainLabel.setBounds(10, 10, 1000, 700);

		JPanel panel = new JPanel(null);
		panel.add(score); // 점수
		panel.add(start); // 시작
		panel.add(stop); // 멈췀
		panel.add(again); // 다시시작
		panel.add(mainLabel); // 메인 폼
		this.setBackground(Color.cyan);
		cont.add(panel);

		setContentPane(cont);

		this.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.start.setEnabled(false);
		Thread t1 = new Thread(new MainLabel());
		t1.start();

	}

	class MainLabel extends JLabel implements Runnable {
		public MainLabel() {
			// TODO Auto-generated constructor stub
			try {
				backgroundImage2 = ImageIO.read(new File("images/background.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			// 그림그려준다 .
			// TODO Auto-generated method stub
			g.drawImage(backgroundImage2, 0, 0, null);
			repaint();
		}

		@Override
		public Dimension getPreferredSize() {
			// TODO Auto-generated method stub
			// 현재 백그라운드 이미지 의 가로 , 세로 넓이를 null 로 화면에 맞추어준다.
			return new Dimension(backgroundImage2.getWidth(null), backgroundImage2.getHeight(null));
		}

		@Override
		public synchronized void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 10000; i++) {
				try {
					Thread.sleep(1000);
					//int -> String
					score.setText(Integer.toString(i));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	

}
