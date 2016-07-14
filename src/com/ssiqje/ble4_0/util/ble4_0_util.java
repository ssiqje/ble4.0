package com.ssiqje.ble4_0.util;

import java.util.List;

import com.ssiqje.ble4_0.MainActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class ble4_0_util {
	private BluetoothGattCallback callback;
	private BluetoothGatt mbBluetoothGatt;
	private List<BluetoothGattService> listServers;
	private BluetoothAdapter mBluetoothAdapter;
	private Context context;
	private BluetoothManager mBluetoothManager;
	private boolean isonDiscovery=false;
	private Handler mHandler=new Handler();
	private BluetoothAdapter.LeScanCallback mLeScanCallback;
	@SuppressLint("NewApi")
	public ble4_0_util(final Context context) {
		Log.i("info", "工具类开始初始化！");
		this.context=context;
		Log.i("info", "工具类开始初始化！context");
		mBluetoothManager=(BluetoothManager) context.getSystemService(context.BLUETOOTH_SERVICE);
		Log.i("info", "工具类开始初始化！mBluetoothManager");
		mBluetoothAdapter=mBluetoothManager.getAdapter();
		Log.i("info", "工具类开始初始化！mBluetoothAdapter");
		callback=new BluetoothGattCallback() {

			@SuppressLint("NewApi")
			@Override
			public void onCharacteristicChanged(BluetoothGatt gatt,
					BluetoothGattCharacteristic characteristic) {
				// TODO Auto-generated method stub
				super.onCharacteristicChanged(gatt, characteristic);
			}

			@Override
			public void onCharacteristicRead(BluetoothGatt gatt,
					BluetoothGattCharacteristic characteristic, int status) {
				// TODO Auto-generated method stub
				super.onCharacteristicRead(gatt, characteristic, status);
			}

			@Override
			public void onCharacteristicWrite(BluetoothGatt gatt,
					BluetoothGattCharacteristic characteristic, int status) {
				// TODO Auto-generated method stub
				super.onCharacteristicWrite(gatt, characteristic, status);
			}

			@Override
			public void onConnectionStateChange(BluetoothGatt gatt, int status,
					int newState) {
				// TODO Auto-generated method stub
				super.onConnectionStateChange(gatt, status, newState);
				if(status==BluetoothGatt.GATT_SUCCESS)
				{
					if(newState==BluetoothGatt.STATE_CONNECTED)
					{
						listServers=gatt.getServices();
					}
				}
			}

			@Override
			public void onDescriptorRead(BluetoothGatt gatt,
					BluetoothGattDescriptor descriptor, int status) {
				// TODO Auto-generated method stub
				super.onDescriptorRead(gatt, descriptor, status);
			}

			@Override
			public void onDescriptorWrite(BluetoothGatt gatt,
					BluetoothGattDescriptor descriptor, int status) {
				// TODO Auto-generated method stub
				super.onDescriptorWrite(gatt, descriptor, status);
			}

			@Override
			public void onReadRemoteRssi(BluetoothGatt gatt, int rssi,
					int status) {
				// TODO Auto-generated method stub
				super.onReadRemoteRssi(gatt, rssi, status);
			}

			@Override
			public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
				// TODO Auto-generated method stub
				super.onReliableWriteCompleted(gatt, status);
			}

			@Override
			public void onServicesDiscovered(BluetoothGatt gatt, int status) {
				// TODO Auto-generated method stub
				super.onServicesDiscovered(gatt, status);
			}
			
		};
		Log.i("info", "工具类开始初始化！callback");
		mLeScanCallback =
		        new BluetoothAdapter.LeScanCallback() {
		    @Override
		    public void onLeScan(final BluetoothDevice device, int rssi,
		            byte[] scanRecord) {
		    	Log.i("info", "找到一个东西！"+device.getName());
		   }
		};
		Log.i("info", "工具类开始初始化！leScanCallback");
		}
		
	@SuppressLint("NewApi")
	public void getromeDevices(BluetoothAdapter pBluetoothAdapter)
	{
		pBluetoothAdapter.startLeScan((LeScanCallback) callback);
	}
	@SuppressLint("NewApi")
	public BluetoothGatt connectGatt(Context context,boolean isAutoConnect,BluetoothDevice device)
	{
		
		return device.connectGatt(context, isAutoConnect, callback);
		
	}
	public List<BluetoothGattService> getBluetoothGattServers(BluetoothGatt bluetoothGatt)
	{
		
		return listServers;
		
	}

	public  boolean isBle4() {
		// TODO Auto-generated method stub
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
			return true;
		else {
			return false;
		}
		
	}

	@SuppressLint("NewApi")
	public  void discovery() {
		// TODO Auto-generated method stub
		if(!isonDiscovery)
		{
			Log.i("info", "通知，十秒后停止扫描！");
			mHandler.postDelayed(new Runnable() {
				
				@SuppressLint("NewApi")
				@Override
				public void run() {
					// TODO Auto-generated method stub
					isonDiscovery=false;
					mBluetoothAdapter.stopLeScan(mLeScanCallback);
					Log.i("info", "扫描结束！");
				}
			}, 10*1000);
			Log.i("info", "扫描FLAG=TRUE");
			isonDiscovery=true;
			Log.i("info", "启动扫描！");
			try {
				mBluetoothAdapter.startLeScan(mLeScanCallback);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			Log.i("info", "扫描开始");
		}
		
	}
	
}
