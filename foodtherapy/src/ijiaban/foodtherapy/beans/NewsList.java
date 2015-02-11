package ijiaban.foodtherapy.beans;

import java.util.Date;

/**
 * 食品资讯列表 
 * http://api.yi18.net/news/list?page=1&limit=10&type=id&id=5
 * @author adamin
 *
 */
public class NewsList {
	private long id;//资讯id
	private String title;//资讯标题
	private String tag;//资讯标签tag
	private String img;//资讯图片
	private int count;//浏览次数 
	private String author;//作者 
	private int focal;//是否是焦点新闻，0：一般新闻，1,：焦点新闻
	private Date time;  //发布时间 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImg() {
		return "http://www.yi18.net/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getFocal() {
		return focal;
	}
	public void setFocal(int focal) {
		this.focal = focal;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	

}
