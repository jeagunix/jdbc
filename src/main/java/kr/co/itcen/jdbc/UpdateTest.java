package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static Boolean update(Long no, String name) {
		Boolean result = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.jdbc 연결하기
			String url = "jdbc:mariadb://192.168.1.43:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결성공");

			// 3.Statement 객체 생성(받아오기)
			stmt = connection.createStatement();

			// 4. SQL문 실행
			String sql = "update department set name ='" + name + "'where no= " + no;
			int count = stmt.executeUpdate(sql);

			result = (count == 1);

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static Boolean update2(Long no, String name) {
		Boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1.JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.jdbc 연결하기
			String url = "jdbc:mariadb://192.168.1.43:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결성공");

			// 3.SQL문 준비시키기
			String sql = "update department set name = ? where no =?";
			pstmt = connection.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, name);
			pstmt.setLong(2, no);

			// 4. SQL문 실행
			
			int count = pstmt.executeUpdate(sql);

			result = (count == 1);

		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	public static void main(String[] args) {
		update(1L, "총무1팀");
		update(2L, "영업1팀");
		update(3L, "인사1팀");

	}

}
