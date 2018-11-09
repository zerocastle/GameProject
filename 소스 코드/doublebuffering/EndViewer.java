package doublebuffering;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EndViewer extends JPanel{
	
	public EndViewer(DoubleBuffering doubleBuffering) {
		// TODO Auto-generated constructor stub
		
		JButton button = new JButton("랭킹등록 할래");
		JButton button2 = new JButton("랭킹볼래");
		
		this.add(button);
		this.add(button2);
//		doubleBuffering.setVisible(false);
		this.setVisible(true);
		doubleBuffering.add(this);
	}

}
