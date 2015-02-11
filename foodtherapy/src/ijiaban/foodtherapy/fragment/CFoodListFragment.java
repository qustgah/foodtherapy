package ijiaban.foodtherapy.fragment;

import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.dao.FoodDBDao;
import iijiaban.foodtherapy.ui.materialnavigation.CFoodDetailActivity;
import iijiaban.foodtherapy.ui.materialnavigation.CNewsDetailActivity;
import ijiaban.foodtherapy.fragment.CNewsListFragment.ViewHolder;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;

import java.util.ArrayList;
import java.util.List;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class CFoodListFragment extends Fragment{

	private CommonViewAdapter adapter;
	private List<FoodDB> foodlist = new ArrayList<FoodDB>();
	private GridView gridview;
	private FoodDBDao foodDBDao;
	
	public boolean delete = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) { 
		
		View view = inflater.inflate(R.layout.fragment_collect_newsgrid, null);
		foodDBDao = new FoodDBDao(getActivity());
		gridview = (GridView) view.findViewById(R.id.grid_collection_news);
		initAdapter();
		getData();
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) { 
				Intent intent = new Intent(getActivity(), CFoodDetailActivity.class);
				intent.putExtra("id", ((FoodDB)gridview.getItemAtPosition(arg2)).getId());
				startActivity(intent);
			}
		});
		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) { 
				

				AlertDialog log =new AlertDialog.Builder(getActivity()).setMessage("删除本条？")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
							}
						}).setPositiveButton("删除", new DialogInterface.OnClickListener() { 
							@Override
							public void onClick(DialogInterface dialog, int which) {
								foodDBDao.delete(((FoodDB)gridview.getItemAtPosition(arg2)).getId());
								getData();
							}
						}).create();
				log.show();
				return false;
			}
		}); 
		return view;
	}
	
	void getData() { 
			new AsyncTask<Void, Integer, List<FoodDB>>() {
				@Override
				protected List<FoodDB> doInBackground(Void... params) {
					// TODO 查找数据库 
					List<FoodDB> list = new ArrayList<FoodDB>();
					list = foodDBDao.findAll();
					return list;
				}
				protected void onPreExecute() { 
					super.onPreExecute();
				}; 
				protected void onPostExecute(List<FoodDB> result) {
					foodlist = result;
					adapter.refreshData(foodlist);
				};
		}.execute();  
	} 
	void initAdapter() { 
			adapter = new CommonViewAdapter(foodlist, getActivity()) {
				@Override
				public View setView(View convertView, int position, ViewGroup parent) {
					// TODO 初始adapter
					ViewHolder viewholder;
					if (convertView == null) {
						viewholder = new ViewHolder();
						convertView = getActivity().getLayoutInflater().inflate(R.layout.item_fragment_newslist, null);
						viewholder.imageview = (ImageView) convertView.findViewById(R.id.img_news_img);
						viewholder.text = (TextView) convertView.findViewById(R.id.txt_news_title); 
						convertView.setTag(viewholder);
					} else {
						viewholder = (ViewHolder) convertView.getTag();
					} 
					Picasso.with(common_context).load("file://"+((FoodDB) common_datas.get(position)).getImg()).into(viewholder.imageview);
					viewholder.text.setText(((FoodDB) common_datas.get(position)).getName());     
					return convertView;
				}
				@Override
				public long setItemId(int position) {
					return position;
				}
			}; 
	}
	static class ViewHolder {
		TextView text;
		ImageView imageview; 
	}  
	@Override
	public void onResume() { 
		super.onResume();
		getData();
	}
}
