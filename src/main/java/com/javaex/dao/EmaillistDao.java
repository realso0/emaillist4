package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.EmaillistVo;


@Repository //내가 dao 야, 나만 뒤지면 돼
public class EmaillistDao {
//	@Autowired //connection 자동 연결 준비(미리 접속해두기)
//	private OracleDataSource oracleDataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<EmaillistVo> getList() {
		List<EmaillistVo> list = sqlSession.selectList("emaillist.getList"); //앞에는 쿼리문의 위치, 뒤에는 입력받는 값
		return list;
	}
	
	public void insert(EmaillistVo emaillistVo) {
		sqlSession.insert("emaillist.insert", emaillistVo);
		int count=sqlSession.insert("emaillist.insert", emaillistVo);
		System.out.println(count); //1건 추가완료 됬는다는 것을 출력함.
	}
	
//	public List<EmaillistVo> getListByMap() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("last_name", "정"); //(키,값) 마땅한 vo가 없으면, map이라는 객체를 만들어서 사용
//		map.put("email", "nnnn");
//		List<EmaillistVo> list = sqlSession.selectList("emaillist.getListMap",map);
//		return list;
//	}
}

