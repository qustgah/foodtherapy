package iijiaban.foodtherapy.ui.materialnavigation;

import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.dao.FoodDBDao;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CFoodDetailActivity extends ActionBarActivity{

	private Toolbar mToolbar;
	private ImageView detailImage;
	private TextView menu_bartext;
    private TextView content;
    private TextView summaryView;
    private FoodDB food;
    private long id;   //食谱id   
    
    private FoodDBDao foodDBDao;
    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fooddetail_2);
		foodDBDao = new FoodDBDao(this);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		detailImage = (ImageView) findViewById(R.id.img_activity_fooddetail);
		menu_bartext = (TextView) findViewById(R.id.txt_fooddetail_content); 
		summaryView = (TextView) findViewById(R.id.txt_summary);
		content = (TextView) findViewById(R.id.txt_content); 
		id=getIntent().getLongExtra("id", 0);
		getData();
	}
	private void getData() {
		new AsyncTask<Void, String, FoodDB>() {
			@Override
			protected FoodDB doInBackground(Void... params) {
				food = foodDBDao.select(id); 
				return food;
			} 
			@Override
			protected void onPostExecute(FoodDB result) {
				super.onPostExecute(result);
				setData(result);
			}
		}.execute();
	} 
	private void setData(FoodDB food) {
		// 显示数据
		Picasso.with(this).load("file://"+food.getImg()).into(detailImage); 
		menu_bartext.setText(food.getMenu() + "\\\n" + food.getBar());
		if(food.getSummary()!=null){ 
			summaryView.setText(Html.fromHtml(food.getSummary()));
		} 
		content.setText(Html.fromHtml(food.getDetailText()));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_cdetail, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break; 
		case R.id.menu_share:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, "http://food.yi18.net/show/"+id);
			intent.setType("text/plain");
			startActivity(intent);
			break; 
		default:
			break;
		}
		return false;
	}
}
