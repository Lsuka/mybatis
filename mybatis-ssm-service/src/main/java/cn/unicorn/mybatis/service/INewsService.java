package cn.unicorn.mybatis.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.unicorn.mybatis.vo.News;
public interface INewsService {
	public boolean add(News vo);
 
	public List<News> listByIds(Set<Long> ids);

	public List<News> listSplit(long currentPage, int lineSize, String column, String keyWord);
}
