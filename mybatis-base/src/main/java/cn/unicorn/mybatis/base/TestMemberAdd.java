package cn.unicorn.mybatis.base;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.unicorn.mybatis.util.MyBatisSessionFactory;
import cn.unicorn.mybatis.vo.Member;

public class TestMemberAdd {
	private static Random random = new Random();
	private static int rand;
	static {
		rand = random.nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void testAddMember() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml");
		// 获取一个SQLSerssionFactory接口对象,表示所有的数据库连接处理
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过SqlSessionFactory获取SqlSession接口,该接口主要用于进行数据库的操作
		SqlSession session = sessionFactory.openSession();// 获取链接
		Member vo = new Member();
		vo.setMid("unicorn" + rand);
		vo.setName("什么鬼" + rand);
		vo.setBirthday(new Date());
		vo.setSalary(666.6);
		vo.setNote("哔哩哔哩干杯" + rand);
		// 找到命名空间之中定义的具体的SQL语句,而后执行追加
		System.out.println(MyBatisSessionFactory.getSession().insert("cn.unicorn.mapping.MemberNS.doCreate",vo));
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
	}
}
