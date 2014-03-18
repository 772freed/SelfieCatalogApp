package com.selfiecatalog;

import java.io.File;
import java.io.FileOutputStream;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Telephony.Sms.Intents;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	


		
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
	private Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 };
	
	private ImageView imageView;
	ImageView ivPhoto;
	File myFilesDir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    ivPhoto = (ImageView) findViewById(R.id.imageView1);
	    myFilesDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.selfiecatalog/files");
	    System.out.println (myFilesDir);
	    myFilesDir.mkdirs();
		
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this));
		imageView = (ImageView) findViewById(R.id.imageView1);
		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "pic: " + arg2,
						Toast.LENGTH_SHORT).show();
				imageView.setImageResource(pics[arg2]);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	public class ImageAdapter extends BaseAdapter {
		private Context context;
		int imageBackground;

		public ImageAdapter(Context context) {
			this.context = context;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pics.length;
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(pics[arg0]);
			return imageView;
		}
	}
	


	
	public void gotoActivity1(View v){// for gallery
		
		Intent intent = new Intent(this, GalleryActivity.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "Open Gallery", Toast.LENGTH_SHORT).show();
	}
	
	public void gotoActivity(View v){
	    Intent camIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(myFilesDir.toString()+"/temp.jpg")));
	    startActivityForResult(camIntent, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode==0){
	        try {
	            Bitmap cameraBitmap;
	            cameraBitmap = BitmapFactory.decodeFile(myFilesDir + "/temp.jpg");
	            Bitmap.createBitmap(cameraBitmap);
	            ivPhoto.setImageBitmap(cameraBitmap);
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
}

