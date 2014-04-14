package com.example.myfirstapp;


import com.example.myfirstapp.MainActivity.Sign_up_button_Listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sign_in extends Activity 
{
    /** Called when the activity is first created. */
	private Button submission_button=null;
    @Override
    public void onCreate(Bundle icicle) 
   {
        super.onCreate(icicle);
        setContentView(R.layout.sign_in_main);
        submission_button = (Button) findViewById(R.id.submission);
        submission_button.setOnClickListener((OnClickListener) new Submission_button_Listener());
    }
    
    class Submission_button_Listener implements OnClickListener{  
    	
    	@Override
    	public void onClick(View v){
    		Intent intent=new Intent();
    		intent.setClass(Sign_in.this,MainActivity.class);
    		Sign_in.this.startActivity(intent);
    	}
    }
    
} 