package ys.db;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



class MyFrame extends JFrame{
	public MyFrame() {
		// TODO Auto-generated constructor stub
		ShowRank show = new ShowRank();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.add(show);
		show.setLayout(new BoxLayout(show, BoxLayout.Y_AXIS));
		this.setVisible(true);
	}
}


public class ShowRank extends JPanel{

	JPanel panel = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet set = null;

	String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUser = "practice";
	String dbPassword = "123456";

	String query = "select * from gameMember order by score desc";
	
	public ShowRank() {
		// TODO Auto-generated constructor stub
		this.showRankList();
	}
	// 일딴 테스트까지해봄 생성자부터 붙이기 작업 처리하기 

	
//	public ShowRank(JPanel panel) {
//		// TODO Auto-generated constructor stub
//		this.panel = panel;
//		this.showRankList();
////		this.panel.setFocusable(true);
//		this.panel.setVisible(true);
//	}

	public void showRankList() {
		// TODO Auto-generated method stub
		int counter = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			pstmt = con.prepareStatement(query);
			set = pstmt.executeQuery();

			while (set.next()) {
				++counter;
				this.add(new JLabel(counter + "순위 : " + set.getString("name") + set.getString("score")));
				System.out.println(set.getString("name"));
				
			}
			this.setVisible(true);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if(con != null)
					con.close();
				if(pstmt != null)
					pstmt.close();
				if(set != null)
					set.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
