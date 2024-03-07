package user.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * sqlSession : DB crud 관련한 메서드를 가지고 있다.
 * # Factory Pattern
 * [1] SqlSessionFactoryBuilder를 통해서 공장을 짓고(설계도 참조 : mybatis-config.xml)
 * [2] SqlSessionFactory 가 생성되면 공장을 통해 sqlSession(제품)을 얻어온다.
 * */
public class MemberDAOMyBatis {

	// namespace : 어떤 mapper를 사용할 지 네임스페이스를 보고 찾는다.(네임스페이스 지정 필수!!)
	private final String NS = "user.model.MemberMapper";

	private SqlSession ses; // 제품. 공장에서 만들어짐 (crud작업가능)

	public SqlSessionFactory getSessionFactory() {
		String resource = "common/config/mybatis-config.xml"; // 설계도 파일
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource); // 설계도 파일을 읽을 수 있는 스트림
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 건축가
		SqlSessionFactory factory = builder.build(is); // 공장을 짓는다.
		return factory;
	}// -----------------------------------

	public int getMemberCount() {
		try {
			ses = this.getSessionFactory().openSession(); // 공장문열어제품뚝딱 //수동커밋
			int count = ses.selectOne(NS + ".userCount"); // userCount : memberMapper.xml에서 가져온 id 값
			return count;
		} finally {
			close();
		}
	}// -----------------------------------

	public int insertMember(MemberVO user) {
		try {
			// ses = this.getSessionFactory().openSession(); // 수동커밋
			ses = this.getSessionFactory().openSession(true);// 자동커밋 <==true
			int n = ses.insert(NS + ".insertMember", user); // user를 파라미터로 전달
			// 수동커밋일 때 트랜잭션 처리
//			if (n > 0) {
//				ses.commit();
//			} else {
//				ses.rollback();
//			}
			return n;
		} finally {
			close();
		}
	}// -----------------------------------

	public List<MemberVO> listMember(int start, int end) {
		try {
			// 1. mybatis는 하나의 객체밖에 얻지못함
			// ==>하나의 리스트가 아닌 여러가지 파라미터를 넣어야한다면 HashMap을 이용
			Map<String, Object> map = new HashMap<>();
			map.put("start", start); // integer객체가 저장
			map.put("end", end);
			// 2. 이렇게 HashMap에 담아서
			ses = this.getSessionFactory().openSession(true); // true 줘도되고 안줘도됨
			List<MemberVO> arr = ses.selectList(NS + ".listMember", map); // 3. list에 담아줌
			return arr;
		} finally {
			close();
		}
	}// -----------------------------------

	/** 회원 삭제 */
	public int deleteMember(String id) {
		try {
			ses = this.getSessionFactory().openSession(true);
			int n = ses.delete(NS + ".deleteMember", id);
			return n;
		} finally {
			close();
		}
	}// -----------------------------------

	private void close() {
		if (ses != null)
			ses.close();

	}// -----------------------------------

}