package cn.unicorn.mybatis.cache.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import cn.unicorn.mybatis.cache.vo.News;
import cn.unicorn.mybatis.util.MyBatisSessionFactory;
import junit.framework.TestCase;

public class TestNewsCRUD {
	private static Random random = new Random();
	private static int rand;
	static {
		rand = random.nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void testNewsAdd() throws Exception {
		News vo = new News();
		vo.setTitle("大新闻 - " + rand);
		vo.setPubdate(new Date());
		vo.setNote("我为长者续" + rand + "s");
		int len = MyBatisSessionFactory.getSession().insert("cn.unicorn.mapping.NewsNS.doCreate", vo);
		MyBatisSessionFactory.getSession().commit();// 提交更新事务
		TestCase.assertEquals(len, 1);
		System.out.println(vo);
		MyBatisSessionFactory.close();
	}

	@Test
	public void testNewsEdit() throws Exception {
		News vo = new News();
		vo.setNid(2L);
		vo.setTitle("搞基 " + rand);
		vo.setNote("ass we can" + rand);
		int len = MyBatisSessionFactory.getSession().update("cn.unicorn.mapping.NewsNS.doEdit", vo);
		MyBatisSessionFactory.getSession().commit();// 提交更新事务
		MyBatisSessionFactory.close();
		TestCase.assertEquals(len, 1);
	}

	@Test
	public void testNewsRemove() throws Exception {
		int len = MyBatisSessionFactory.getSession().update("cn.unicorn.mapping.NewsNS.doRemove", 1L);
		MyBatisSessionFactory.getSession().commit();// 提交更新事务
		MyBatisSessionFactory.close();
		TestCase.assertEquals(len, 1);
	}

	@Test
	public void testNewsFindById() throws Exception {
		// // 查询数据并且直接以Vo类型返回
		// News vo =
		// MyBatisSessionFactory.getSession().selectOne("cn.unicorn.mapping.NewsNS.findById",
		// 2L);
		// System.err.println(vo);
		// MyBatisSessionFactory.getSession().clearCache();//清空缓存
		// System.out.println("-------------------------------------");
		// News vob =
		// MyBatisSessionFactory.getSession().selectOne("cn.unicorn.mapping.NewsNS.findById",
		// 2L);
		// System.err.println(vob);
		SqlSessionFactory factory = MyBatisSessionFactory.getSessionFactory();// 获取SqlSessionFactory
		SqlSession sessionA = factory.openSession();// 开启SqlSession
		News vo = sessionA.selectOne("cn.unicorn.mapping.NewsNS.findById", 2L);
		System.err.println(vo);
		sessionA.close();
		System.out.println("-------------------------------------");
		SqlSession sessionB = factory.openSession();// 开启SqlSession
		News vob = sessionB.selectOne("cn.unicorn.mapping.NewsNS.findById", 2L);
		System.err.println(vob);
		sessionB.close();

	}

	@Test
	public void testNewsList() throws Exception {
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.unicorn.mapping.NewsNS.findAll");
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}

	@Test
	public void testNewsMap() throws Exception {
		// 使用Map集合进行处理,首先要设置使用的SQL语句,而后要设置一个描述key的列名称(此处的key为nid)
		Map<Long, Map<String, Object>> map = MyBatisSessionFactory.getSession()
				.selectMap("cn.unicorn.mapping.NewsNS.findMap", "nid");
		Iterator<Map.Entry<Long, Map<String, Object>>> newsMap = map.entrySet().iterator();
		while (newsMap.hasNext()) {
			Map.Entry<Long, Map<String, Object>> newsME = newsMap.next();
			System.err.print("key = " + newsME.getKey() + ",value: ");
			Map<String, Object> newsTemp = newsME.getValue();// 获取每一组数据
			Iterator<Map.Entry<String, Object>> iter = newsTemp.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> me = iter.next();
				System.out.println("\t|-" + me.getKey() + " = " + me.getValue());
			}
		}
		MyBatisSessionFactory.close();
	}

	@Test
	public void testSplit() throws Exception {
		Long currentPage = 1L;// 传递进来的处理参数
		Integer lineSize = 2;// 传递进来的处理参数
		String column = "title";// 传递进来的处理参数
		String keyWord = "搞";// 传递进来的处理参数
		Map<String, Object> map = new HashMap<>();
		map.put("startPage", (currentPage - 1) * lineSize);
		map.put("lineSize", lineSize);
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%");
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.unicorn.mapping.NewsNS.findSplit", map);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
		MyBatisSessionFactory.close();
	}

	@Test
	public void testSplitCount() {
		String column = "title";// 传递进来的处理参数
		String keyWord = "搞";// 传递进来的处理参数
		Map<String, Object> map = new HashMap<>();
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%");
		Long count = MyBatisSessionFactory.getSession().selectOne("cn.unicorn.mapping.NewsNS.getSplitCount", map);
		System.err.println(count);
		MyBatisSessionFactory.close();
	}
}
