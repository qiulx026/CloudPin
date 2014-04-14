package com.example.myfirstapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{
    /** Called when the activity is first created. */
	private Button sign_up_button=null;
			Button sign_in_button=null;
			Button skip_button=null;
    @Override  
    public void onCreate(Bundle icicle) 
   {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        sign_up_button = (Button) findViewById(R.id.sign_up);
        sign_up_button.setOnClickListener((OnClickListener) new Sign_up_button_Listener());
        sign_in_button = (Button) findViewById(R.id.sign_in);
        sign_in_button.setOnClickListener((OnClickListener) new Sign_in_button_Listener()); 
        skip_button=(Button) findViewById(R.id.skip);
        skip_button.setOnClickListener((OnClickListener) new Skip_button_Listener());
    }    
    class Sign_in_button_Listener implements OnClickListener{  
    	
    	@Override
    	public void onClick(View v){
    		Intent intent=new Intent();
    		intent.setClass(MainActivity.this,secondac.class);
    		MainActivity.this.startActivity(intent);
    	}
    }
    class Sign_up_button_Listener implements OnClickListener{  
    	
    	@Override
    	public void onClick(View v){
    		Intent intent=new Intent();
    		intent.setClass(MainActivity.this,Sign_in.class);
    		MainActivity.this.startActivity(intent);
    	}
    }
    class Skip_button_Listener implements OnClickListener{
    	
    	@Override
    	public void onClick(View v){
    		Intent intent=new Intent();
    		intent.setClass(MainActivity.this,secondac.class);
    		MainActivity.this.startActivity(intent);
    	}
    }
}
