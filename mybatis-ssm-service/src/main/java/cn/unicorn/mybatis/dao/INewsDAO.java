package cn.unicorn.mybatis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.unicorn.mybatis.vo.News;
public interface INewsDAO {
	public boolean doCreate(News vo) ;
	public List<News> findByIds(Object ids) ; 
	public List<News> findSplit(Map<String,Object> params) ;
}
