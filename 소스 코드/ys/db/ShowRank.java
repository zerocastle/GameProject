package ys.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ShowRank {

	JPanel panel = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet set = null;

	String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUser = "practice";
	String dbPassword = "123456";

	String query = "select * from gameMember order by score desc";

	public ShowRank(JPanel panel) {
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.showRankList();
//		this.panel.setFocusable(true);
		this.panel.setVisible(true);
	}

	public void showRankList() {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			pstmt = con.prepareStatement(query);
			set = pstmt.executeQuery();

			while (set.next()) {
				panel.add(new JTextField(set.getString("name") + set.getString("score")));
				System.out.println(set.getString("name"));
				
			}
			panel.setVisible(true);
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

}
