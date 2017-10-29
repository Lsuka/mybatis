package cn.unicorn.mybatis.ation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.unicorn.mybatis.service.INewsService;

@Controller
@RequestMapping("/pages/news/*")
public class NewsAction {
	@Resource
	private INewsService newsService;
	@ResponseBody
	@RequestMapping("news_list")
	public Object list() {
		System.err.println(this.newsService.listSplit(1, 2, null, null));
		return this.newsService.listSplit(1, 2, null, null);
	}
}
