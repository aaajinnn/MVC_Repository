package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
//데이터소스 가져오기 위함
import javax.sql.DataSource;

public class BoardDAO {

	private DataSource ds; // 이름으로 찾아올거야
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// --------------------------

	// String ==> 불변성(immutable) 원본은 편집불가, 새로운 객체를 만들어서 문자열을 추가 또는 삭제 등을 수행함
	// StringBuffer / StringBuilder ==> 문자열 편집가능
	public int insertBoard(BoardVO vo) throws SQLException {
		try {
			con = ds.getConnection();
			StringBuffer buf = new StringBuffer("INSERT INTO mvc_board(")
					.append(" num, name, passwd, title, content, fileName, fileSize, readnum, wdate)")
					.append(" VALUES(mvc_board_seq.nextval, ?,?,?,?,?,?,0,sysdate)");
			String sql = buf.toString(); // StringBuffer을 문자열로 변환
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPasswd());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getFileName());
			ps.setLong(6, vo.getFileSize());

			return ps.executeUpdate();

		} finally {
			close();
		}
	}// --------------------------

	/** 총 게시글 수 */
	public int getTotalCount() throws SQLException {
		try {
			con = ds.getConnection();

			String sql = "SELECT count(num) cnt FROM mvc_board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// 단일행 반환
			if (rs.next()) {
				int count = rs.getInt("cnt");
				return count;
			}
			return 0; // resultSet했는데 없을시 0반환
		} finally {
			close();
		}

	}

	/** 게시글 목록 */
	public List<BoardVO> listBoard(int start, int end) throws Exception {
		try {
			con = ds.getConnection();
			// String sql = "SELECT * FROM mvc_board ORDER BY num DESC"; // paging처리 안할 때
			StringBuffer buf = new StringBuffer("select * from(").append(" select rownum rn, a.*")
					.append(" from (select * from mvc_board order by num desc) a)").append(" where rn between ? and ?"); // paging처리
																															// 할
																															// 때

			String sql = buf.toString();

			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);

			rs = ps.executeQuery();

			return makeList(rs);
		} finally {
			close();
		}
	}// ---------------------------

	/** 게시글보기 */
	public List<BoardVO> getBoard(int num) throws SQLException {
		try {
			con = ds.getConnection();

			String sql = "SELECT * FROM mvc_board WHERE num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);

			rs = ps.executeQuery();

			return makeList(rs);

		} finally {
			close();
		}
	}

	public List<BoardVO> makeList(ResultSet rs) throws SQLException {
		List<BoardVO> arr = new ArrayList<>();
		while (rs.next()) {
			int num = rs.getInt("num");
			String name = rs.getString("name");
			String passwd = rs.getString("passwd");
			String title = rs.getString("title");
			String content = rs.getString("content");
			java.sql.Date wdate = rs.getDate("wdate");
			int readnum = rs.getInt("readnum");
			String fileName = rs.getString("fileName");
			long fileSize = rs.getLong("fileSize");
			BoardVO record = new BoardVO(num, name, passwd, title, content, wdate, readnum, fileName, fileSize);
			arr.add(record);
		}
		return arr;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
