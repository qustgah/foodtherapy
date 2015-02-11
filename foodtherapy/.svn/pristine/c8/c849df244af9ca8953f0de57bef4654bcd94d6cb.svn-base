package iijiaban.foodtherapy.ui.materialnavigation;

import iiijiaban.foodtherapy.db.NewsDB;
import iiijiaban.foodtherapy.db.dao.NewsDBDao;
import ijiaban.foodtherapy.beans.NewDetail;
import ijiaban.foodtherapy.dao.NewsDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import utils.QuickReturnUtils;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CNewsDetailActivity extends ActionBarActivity{

	private Toolbar mToolbar;
	private long id;
	private TextView title;
	private TextView time;
	private TextView author;
	private TextView count;
	
	private ImageView image;
	private TextView message;
	private NewsDB detail; 
	private String fileName;
	private NewsDBDao newsDBDao;
	private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/Foodthepy/";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.newsdetailactivity);
		newsDBDao = new NewsDBDao(this);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		id = intent.getLongExtra("id", 1);
		initView();
		getdata();
	} 
	private void getdata() {
		new AsyncTask<Void, String, NewsDB>() {
			@Override
			protected NewsDB doInBackground(Void... params) {
				detail = newsDBDao.select(id); 
				return detail;
			} 
			@Override
			protected void onPostExecute(NewsDB result) {
				super.onPostExecute(result);
				setData(result);
			}
		}.execute();
	}

	protected void setData(NewsDB result) {
		title.setText(result.getTitle());
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		date = result.getTime();
		String str = sdf.format(date);
		time.setText("发布时间："+str);
		author.setText("作者："+result.getAuthor());
		count.setText("点击量："+result.getCount());
		message.setText(Html.fromHtml(result.getMessage()));
		Picasso.with(this)
				.load("file://"+result.getImg())
				.centerCrop()
				.resize(QuickReturnUtils.dp2px(this, 346),QuickReturnUtils.dp2px(this, 320)).into(image);
	}

	private void initView() {
		
		title = (TextView) findViewById(R.id.news_title);
		time = (TextView) findViewById(R.id.display_time);
		author = (TextView) findViewById(R.id.display_author);
		count = (TextView) findViewById(R.id.display_count);
		image = (ImageView) findViewById(R.id.post_iv);
		message = (TextView) findViewById(R.id.message_tv);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 初始化menu
		
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
