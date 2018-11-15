package doublebuffering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ys.db.ShowRank;

// 
class EventRnak implements ActionListener {
	JPanel panel = null;

	public EventRnak(EndViewer endViewer) {
		// TODO Auto-generated method stub
		this.panel = endViewer;
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		JButton temp = (JButton) a.getSource();
		String command = temp.getText();
		if (command.equals("랭킹보기!!!")) {
			System.out.println("들어옴?");
			panel.setVisible(false);
			temp.setVisible(false);
			new ShowRank(panel);

		}

	}

}

public class EndViewer extends JPanel {

	public static Stack<Object> PAGE = null; // 이전 페이지와 다음 페이지를 불러올떄 스텍을 이용해서 불러온다.

	JButton button = null;
	JButton button2 = null;
//	Graphics gp = null;

	JFrame frame = new JFrame();

	public EndViewer(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.setSize(DoubleBuffering.WIDTH, DoubleBuffering.HEIGHT);
		PAGE = new Stack<Object>(); // EndViewer 객체를 넣어준다 . 두번쨰 페이지로
		PAGE.push(this);
		button = new JButton("랭킹등록!!!");
		button2 = new JButton("랭킹보기!!!");

		this.add(button);
		this.add(button2);

		button.addActionListener(new EventRnak(this));
		button2.addActionListener(new EventRnak(this));

		this.frame.add(this);
		this.frame.setVisible(true);
	}

}
