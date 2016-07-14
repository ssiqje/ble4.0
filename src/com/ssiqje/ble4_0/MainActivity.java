package com.ssiqje.ble4_0;

import com.ssiqje.ble4_0.util.ble4_0_util;
import com.ssiqje.ble4_0_util.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	ble4_0_util ble4_0_util;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ble4_0_util=new ble4_0_util(this);
		if(ble4_0_util.isBle4())
		{
			Log.i("info", "支持4.0，准备进行扫描！");
			ble4_0_util.discovery();
			
		}
		else {
			Toast.makeText(this, "本机不支持4.0", Toast.LENGTH_LONG).show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
