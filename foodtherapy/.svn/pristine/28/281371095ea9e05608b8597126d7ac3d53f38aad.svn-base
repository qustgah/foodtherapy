package ijiaban.foodtherapy.fragment;

import iijiaban.foodtherapy.ui.materialnavigation.NewsDetailActivity;
import ijiaban.foodtherapy.beans.Food;
import ijiaban.foodtherapy.beans.FoodItem;
import ijiaban.foodtherapy.beans.NewsItem;
import ijiaban.foodtherapy.dao.FoodDao;
import ijiaban.foodtherapy.dao.SearchDao;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;
import ijiaban.foodtherapy.utils.http.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.SearchView.OnQueryTextListener;

public class NewsSearchFragment extends Fragment{

	private List<NewsItem> foodItems = new ArrayList<NewsItem>();
	private CommonViewAdapter adapter;
	private ListView listview;
	private String myclass ;//大分类  barlist，menulist
	private int pageno = 1;//页码
	private long smallclass;//小分类id
	private boolean ishas = true;
	private String keywords="健康";
	private boolean isloading = true;
	private String searchtype = "news";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO 

		View view = inflater.inflate(R.layout.fragment_search, null);
		listview = (ListView) view.findViewById(R.id.list_search);
		myclass = getActivity().getIntent().getStringExtra("myclass");
		smallclass = getActivity().getIntent().getLongExtra("smallclass",1);
		keywords = getActivity().getIntent().getStringExtra("keywords");
		initAdapter();
		getData();
		listview.setAdapter(adapter);
		listview.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				// TODO 滚动加载
				if(firstVisibleItem+visibleItemCount == totalItemCount&&!isloading){
					getData();
				} 
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Intent intent = new Intent();
				intent.putExtra("id", foodItems.get(arg2).getId());
				intent.setClass(getActivity(), NewsDetailActivity.class);
				startActivity(intent); 
			}
		});
		return view;
	} 
	
	
	void getData(){  
		new AsyncTask<Void, Integer, List<NewsItem>>() {
			@Override
			protected List<NewsItem> doInBackground(Void... params) {
				// TODO 网络任务
				String url ="http://api.yi18.net/"+searchtype+"/search?keyword="+keywords+"&page="+pageno;
				List<NewsItem> list = new ArrayList<NewsItem>();
				try {
					String json = HttpUtil.getStringFromUrl(url);
					list = SearchDao.getNewsSearchItem(json);
				} catch (IOException e) {
					// TODO 网络错误
					e.printStackTrace();
				} 
				return list;
			}
			protected void onPreExecute() {
				isloading = true;
			};
			protected void onPostExecute(List<NewsItem> result) {
				isloading = false;
				foodItems.addAll(result);
				pageno+=1;
				adapter.refreshData(foodItems);
			};
		}.execute();
	}
	void initAdapter(){
		adapter = new CommonViewAdapter(foodItems,getActivity()) {
			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				// TODO 初始adapter
				ViewHolder viewholder ;
				if(convertView==null){
					viewholder = new ViewHolder();
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_search_news, null);
					viewholder.imageview = (ImageView) convertView.findViewById(R.id.img_search_news);
					viewholder.txt_title = (TextView) convertView.findViewById(R.id.txt_title_content);
					viewholder.txt_content = (TextView) convertView.findViewById(R.id.txt_content_detail);
					convertView.setTag(viewholder);
				}else{
					viewholder = (ViewHolder) convertView.getTag(); 
				}
				Picasso.with(common_context)
				.load(((NewsItem)common_datas.get(position)).getImg()).into(viewholder.imageview);
				viewholder.txt_title.setText(Html.fromHtml(((NewsItem)common_datas.get(position)).getTitle()));
				viewholder.txt_content.setText(Html.fromHtml(((NewsItem)common_datas.get(position)).getContent()));
				return convertView;
			} 
			@Override
			public long setItemId(int position) {
				return position;
			}
		};
	}
	static class ViewHolder{
		TextView txt_title;
		TextView txt_content;
		ImageView imageview;
	}
	@Override
	public void onResume() { 
		pageno = 1;
		keywords = getActivity().getIntent().getStringExtra("keywords");
		foodItems.clear();
		getData(); 
		super.onResume();
	} 
}
