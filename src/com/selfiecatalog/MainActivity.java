package com.selfiecatalog;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoActivity(View v) {

	//define the file-name to save photo taken by Camera activity
	String fileName = "new-photo-name.jpg";
	//create parameters for Intent with filename
	ContentValues values = new ContentValues();
	values.put(MediaStore.Images.Media.TITLE, fileName);
	values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
	//imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
	Uri imageUri = getContentResolver().insert(
	        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	//create new Intent
	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
		    if (resultCode == RESULT_OK) {
		        //use imageUri here to access the image
		 
		    } else if (resultCode == RESULT_CANCELED) {
		        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
		    } else {
		        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
		    }
		}
		}
	
	
}

