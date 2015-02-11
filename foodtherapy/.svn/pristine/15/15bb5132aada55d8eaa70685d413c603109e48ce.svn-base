package ijiaban.foodtherapy.dao;

import ijiaban.foodtherapy.beans.Bar;
import ijiaban.foodtherapy.beans.Cook;
import ijiaban.foodtherapy.beans.Food;
import ijiaban.foodtherapy.beans.Menu;
import ijiaban.foodtherapy.beans.NewDetail;
import ijiaban.foodtherapy.consants.Constants; 

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 食品的一些操作
 * @author Administrator
 *
 */
public class FoodDao {
	static Gson gson = new Gson();
	
	/**
	 * 获取食品详情
	 * @param json
	 * @return
	 */
	public static Food getFoodFromJson(String json){
		Food food = new Food();
		try {
			JSONObject jsonObject = new JSONObject(json);
			if(jsonObject.has("yi18")){
				JSONObject obj = (JSONObject) jsonObject.get("yi18");
				food = gson.fromJson(obj.toString(), Food.class);
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return food;
	} 
	/**
	 * 获取食物列表
	 * @param json
	 * @return
	 */
	public static List<Food> FoodJsonToList(String json){ 
		List<Food> list = new ArrayList<Food>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					Food food = new Food();
					food = gson.fromJson(obj.toString(),Food.class);
					list.add(food);
				} 
			}  
		} catch (JSONException e) {
			// TODO json转换错误
			e.printStackTrace();
		} 
		return list;
	}
	public static List<Cook> CookJsonToList(String json){ 
		List<Cook> list = new ArrayList<Cook>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					Cook food = new Cook();
					food = gson.fromJson(obj.toString(),Cook.class);
					list.add(food);
				} 
			}  
		} catch (JSONException e) {
			// TODO json转换错误
			e.printStackTrace();
		} 
		return list;
	}

	/**
	 * 疗效分类列表
	 * @param json
	 * @return
	 */
	public static List<Menu> MenuJsonToList(String json){
		List<Menu> list = new ArrayList<Menu>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					Menu menu = new Menu();
					menu = gson.fromJson(obj.toString(),Menu.class);
					list.add(menu);
				} 
			}   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	/**
	 * 功能列表
	 * @param json
	 * @return
	 */
	public static List<Bar> BarJsonToList(String json){
		List<Bar> list = new ArrayList<Bar>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					Bar bar = new Bar();
					bar = gson.fromJson(obj.toString(),Bar.class);
					list.add(bar);
				} 
			}   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	} 

	public static Cook getCook(long id) throws Exception{
		Cook cook = null;
		String url = Constants.DETAIL_COOK+id;
		String json = CommonDao.getDataFromServer(url);
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jb = null;
		if(jsonObject.has("yi18")){
			jb = jsonObject.getJSONObject("yi18");
			cook = gson.fromJson(jb.toString(), Cook.class);
		}
		return cook;
	}
	public static Food getFood(long id) throws Exception{
		Food food = null;
		String url = Constants.DETAIL_FOOD+id;
		String json = CommonDao.getDataFromServer(url);
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jb = null;
		if(jsonObject.has("yi18")){
			jb = jsonObject.getJSONObject("yi18");
			food = gson.fromJson(jb.toString(), Food.class);
		}
		return food;
	}
	public static NewDetail getNews(long id) throws Exception{
		NewDetail newDetail = null;
		String url = Constants.DETAIL_NEWS+id;
		String json = CommonDao.getDataFromServer(url);
		JSONObject jsonObject = new JSONObject(json);
		JSONObject jb = null;
		if(jsonObject.has("yi18")){
			jb = jsonObject.getJSONObject("yi18");
			newDetail = gson.fromJson(jb.toString(), NewDetail.class);
		}
		return newDetail;
	}
	
}
