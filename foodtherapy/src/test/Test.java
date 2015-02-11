package test;

import ijiaban.foodtherapy.beans.Menu;
import ijiaban.foodtherapy.beans.NewDetail;
import ijiaban.foodtherapy.beans.NewsList;
import ijiaban.foodtherapy.consants.Constants;
import ijiaban.foodtherapy.dao.MenuDao;
import ijiaban.foodtherapy.dao.NewsDao;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.iiijiaban.foodtherapy.R;

public class Test extends Activity{
	String  json;
	Gson gson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_row);
	 gson = new Gson();

		
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Menu> items=MenuDao.getMenus(Constants.BAR_URL);
					List<NewsList> details=NewsDao.getnews(Constants.NEWS_COMMON_URL+"1"+Constants.NEWS_LIMIT+
							"10"+Constants.NEWS_TYPE+"id"+Constants.NEWS_ID+"5");
					System.out.print(details.get(1).getImg());
					System.out.print(items.get(0).getName());
				}
			}).start();
			


	}
	


}
