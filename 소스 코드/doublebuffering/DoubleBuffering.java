package doublebuffering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DoubleBuffering extends JFrame implements Runnable {

	// 캐릭터 위치, 점수, 더블 버퍼링, 이미지 변수, 마우스 변수, 승리 판정 변수 선언

	int x, y, xDirection, yDirection, score = 0, rectX, rectY;
	Image dbImage;
	Graphics dbg;
	Image face, coin, icon;
	boolean mouseOnScreen;
	boolean won = false;

	// 기본적인 설정
	private final int WIDTH = 1600; // 가로 화면 크기
	private final int HEIGHT = 900; // 세로 화면크기

	private final int circleSize = 32; // 동전 먹는거 판단
	private final int rectSize = 32; // 동전 먹는거 판단
	private final Font font = new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 30);

	// 음악 관련 설정
	// static InputStream IN;
	// static AudioStream BGM;
	// static AudioPlayer MGP = AudioPlayer.player;

	// 쓰레드 실행 함수
	public void run() {
		try {
			while (true) {
				move();
				Thread.sleep(5);
			}
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다.");
		}
	}

	// 실제로 캐릭터가 이동하는 함수
	public void move() {
		x += xDirection;
		y += yDirection;
		if (x <= 0)
			x = 0;
		if (x >= WIDTH - circleSize)
			x = WIDTH - circleSize;
		if (y <= circleSize)
			y = circleSize;
		if (y >= WIDTH - circleSize)
			y = WIDTH - circleSize;
	}

	// 방향 지정
	public void setXDirection(int xdir) {
		xDirection = xdir;
	}

	public void setYDirection(int ydir) {
		yDirection = ydir;
	}

	// 마우스 이벤트 처리
	public class MO extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int xCoord = e.getX(); // 마우스 클릭시 클릭시점에 x 축 좌표 봔환
			int yCoord = e.getY(); // 마우스 클릭시 클릭시점에 y 축 좌표 반환
			x = xCoord - circleSize / 2;
			y = yCoord - circleSize / 2;
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mouseOnScreen = true;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			mouseOnScreen = false;
		}
	}
	// ===========================================

	// 키보드 이벤트 처리
	public class AL implements KeyEventDispatcher {
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				// 키보들르 눌렀을때 처리
				int keyCode = e.getKeyCode();
				if (keyCode == e.VK_LEFT) {
					setXDirection(-1);
				}
				if (keyCode == e.VK_RIGHT) {
					setXDirection(+1);
				}
				if (keyCode == e.VK_UP) {
					setYDirection(-1);
				}
				if (keyCode == e.VK_DOWN) {
					setYDirection(+1);
				}
			}
			if (e.getID() == KeyEvent.KEY_RELEASED) {
				int keyCode = e.getKeyCode();
				if (keyCode == e.VK_LEFT) {
					setXDirection(0);
				}
				if (keyCode == e.VK_RIGHT) {
					setXDirection(0);
				}
				if (keyCode == e.VK_UP) {
					setYDirection(0);
				}
				if (keyCode == e.VK_DOWN) {
					setYDirection(0);
				}
			}
			return false;
		}
	}

	// ======================================

	public DoubleBuffering() {
		// 이미지 불러오기
		ImageIcon faceIcon = new ImageIcon(getClass().getResource("/image/face.png"));
		face = faceIcon.getImage();
		ImageIcon coinIcon = new ImageIcon(getClass().getResource("/image/coin.png"));
		coin = coinIcon.getImage();
		ImageIcon iconIcon = new ImageIcon(getClass().getResource("/image/icon.png"));
		icon = iconIcon.getImage();

		// 게임 기초 설정
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); //
		manager.addKeyEventDispatcher(new AL());
		addMouseListener(new MO());
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setIconImage(icon); // 아이콘 이미지 세팅

		setTitle("더블 버퍼링");
		setSize(WIDTH, HEIGHT);
		setResizable(false); // 사용자가 맘대로 크기를 줄이지 못함
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 중앙
		// 캐릭터 및 장애물 초기 설정
		// 캐릭 위치 중앙에 위치
		x = WIDTH / 2;
		y = HEIGHT / 2;

		rectX = new Random().nextInt(WIDTH - rectSize); // 랜덤한 x 좌표에 동전 생성
		rectY = new Random().nextInt(HEIGHT - rectSize * 2) + rectSize; // 랜덤한 y 좌표에 동전 생성

	}

	// 더블 버퍼링을 이용해 실제로 그리는 함수
	public void paint(Graphics g) {
		dbImage = createImage(WIDTH, HEIGHT);
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	// 더블 버퍼링에 사용되는 그리기 컴포넌트
	public void paintComponent(Graphics g) {

		// 캐릭터와 위치 정보 나타내기 그리고 코인 나타내기
		g.setFont(font);
		g.setColor(Color.WHITE);

		// 승리 판정
		if (score >= 10) {
			g.drawString("승리하셨습니다!", 2, 62);
			won = true;
		} else
			g.drawString("현재 위치 : (" + x + ", " + y + ") 점수 : " + score, 2, 62);
		g.drawImage(face, x, y, this);
		g.drawImage(coin, rectX, rectY, this);

		// 코인과 캐릭터 충돌 감지
		if (rectX - circleSize < x && x < rectX + rectSize && rectY - circleSize < y && y < rectY + rectSize) {
			score++;
			rectX = new Random().nextInt(WIDTH - rectSize);
			rectY = new Random().nextInt(HEIGHT - rectSize * 2) + rectSize;
		}

		// 아직 승리하지 않은 경우에
		if (won == false)
			repaint();

	}

	// 메인 함수
	public static void main(String[] args) {

		// 클래스 인스턴스 객체 선언
//		DoubleBuffering db = new DoubleBuffering();
		Main login = new Main();

//		// 쓰레드 실행
//		Thread t1 = new Thread(db);
//		t1.start();
	}
}