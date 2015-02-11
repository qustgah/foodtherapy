package ijiaban.foodtherapy.dao;

import ijiaban.foodtherapy.beans.CookClass;
import ijiaban.foodtherapy.beans.Recipe;
import ijiaban.foodtherapy.utils.http.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 食谱的一些操作
 * @author Administrator
 *
 */
public class RecipeDao {

	static Gson gson = new Gson();
	/**
	 * 获取食谱详情
	 * @param json
	 * @return
	 */
	public static Recipe getRecipeFromJson(String json){
		Recipe recipe = new Recipe();
		try {
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.has("yi18")){
				JSONObject obj = (JSONObject) jsonObject.get("yi18");
				recipe = gson.fromJson(obj.toString(), Recipe.class);
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return recipe;
	} 
	/**
	 * 获取食谱列表
	 * @param json
	 * @return
	 */
	public static List<Recipe> CookClassJsonToList(String json){ 
		List<Recipe> list = new ArrayList<Recipe>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					Recipe recipe = new Recipe();
					recipe = gson.fromJson(obj.toString(),Recipe.class);
					list.add(recipe);
				} 
			}  
		} catch (JSONException e) {
			// TODO json转换错误
			e.printStackTrace();
		} 
		return list;
	}


	/**
	 * 食谱分类列表
	 * @param json
	 * @return
	 */
	public static List<CookClass> MenuJsonToList(String json){
		List<CookClass> list = new ArrayList<CookClass>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					CookClass cookclass = new CookClass();
					cookclass = gson.fromJson(obj.toString(),CookClass.class);
					list.add(cookclass);
				} 
			}   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	
	public static List<Map<CookClass, List<CookClass>>> getAllClass(String json){
		
		List<Map<CookClass, List<CookClass>>> listmap = new ArrayList<Map<CookClass,List<CookClass>>>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					Map<CookClass, List<CookClass>> map = new HashMap<CookClass, List<CookClass>>(); 
					JSONObject obj = jsonArray.getJSONObject(i);
					CookClass cookclass = new CookClass();
					cookclass = gson.fromJson(obj.toString(),CookClass.class);
					String url = "http://api.yi18.net/cook/cookclass?id="+cookclass.getId();
					try { 
						List<CookClass> list2 = new ArrayList<CookClass>();
						String moreclass = HttpUtil.getStringFromUrl(url);
						JSONObject jsonObject2 = new JSONObject(moreclass);
						JSONArray jsonArray2  = null;
						if(jsonObject2.has("yi18")){
							jsonArray2 = jsonObject2.getJSONArray("yi18");
							for(int j = 0;j<jsonArray2.length();j++){ 
								JSONObject obj2 = jsonArray2.getJSONObject(j);
								CookClass cookclass2 = new CookClass();
								cookclass2 = gson.fromJson(obj2.toString(),CookClass.class);
								list2.add(cookclass2);
							} 
						}
						map.put(cookclass, list2);
					} catch (IOException e) {
						// TODO 获取食谱小分类 网络错误
						e.printStackTrace();
					}
					listmap.add(map);
				} 
			}   
		} catch (JSONException e) {
			// TODO json转化错误
			e.printStackTrace();
		} 
		return listmap;
	} 
}
