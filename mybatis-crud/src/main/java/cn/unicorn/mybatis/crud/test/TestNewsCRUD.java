package cn.unicorn.mybatis.crud.test;

import java.util.Date;
import java.util.Random;

import org.junit.Test;

import cn.unicorn.mybatis.crud.vo.News;
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
		vo.setTitle("大新闻 - "+rand);
		vo.setPubdate(new Date());
		vo.setNote("我为长者续"+rand+"s");
		int len = MyBatisSessionFactory.getSession().insert("cn.unicorn.mapping.NewsNS.doCreate",vo);
		MyBatisSessionFactory.getSession().commit();//提交更新事务
		TestCase.assertEquals(len, 1);
		System.out.println(vo);
		MyBatisSessionFactory.close();
	}
}
