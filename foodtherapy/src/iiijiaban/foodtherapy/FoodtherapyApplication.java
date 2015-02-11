package iiijiaban.foodtherapy;


import android.app.Application;
import ijiaban.foodtherapy.utils.http.HttpUtil;
import ijiaban.foodtherapy.utils.http.RequestCache;

public class FoodtherapyApplication extends Application{

	private RequestCache requestCache;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		requestCache = new RequestCache(); 
		HttpUtil.setRequestCache(requestCache);
	} 
}
