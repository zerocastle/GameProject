package doublebuffering;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EnemyClass {
	Image enemy; List<Image>enemyList = null; int enemyMax = 10;
	JFrame frame = null; 
	
	private void EmeymyClass(JFrame frame) {
		// TODO Auto-generated method stub
		// 이미지 불러오기
		ImageIcon enemyIcon = new ImageIcon(getClass().getResource("/image/enemy.png"));
		enemy = enemyIcon.getImage();
		
		this.frame = frame;
	}
}
