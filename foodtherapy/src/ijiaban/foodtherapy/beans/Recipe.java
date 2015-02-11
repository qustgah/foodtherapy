package ijiaban.foodtherapy.beans;
/**
 * 食谱详情
 * 
 * http://api.yi18.net/cook/show?id=1
 * @author Administrator
 *
 */
public class Recipe {

	private long id;//食谱ID
	private String name;//食谱名称
	private String tag;//食谱疗效主要功能 
	private String bar;//食品 主要的功能
	private String img;//图片
	private String message;//食谱的主要做法
	private int count ;//浏览次数
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	public String getImg() {
		return "http://www.yi18.net/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	} 
}
