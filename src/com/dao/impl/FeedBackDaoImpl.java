package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.entity.FeedBack;
@Repository("feedBackDao")
public class FeedBackDaoImpl implements FeedBackDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<FeedBack> getFeedBackList(Integer isReplied, String order) {
		System.out.println("我看到了order：" + order);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("isReplied", isReplied);
		args.put("order", order);
		List<FeedBack> list = sqlSession.selectList("selectAllFeedBack", args);
		return list;
	}

	@Override
	public List<FeedBack> getFeedBackListByUserId(String userId) {
		List<FeedBack> list = sqlSession.selectList("selectFeedBackListByUserId", userId);
		return list;
	}

	@Override
	public FeedBack getFeedBackById(int fid) {
		FeedBack fb = sqlSession.selectOne("selectFeedBackListById", fid);
		return fb;
	}

	@Override
	public int insertFeedBack(FeedBack fb) {
		int row =sqlSession.insert("insertFeedBack", fb);
		sqlSession.commit();
		return row;
	}

	@Override
	public int updateFeedBack(FeedBack fb) {
		int result = sqlSession.update("updateFeedBack", fb);
		sqlSession.commit();
		return result;
	}


}