package ijiaban.foodtherapy.fragment;

import java.util.ArrayList;
import java.util.List;

import iiijiaban.foodtherapy.db.CookDB;
import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.dao.CookDBDao;
import iijiaban.foodtherapy.ui.materialnavigation.CCookDetailActivity;
import ijiaban.foodtherapy.fragment.CFoodListFragment.ViewHolder;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class CCookListFragment extends Fragment {
	private GridView cookView;
    private CommonViewAdapter adapter;
    private ArrayList<CookDB> list; 
    private ArrayList<CookDB> cookList;
    private CookDBDao DBhelper;
    private Handler handler;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_ccookfragment, null);
		cookView = (GridView) view.findViewById(R.id.ccookList);
		DBhelper = new CookDBDao(getActivity());
		list = new ArrayList<CookDB>();
		adapter = new CommonViewAdapter(list, getActivity()){
            ViewHolder holder;
			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				holder = new ViewHolder();
				if(convertView == null){
					convertView = getActivity().getLayoutInflater().inflate(R.layout.item_class_cook, null);
					holder.imageview = (ImageView) convertView.findViewById(R.id.cookImage);
					holder.text = (TextView) convertView.findViewById(R.id.cookName);
					convertView.setTag(holder);
				}
				else{
					holder = (ViewHolder) convertView.getTag();
				}
				Picasso.with(common_context).load("file://"+((CookDB) common_datas.get(position)).getImg()).into(holder.imageview);
				holder.text.setText(((CookDB) common_datas.get(position)).getName());
				return convertView;
			}
			
			@Override
			public long setItemId(int position) {
				return position;
			}
			
		};
		cookView.setAdapter(adapter);
		cookView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), CCookDetailActivity.class);
				intent.putExtra("id", ((CookDB)cookView.getItemAtPosition(position)).getId());
				startActivity(intent);
			}
			
		});
		cookView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog dialog = new AlertDialog.Builder(getActivity()).setMessage("删除本条？")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
							}
						}).setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								DBhelper.delete(((CookDB) cookView.getItemAtPosition(position)).getId());
								getData();
							}
						}).create();
				dialog.show();
				return false;
			}
			
		});
		getData();
		return view;
	}
	public void getData(){
		
		new Thread(new Runnable(){

				@Override
				public void run() {
					cookList = new ArrayList<CookDB>();
					cookList = DBhelper.findAll();
					Message message = Message.obtain();
					message.obj = cookList;
					handler.sendMessage(message);
				}
		
		}).start();
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				cookList = (ArrayList<CookDB>) msg.obj;
				adapter.refreshData(cookList);
			}
			
		};
	}
	
	
}
