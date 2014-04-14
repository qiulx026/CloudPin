package com.example.myfirstapp;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;

import com.example.myfirstapp.secondac.Search_button_Listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class upload extends Activity{
	
	private Button search_file_button=null;
	private Button choose_file_button=null;
	private Button upload_button=null;
	private Button change_pin_button=null;
	private EditText file_name=null;
	private EditText pin_name=null;
	
	@Override	
	public void onCreate(Bundle icicle) 
	   {
	        super.onCreate(icicle);
	        setContentView(R.layout.upload_main);
	        search_file_button= (Button) findViewById(R.id.search_file_button);
	        choose_file_button= (Button) findViewById(R.id.choose_file_button);
	        choose_file_button.setOnClickListener((OnClickListener) new choose_file_button_listener());
	        upload_button= (Button) findViewById(R.id.upload_file_button);
	        change_pin_button= (Button) findViewById(R.id.change_pin_button);
	        file_name = (EditText) findViewById(R.id.file_name);
	        pin_name = (EditText) findViewById(R.id.pin);
	        
	    }
	private static final int FILE_SELECT_CODE = 0;
	
	class choose_file_button_listener implements OnClickListener{
		@Override
		public void onClick(View v){
			showFileChooser();
		}		
	}
	
	
	private void showFileChooser() {
	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	    intent.setType("*/*"); 
	    intent.addCategory(Intent.CATEGORY_OPENABLE);

	    try {
	        startActivityForResult(
	                Intent.createChooser(intent, "Select a File to Upload"),
	                FILE_SELECT_CODE);
	    } catch (android.content.ActivityNotFoundException ex) {
	        // Potentially direct the user to the Market with a Dialog
	        Toast.makeText(this, "Please install a File Manager.", 
	                Toast.LENGTH_SHORT).show();
	    }
	}
	private static final String TAG = upload.class.getName();
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	        case FILE_SELECT_CODE:
	        if (resultCode == RESULT_OK) {
	            // Get the Uri of the selected file 
	            Uri uri = data.getData();
	            //Log.d(TAG, "File Uri: " + uri.toString());
	            // Get the path
	            try {
					String path = getPath(this, uri);
					file_name.setText(path);
		            Log.d(TAG, "File Path: " + path);
		            // Get the file instance
		             File file = new File(path);
		            // Initiate the upload
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        break;
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	public String getPath(Context context, Uri uri) throws URISyntaxException {
	    if ("content".equalsIgnoreCase(uri.getScheme())) {
	        String[] projection = { "_data" };
	        Cursor cursor = null;

	        try {
	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
	            int column_index = cursor.getColumnIndexOrThrow("_data");
	            if (cursor.moveToFirst()) {
	                return cursor.getString(column_index);
	            }
	        } catch (Exception e) {
	            // Eat it
	        }
	    }
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	} 
	
	
}
