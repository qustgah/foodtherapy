 package ijiaban.foodtherapy.fragment;

import iijiaban.foodtherapy.ui.materialnavigation.FoodListActivity;
import iijiaban.foodtherapy.ui.materialnavigation.SearchActivity;
import iijiaban.foodtherapy.ui.materialnavigation.ClassActivity.ViewHolder;
import ijiaban.foodtherapy.beans.CookClass;
import ijiaban.foodtherapy.dao.RecipeDao;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;
import ijiaban.foodtherapy.utils.http.HttpUtil;
import ijiaban.foodtherapy.wedgits.expandlistview.MyGridView;
import ijiaban.foodtherapy.wedgits.expandlistview.SlideExpandableListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.iiijiaban.foodtherapy.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class CookClassFragment extends Fragment implements OnQueryTextListener{

	private SlideExpandableListView listview;
	List<CookClass> lists = new ArrayList<CookClass>();
	private CommonViewAdapter myadapter; 
	List<Map<CookClass, List<CookClass>>> cookclasses = new ArrayList<Map<CookClass,List<CookClass>>>();//食谱分类
	private String searchtype = "cook";
	private ProgressBar progressbar_cookclass;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO 初始
		View view = inflater.inflate(R.layout.activity_class_expandable_list, null); 
		getActivity().getIntent().putExtra("searchtype", searchtype);
		progressbar_cookclass = (ProgressBar) view.findViewById(R.id.progressbar_cookclass);
		listview = (SlideExpandableListView)view.findViewById(R.id.list);
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
			
		});
		initialAdapter();
		getData();
		listview.setAdapter(myadapter);
		return view;
	}
	/**
	 * 初始化adapter
	 */
	public void initialAdapter() {
		myadapter = new CommonViewAdapter(cookclasses,getActivity()) {
			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				// TODO 初始化listview
				ViewHolder viewholder ;
				if(convertView == null){
					viewholder = new ViewHolder();
					convertView = getActivity().getLayoutInflater().inflate(R.layout.item_class_expandable, null);
					viewholder.textview = (TextView) convertView.findViewById(R.id.text);
					viewholder.gridview =(MyGridView) convertView.findViewById(R.id.expandable);
					convertView.setTag(viewholder); 
				}else{
					viewholder = (ViewHolder) convertView.getTag();
				}
				CookClass cls = new CookClass();
//				List<CookClass> lists = new ArrayList<CookClass>();
				Iterator<Entry<CookClass, List<CookClass>>> it1 = ((Map<CookClass, List<CookClass>>)common_datas.get(position)).entrySet().iterator();
//				Iterator<List<CookClass>> it2 = (Iterator<List<CookClass>>) ((Map<CookClass, List<CookClass>>)common_datas.get(position)).values();
				while(it1.hasNext()){
					Entry<CookClass, List<CookClass>> entry = it1.next();
					cls = entry.getKey(); 
					lists = entry.getValue();
				} 
				viewholder.textview.setText(cls.getName()); 
				CommonViewAdapter comm = new CommonViewAdapter(lists,common_context) {
					
					@Override
					public View setView(View convertView, final int position, ViewGroup parent) {
						// TODO 
						Button button ;
						if(convertView==null){
							convertView = getActivity().getLayoutInflater().inflate(R.layout.item_class_grid, null);
							button = (Button) convertView.findViewById(R.id.myclasses);
							convertView.setTag(button);
						}else{
							button = (Button) convertView.getTag();
						}
						button.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO 点击
								CookClass menu = (CookClass) common_datas.get(position);
								Intent intent = new Intent(common_context, FoodListActivity.class);
								intent.putExtra("myclass", "cook/list");
								intent.putExtra("type", 1);  //cook  
								intent.putExtra("searchtype", searchtype);
								intent.putExtra("smallclass",menu.getId());
								startActivity(intent);
							}
						});
						button.setText(((CookClass)common_datas.get(position)).getName());
						return convertView;
					} 
					@Override
					public long setItemId(int position) {
						// TODO  
						return position;
					}
				};
				viewholder.gridview.setAdapter(comm);
				comm.notifyDataSetChanged();
				return convertView;
			}
			@Override
			public long setItemId(int position) {
				return position;
			}
		}; 
	}
	
	void getData(){
		new AsyncTask<Void, Integer, List<Map<CookClass, List<CookClass>>>>() {

			@Override
			protected List<Map<CookClass, List<CookClass>>> doInBackground(Void... params) {
				// TODO 网络任务
				String url ="http://api.yi18.net/cook/cookclass";
				List<Map<CookClass, List<CookClass>>> listmap = new ArrayList<Map<CookClass,List<CookClass>>>();
				try {
					String json = HttpUtil.getStringFromUrl(url);
					listmap = RecipeDao.getAllClass(json);
				} catch (IOException e) {
					// TODO 网络错误
					e.printStackTrace();
				} 
				return listmap;
			}
			protected void onPostExecute(java.util.List<java.util.Map<CookClass,java.util.List<CookClass>>> result) {
				cookclasses = result;
				progressbar_cookclass.setVisibility(View.GONE);
				myadapter.refreshData(cookclasses);
			};
		}.execute();
	}
	public static  class ViewHolder{
		TextView textview;
		MyGridView gridview; 
	}
	
	public static Fragment newInstance() {
		Fragment fr=new CookClassFragment();
	    return fr;
	}
	@Override
	public void onCreateOptionsMenu(android.view.Menu menu,
			MenuInflater inflater) {
		// TODO 菜单
		inflater.inflate(R.menu.main, menu); 
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	    searchView.setOnQueryTextListener(this);
//		MenuItem item = menu.findItem(R.id.menu_search);
//		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//			
//			@Override
//			public boolean onMenuItemClick(MenuItem item) {
//				// TODO Auto-generated method stub 
//				Intent intent = new Intent(getActivity(),SearchActivity.class);
//				intent.putExtra("searchtype", searchtype);
//				startActivity(intent);   
//				return false;
//			}
//		});
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public boolean onQueryTextChange(String newText) { 
		return false;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(),SearchActivity.class); 
		intent.putExtra("searchtype", intent.getStringExtra("searchtype"));
		intent.putExtra("keywords", query);
		startActivity(intent); 
		return false;
	}  
}
