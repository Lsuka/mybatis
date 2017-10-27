package cn.unicorn.mybatis.cache.vo;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
	private Long nid;
	private String title;
	private Date pubdate;
	private String note;

	public Long getNid() {
		return nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "News [nid=" + nid + ", title=" + title + ", pubdate=" + pubdate + ", note=" + note + "]";
	}

}