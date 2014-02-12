package com.example.adnroid_client_2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.adnroid_client_2.MainActivity.Download;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendActivity extends Activity {

	private String[] name = { "冬季养生", "冬季养生简介" };


	//获取server端数据
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			name = (String[]) msg.obj;
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend_main_frame);
		
		new Thread() {
        	@Override
        	public void run()
        	{
        		 HttpUtility httpUtility = new HttpUtility();
        		 String[] nameTmp= httpUtility.load_recommend("hot");
        		 Message msg = new Message();
        		 msg.obj = nameTmp;
        		 handler.sendMessage(msg);
        		 //handler.obtainMessage(MSG_SUCCESS, nameTmp).sendToTarget();
        	}     	
        }.start();
        
        
        
        System.out.println(name[0]);
        System.out.println(name[1]);
        
        
        TextView title = (TextView) findViewById(R.id.title);
        TextView brief = (TextView) findViewById(R.id.brief);
        
        title.setText(name[0]);
        brief.setText(name[1]);
        
        
	}

}
