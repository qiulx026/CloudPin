package com.example.myfirstapp;


import java.io.InputStream;
import org.json.*; 
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.example.myfirstapp.MainActivity.Sign_in_button_Listener;

import android.R.string;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class secondac extends Activity 
{
	/** Called when the activity is first created. */
	private Button search_buttom=null;
	private EditText search_text=null;
	private TextView  searchresult= null;
	public static String content;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle icicle) 
	{
		super.onCreate(icicle);
		setContentView(R.layout.second_main);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads().detectDiskWrites().detectNetwork() // or
                                                                // .detectAll()
                                                                // for
                                                                // all
                                                                // detectable
                                                                // problems
        .penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
        .penaltyLog().penaltyDeath().build());
		searchresult = (TextView) findViewById(R.id.searchresult);
		search_text = (EditText) findViewById(R.id.search_text);
		search_buttom = (Button) findViewById(R.id.search_buttom);
		search_buttom.setOnClickListener((OnClickListener) new Search_buttom_Listener());
	}
	class Search_buttom_Listener implements OnClickListener{  


		@Override
			public void onClick(View v){
				String search_text_string= search_text.getText().toString(); 
				//String url = "http://cloudpin.herokuapp.com"+"?pin="+search_text_string;
				//Intent intent = new Intent(Intent.ACTION_VIEW);
				//intent.setData(Uri.parse(url));
				//startActivity(intent);
				//intent.setClass(MainActivity.this,secondac.class);
				//MainActivity.this.startActivity(intent);
				//���뽫Ҫ���͸�����������Ϣ���Լ�ֵ�Է�ʽ���漴?name=zhangsan
				String params = search_text_string;
				//������ƴ����URl��ַ����
				try{
					URL url = new URL("http://cloudpin.herokuapp.com/searchfile?pin=" + params);
					//ͨ��url��ַ������
					System.out.println("OPEN URL");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					//���ó�ʱʱ��
					conn.setConnectTimeout(3000);
					//��������ʽ
					conn.setRequestMethod("GET");
					//������ص�״̬����200����һ��Ok�����ӳɹ���
					if(conn.getResponseCode() == 200)
					{
						InputStream in = conn.getInputStream();//��ȡweb��������Ӧ����
						byte[] b = new byte[8000];
						in.read(b);
						content = new String(b,"utf-8").trim();//ת���ı���Ҫ��Web������һ��
						in.close();
						JSONObject a = new JSONObject(content.substring(1,content.length()-1));
						String realurl = (String) a.get("url");
						searchresult.setText(realurl);
						System.out.println(realurl);
					}
				}catch (Exception e) {  
					e.printStackTrace();  
					Exception mException = e;  
				}   
		}
	}
}