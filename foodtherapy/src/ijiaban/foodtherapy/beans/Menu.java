package ijiaban.foodtherapy.beans;

/**
 * 疗效分类
 * 或功能分类 
 * http://api.yi18.net/food/menu
 * @author Administrator
 *
 */
public class Menu {
	
	private long id;//疗效分类ID
	private String name;//疗效分类名称
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
}
