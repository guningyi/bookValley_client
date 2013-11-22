package com.example.adnroid_client_2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class BookShelfActivity extends Activity {
	private GridView bookShelf;
	private Thread mThread;
	private String[] name;
	private static final int MSG_SUCCESS = 0;
	private static final int MSG_FAILURE = 1;
	//private HttpUtility httpUtility;
	   private int[] data = {
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,
				R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt,R.drawable.cover_txt
				
		};
    //HttpUtility httpUtility = new HttpUtility();
	//private String[] name= httpUtility.list_book("guningyi");
	

	private GridView gv;  
    private SlidingDrawer sd;  
    private Button iv;  
    private List<ResolveInfo> apps; 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
    	//call the thread and handler   
    	if (mThread == null)
    	{
    		mThread = new Thread(runnable);
    		mThread.start();
    	}
    	   
        
        bookShelf = (GridView) findViewById(R.id.bookShelf);
        ShlefAdapter adapter=new ShlefAdapter();
        bookShelf.setAdapter(adapter);
        bookShelf.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2>=data.length){
					
				}else{
				   Toast.makeText(getApplicationContext(), ""+arg2, Toast.LENGTH_SHORT).show();
				}
			}
		});
        loadApps();  
        gv = (GridView) findViewById(R.id.allApps);  
        sd = (SlidingDrawer) findViewById(R.id.sliding);  
        iv = (Button) findViewById(R.id.imageViewIcon);  
        gv.setAdapter(new GridAdapter());  
        sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()// 开抽屉  
        {  
            @Override  
            public void onDrawerOpened() {  
            	iv.setText("返回");
                iv.setBackgroundResource(R.drawable.btn_local);// 响应开抽屉事件  
                                                                // ，把图片设为向下的  
            }  
        });  
        sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {  
            @Override  
            public void onDrawerClosed() {  
            	iv.setText("本地");
                iv.setBackgroundResource(R.drawable.btn_local);// 响应关抽屉事件  
            }  
        });  
    }
    
    class ShlefAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length+5;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View contentView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			contentView=LayoutInflater.from(getApplicationContext()).inflate(R.layout.item1, null);
			
			TextView view=(TextView) contentView.findViewById(R.id.imageView1);
			if(data.length>position){
				if(position<name.length){
				   view.setText(name[position]);
				}
				view.setBackgroundResource(data[position]);
			}else{
				view.setBackgroundResource(data[0]);
				view.setClickable(false);
				view.setVisibility(View.INVISIBLE);
			}
			return contentView;
		}
    	
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("你确定退出吗？")
					.setCancelable(false)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							})
					.setNegativeButton("返回",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
    
    
    private void loadApps() {  
        Intent intent = new Intent(Intent.ACTION_MAIN, null);  
        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
  
        apps = getPackageManager().queryIntentActivities(intent, 0);  
    }  
  
    public class GridAdapter extends BaseAdapter {  
        public GridAdapter() {  
  
        }  
  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return apps.size();  
        }  
  
        public Object getItem(int position) {  
            // TODO Auto-generated method stub  
            return apps.get(position);  
        }  
  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return position;  
        }  
  
        public View getView(int position, View convertView, ViewGroup parent) {  
            // TODO Auto-generated method stub  
            ImageView imageView = null;  
            if (convertView == null) {  
                imageView = new ImageView(BookShelfActivity.this);  
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
                imageView.setLayoutParams(new GridView.LayoutParams(50, 50));  
            } else {  
                imageView = (ImageView) convertView;  
            }  
  
            ResolveInfo ri = apps.get(position);  
            imageView.setImageDrawable(ri.activityInfo  
                    .loadIcon(getPackageManager()));  
  
            return imageView;  
        }  
  
    }  


    Handler  mHandler = new Handler()
    {
    	@Override
    	public void handleMessage(Message msg) {
    		switch(msg.what){
    		    case MSG_SUCCESS:
    		    	name = (String[]) msg.obj;
    			    break;
    			default:
    				
    		}
    			
    	}
    	
    };
    
    Runnable runnable = new Runnable()
    {
    	@Override
    	public void run()
    	{
    		 HttpUtility httpUtility = new HttpUtility();
    		 String[] nameTmp= httpUtility.list_book("guningyi");
    		 mHandler.obtainMessage(MSG_SUCCESS, nameTmp).sendToTarget();
    	}
    };
    

}
