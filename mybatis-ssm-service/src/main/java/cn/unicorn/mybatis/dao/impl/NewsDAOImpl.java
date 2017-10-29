package cn.unicorn.mybatis.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import cn.unicorn.mybatis.dao.INewsDAO;
import cn.unicorn.mybatis.vo.News;

@Repository
public class NewsDAOImpl implements INewsDAO {
	@Resource
	private SqlSessionFactory sessionFactory;

	@Override
	public boolean doCreate(News vo) {
		return this.sessionFactory.openSession().insert("cn.unicorn.mapping.NewsNS.doCreate", vo) > 0;
	}

	@Override
	public List<News> findByIds(Set<Long> ids) {
		return this.sessionFactory.openSession().selectList("cn.unicorn.mapping.NewsNS.findByIds", ids.toArray());
	}

	@Override
	public List<News> findSplit(Map<String, Object> params) {
		return this.sessionFactory.openSession().selectList("cn.unicorn.mapping.NewsNS.findSplit", params);
	}

}
