package gameController;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.xml.soap.Text;

public class DBConnection {
	Connection conn;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	//DB연동
	public DBConnection(){
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "madang";
		String pwd = "madang";

		try { //드라이버를 찾는 과정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { //데이터 베이스를 연결하는 과정
			System.out.println("데이터베이스 연결준비...");
			conn = DriverManager.getConnection(url,userid, pwd);
			System.out.println("데이터 베이스 연결 성공");
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//회원가입
	public void userInsert(String id,String pw) {
		String sql = "Insert into gameuser values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean userCheck(String id) {
		String sql = "select userid from gameuser where userid = '"+id+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	//로그인
	public boolean userLogin(String id,String pw) {
		String sql = "select * from gameuser";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("userid").equals(id) && rs.getString("pw").equals(pw)) {
					return true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void setScore(String id,int score) {
		
		String sql = "insert into gamescore values(SEQ_gamescore.nextval,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, score);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public ResultSet viewRank() {
		String sql = "select s.userid,s.score "
				+ "from gamescore s,gameuser u "
				+ "where s.userid = u.userid "
				+ "order by score desc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//전체보기
	/*public ArrayList<BookBean> bookView() {
		String sql = "select * from book";
		ArrayList<BookBean> arr = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BookBean b = new BookBean();
				b.setNum(rs.getInt("num"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setInDate(rs.getString("inDate"));
				b.setOutDate(rs.getString("outDate"));
				b.setType(rs.getString("price"));
				b.setPrice(rs.getInt("price"));
				arr.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return arr;
	}*/
	//수정하기
	/*public void bookUpdate(int num,
			JTextField tfTitle,JTextField tfWriter, JTextField tfInDate,JTextField tfOutDate,
			JTextField tfBookType, JTextField tfPrice) {
		String sql = "Update book set title = ?,"
				+ "Writer = ?, InDate = ?, OutDate = ?,"
				+ "type = ?, price = ? where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tfTitle.getText());
			pstmt.setString(2, tfWriter.getText());
			pstmt.setString(3, tfInDate.getText());
			pstmt.setString(4, tfOutDate.getText());
			pstmt.setString(5, tfBookType.getText());
			pstmt.setString(6, tfPrice.getText());
			pstmt.setInt(7, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	//삭제하기
	/*public void bookDelete(int num) {
		String sql = "delete book where num = "+num;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}