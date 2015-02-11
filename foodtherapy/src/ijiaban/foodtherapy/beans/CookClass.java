package ijiaban.foodtherapy.beans;
/**
 * 食谱分类
 * http://api.yi18.net/cook/cookclass
 * @author Administrator
 *
 */
public class CookClass {

	private long id;//食谱分类ID
	private String name;//食谱分类名称
	private int cookclass;//
	
	
	public int getCookclass() {
		return cookclass;
	}
	public void setCookclass(int cookclass) {
		this.cookclass = cookclass;
	}
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
