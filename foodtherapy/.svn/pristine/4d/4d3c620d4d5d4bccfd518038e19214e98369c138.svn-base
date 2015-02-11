package ijiaban.foodtherapy.dao;


import ijiaban.foodtherapy.beans.Menu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 取得疗效或者功能 分类 
 * 功能分类 ：http://api.yi18.net/food/bar
 * 疗效分类 :http://api.yi18.net/food/menu
 * @author adamin
 *
 */
public class MenuDao {
	public static List<Menu> getMenus(String url){
		Gson gson=new Gson();
		List<Menu> items=new ArrayList<Menu>();
	String json;
	try {
		json = CommonDao.getDataFromServer(url);
		JSONObject jb=new JSONObject(json);
	    JSONArray ja=jb.getJSONArray("yi18");
		String jas=ja.toString();
		items=gson.fromJson(jas, new TypeToken<List<Menu>>(){}.getType());
	} catch (Exception e) {
		e.printStackTrace();
	}
	    
		
		
		return items;
		
	}

}
