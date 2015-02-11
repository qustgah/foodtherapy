package ijiaban.foodtherapy.dao;

import ijiaban.foodtherapy.beans.CookItem;
import ijiaban.foodtherapy.beans.Food;
import ijiaban.foodtherapy.beans.FoodItem;
import ijiaban.foodtherapy.beans.NewsItem;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class SearchDao {
	static Gson gson = new Gson();
	public static List<FoodItem> getFoodSearchItem(String json){
		List<FoodItem> list = new ArrayList<FoodItem>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					FoodItem food = new FoodItem();
					food = gson.fromJson(obj.toString(),FoodItem.class);
					list.add(food);
				} 
			}  
		} catch (JSONException e) {
			// TODO json×ª»»´íÎó
			e.printStackTrace();
		} 
		return list;
	}
	public static List<CookItem> getCookSearchItem(String json){
		List<CookItem> list = new ArrayList<CookItem>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					CookItem food = new CookItem();
					food = gson.fromJson(obj.toString(),CookItem.class);
					list.add(food);
				} 
			}  
		} catch (JSONException e) {
			// TODO json×ª»»´íÎó
			e.printStackTrace();
		} 
		return list;
	}
	public static List<NewsItem> getNewsSearchItem(String json){
		List<NewsItem> list = new ArrayList<NewsItem>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray  = null;
			if(jsonObject.has("yi18")){
				jsonArray = jsonObject.getJSONArray("yi18");
				for(int i = 0;i<jsonArray.length();i++){
					JSONObject obj = jsonArray.getJSONObject(i);
					NewsItem newsItem = new NewsItem();
					newsItem = gson.fromJson(obj.toString(),NewsItem.class);
					list.add(newsItem);
				} 
			}  
		} catch (JSONException e) {
			// TODO json×ª»»´íÎó
			e.printStackTrace();
		} 
		return list;
	} 
}
