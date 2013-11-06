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

import android.widget.EditText;

public class HttpUtility {

	private String result = ""; 
  
	public HttpUtility()
	{
		
	}
	
	public String[] list_book(String userName)
	{	    
	    // creating new product in background thread
		String strUrl = "http://10.0.2.2/bookValley_Server/list_book_bookValley.php";
		URL url = null;
		HttpPost httpRequest = new HttpPost(strUrl);
		List <NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", userName));
		try{
			httpRequest.setEntity((HttpEntity) new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
			//若状态码为200 ok 
		    if(httpResponse.getStatusLine().getStatusCode()==200){
		        //取出回应字串
		        result=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
		     }
		     else
		     {
		         result = "Error Response"+httpResponse.getStatusLine().toString();
		     }
			 
		}catch(ClientProtocolException e)
		{     
		    e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e)
		{	    		     
		    e.printStackTrace();
		} 
		catch (IOException e)
		{     
		    e.printStackTrace();
		}
		String[] results = result.split(" ");
		return results;
	}
}
