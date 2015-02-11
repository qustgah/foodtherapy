package iijiaban.foodtherapy.ui.materialnavigation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import iiijiaban.foodtherapy.db.CookDB;
import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.dao.CookDBDao;
import iiijiaban.foodtherapy.db.dao.FoodDBDao;
import iijiaban.foodtherapy.textview.MTextView;
import ijiaban.foodtherapy.beans.Cook;
import ijiaban.foodtherapy.dao.FoodDao;
import ijiaban.foodtherapy.fragment.CCookListFragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iiijiaban.foodtherapy.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

public class CookDetailActivity extends ActionBarActivity {
	private Toolbar mToolbar;
	private ImageView cookImage;
	private TextView cookName;
	private TextView cookTag;
    private TextView cookFood;
    private TextView cookdetail; 
    private Cook cook;
    private long id;   //食谱id
    private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/Foodthepy/cook";
    private String cookFile;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookdetail_2);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		cookImage = (ImageView) findViewById(R.id.img_cookdetail);
		cookName = (TextView) findViewById(R.id.txt_cookdetail_cookname);
		cookTag = (TextView) findViewById(R.id.txt_cookdetail_tag);
		cookFood = (TextView) findViewById(R.id.txt_cookdetail_food);
		cookdetail = (TextView) findViewById(R.id.txt_cookdetail_message); 
		id=getIntent().getLongExtra("id", 0);
		getData();
	}
	
	private void getData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					cook = FoodDao.getCook(id);
					Message m = new Message();
					m.what = 0x123;
					handler.sendMessage(m);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x123:
				setData();
				break;
			default:
				break;
			}
		}
	};
	
	private void setData() {
		// 显示数据
		Picasso.with(this).load(cook.getImg()).into(cookImage); 
		cookName.setText(cook.getName());
		cookFood.setText(cook.getFood());
		cookTag.setText(cook.getTag());
		cookdetail.setText(Html.fromHtml(cook.getMessage()));
		cookdetail.setMovementMethod(ScrollingMovementMethod.getInstance());
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.menu_share:
			Intent  sIntent = new Intent();
			sIntent.setAction(Intent.ACTION_SEND);
			sIntent.putExtra(Intent.EXTRA_TEXT, "http://cook.yi18.net/show/"+id);
			sIntent.setType("text/plain");
			startActivity(sIntent);
			break;
		case R.id.menu_collect:
			CookDBDao helper = new CookDBDao(CookDetailActivity.this);
			if(helper.isExits(cook.getId())){
				Toast.makeText(CookDetailActivity.this, "已收藏",Toast.LENGTH_SHORT).show();
			}else{
				CookDB cookDb = new CookDB();
				if (cookDb != null) { 
					cookDb.setId(cook.getId());
					cookDb.setName(cook.getName());
					cookDb.setTag(cook.getTag());
					cookDb.setBar(cook.getBar());
					cookDb.setCount(cook.getCount());
					cookDb.setFood(cook.getFood());
					cookDb.setMessage(cook.getMessage()); 
					try {
						cookImage.setDrawingCacheEnabled(true);
						Bitmap bitmap = Bitmap.createBitmap(cookImage.getDrawingCache());
						cookImage.setDrawingCacheEnabled(false);
						if (bitmap != null) {
							File file = new File(ALBUM_PATH);
							if (!file.exists()) {
								file.mkdir();
							}
							int fileNameStart = cook.getImg().lastIndexOf("/");
							cookFile = cook.getImg().substring(fileNameStart + 1);
							File myCaptureFile = new File(ALBUM_PATH + cookFile);
							FileOutputStream fos = new FileOutputStream(myCaptureFile);
							BufferedOutputStream bos = new BufferedOutputStream(fos);
							bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
							bos.flush();
							bos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
				cookDb.setImg(ALBUM_PATH + cookFile);
				helper.insert(cookDb);
				Toast.makeText(CookDetailActivity.this, "收藏",Toast.LENGTH_SHORT).show();
			} 
			break;
		default:
			break;
		}
		return false;
	}

}
