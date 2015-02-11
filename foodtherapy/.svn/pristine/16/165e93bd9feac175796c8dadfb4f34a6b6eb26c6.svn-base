package iijiaban.foodtherapy.ui.materialnavigation;

import ijiaban.foodtherapy.beans.Cook;
import ijiaban.foodtherapy.beans.Food;
import ijiaban.foodtherapy.dao.FoodDao;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;
import ijiaban.foodtherapy.utils.http.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends ActionBarActivity implements OnQueryTextListener {

	private Toolbar mToolbar;
	private List<Food> foods = new ArrayList<Food>();
	private List<Cook> cooks = new ArrayList<Cook>();
	private CommonViewAdapter adapter;
	private GridView gridview;
	private String myclass;// 大分类 barlist，menulist
	private int pageno = 1;// 页码
	private long smallclass;// 小分类id
	private boolean ishas = true;
	private boolean isloading = true;
	private String searchtype = "";
	String url = "";
	private  int cookorfood=0;   //1 cook  2  food 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 初始界面
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_foodlist);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		searchtype = getIntent().getStringExtra("searchtype");
		gridview = (GridView) findViewById(R.id.grid_food);
		
		myclass = getIntent().getStringExtra("myclass");
		smallclass = getIntent().getLongExtra("smallclass", 1);
		cookorfood=getIntent().getIntExtra("type", 1);
		url = "http://api.yi18.net/" + myclass + "?id=" + smallclass + "&page=" + pageno;
		
		initAdapter();
		getData(url);
		gridview.setAdapter(adapter);
		gridview.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO 滚动加载
				if (firstVisibleItem + visibleItemCount == totalItemCount&&!isloading) {
					getData(url);
				}
			}
		});
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent();
				switch(cookorfood){
				case 1:
					intent.putExtra("id", cooks.get(position).getId());
					intent.setClass(getApplicationContext(), CookDetailActivity.class);
					break;
				case 2:
					intent.putExtra("id", foods.get(position).getId());
					intent.setClass(getApplicationContext(), FoodDetailActivity.class);
					break;
				 default:
					 break;
			}
			
				
				startActivity(intent);
			}
		});

	}

	void getData(final String myurl) {
		switch (cookorfood){
		case 2:
			new AsyncTask<Void, Integer, List<Food>>() {
				@Override
				protected List<Food> doInBackground(Void... params) {
					// TODO 网络任务
//					String url = "http://api.yi18.net/" + myclass + "?id=" + smallclass + "&page=" + pageno;
					List<Food> list = new ArrayList<Food>();
					try {
						String json = HttpUtil.getStringFromUrl(myurl);
						list = FoodDao.FoodJsonToList(json);
					} catch (IOException e) {
						// TODO 网络错误
						e.printStackTrace();
					}
					return list;
				}
				protected void onPreExecute() {
					isloading = true;
					super.onPreExecute();
				};

				protected void onPostExecute(List<Food> result) {
					foods.addAll(result);
					pageno += 1;
					isloading = false;
					adapter.refreshData(foods);
				};
			}.execute();
			break;
		case 1:  //cook 
			new AsyncTask<Void, Integer, List<Cook>>() {
				@Override
				protected List<Cook> doInBackground(Void... params) {
					// TODO 网络任务
					String url = "http://api.yi18.net/" + myclass + "?id=" + smallclass + "&page=" + pageno;
					List<Cook> list = new ArrayList<Cook>();
					try {
						String json = HttpUtil.getStringFromUrl(url);
						list = FoodDao.CookJsonToList(json);
					} catch (IOException e) {
						// TODO 网络错误
						e.printStackTrace();
					}
					return list;
				}
				protected void onPreExecute() {
					isloading = true;
					super.onPreExecute();
				};

				protected void onPostExecute(List<Cook> result) {
					cooks.addAll(result);
					pageno += 1;
					isloading = false;
					adapter.refreshData(cooks);
				};
			}.execute();
			break;
			default :
				break;
		}
		
	}

	void initAdapter() {
		switch (cookorfood){
		case 1:
			adapter = new CommonViewAdapter(cooks, this) {
				@Override
				public View setView(View convertView, int position, ViewGroup parent) {
					// TODO 初始adapter
					ViewHolder viewholder;
					if (convertView == null) {
						viewholder = new ViewHolder();
	 
						convertView = getLayoutInflater().inflate(R.layout.item_activity_foodlist, null);
						viewholder.imageview = (ImageView) convertView.findViewById(R.id.img_food);
						viewholder.text = (TextView) convertView.findViewById(R.id.txt_food);
	 
						convertView.setTag(viewholder);
					} else {
						viewholder = (ViewHolder) convertView.getTag();
					}
					Picasso.with(common_context)
							.load(((Cook) common_datas.get(position)).getImg())
							.into(viewholder.imageview);
					viewholder.text.setText(((Cook) common_datas.get(position))
							.getName());
					return convertView;
				}

				@Override
				public long setItemId(int position) {
					return position;
				}
			};
			break;
		case 2:
			adapter = new CommonViewAdapter(foods, this) {
				@Override
				public View setView(View convertView, int position, ViewGroup parent) {
					// TODO 初始adapter
					ViewHolder viewholder;
					if (convertView == null) {
						viewholder = new ViewHolder();
	 
						convertView = getLayoutInflater().inflate(R.layout.item_activity_foodlist, null);
						viewholder.imageview = (ImageView) convertView.findViewById(R.id.img_food);
						viewholder.text = (TextView) convertView.findViewById(R.id.txt_food);
	 
						convertView.setTag(viewholder);
					} else {
						viewholder = (ViewHolder) convertView.getTag();
					}
					Picasso.with(common_context).load(((Food) common_datas.get(position)).getImg()).into(viewholder.imageview);
					viewholder.text.setText(((Food) common_datas.get(position))
							.getName());
					return convertView;
				}

				@Override
				public long setItemId(int position) {
					return position;
				}
			};
			break;
		}
	}

	static class ViewHolder {
		TextView text;
		ImageView imageview;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 菜单

		switch (item.getItemId()) {
//		case R.id.menu_search:
//			Intent intent = new Intent(this, SearchActivity.class);
//			searchtype = getIntent().getStringExtra("searchtype");
//			intent.putExtra("searchtype", searchtype);
//			startActivity(intent);
//			break;
		case android.R.id.home:
			finish();
			break; 
		default:
			break;
		}
		return super.onOptionsItemSelected(item); 
	} 
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
//		pageno = 1;
//		foods.clear();
//		String myurl = "http://api.yi18.net/"+searchtype+"/search?keyword="+query+"&page="+pageno;
//		getData(myurl);
		Intent intent = new Intent(this,SearchActivity.class);
		intent.putExtra("searchtype", getIntent().getStringExtra("searchtype"));
		intent.putExtra("keywords", query);
		startActivity(intent);
		return false;
	}
}
