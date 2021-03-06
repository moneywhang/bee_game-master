package com.example.money.bee_game;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class bluetooth_40 {

    Timer mTimer;
    private BluetoothAdapter mBluetoothAdapter;//our local adapter
    private static final long SCAN_PERIOD = 1000; //5 seconds
    private static List<BluetoothDevice> mDevices = new ArrayList<BluetoothDevice>();//discovered devices in range
    private BluetoothDevice mDevice; //external BLE device (Grove BLE module)
    private static BluetoothGatt mBluetoothGatt; //provides the GATT functionality for communication
    public static String DEVICE_NAME = "MLT-BT05";//BT05-A
    public static String DEVICE_adress = "C8:FD:19:44:04:3B";//BT05-A
    private static final String GROVE_SERVICE = "0000ffe0-0000-1000-8000-00805f9b34fb";
    private static final String CHARACTERISTIC_TX = "0000ffe1-0000-1000-8000-00805f9b34fb";
    private static final String CHARACTERISTIC_RX = "0000ffe1-0000-1000-8000-00805f9b34fb";
    public boolean BLe_stus =false ,connect_stus =false;
    public static BluetoothGattService mBluetoothGattService; //service on mBlueoothGatt
    private Activity activity;

   public String rssi_string;
   public     String readdata_ble,readdata_ble1,readdata_ble2,readdata_ble3 ;
    public int rssi_int1;
    BluetoothDevice device;

    public boolean A_disable =false,A_connectstus=false,swip_stus =false;
    public static float [] blesetAry =new float[30];
    public static float [] blesetAry1 =new float[30];
    public static float [] blesetAry2 =new float[30];
    public static float [] blesetAry3 =new float[30];
    private  int blesetcount =0,blesetcount1=0,blesetcount2 =0,blesetcount3=0;
    float blee,blee1,blee2,blee3;
    public static boolean drawstus =false,serch_stus=false;
    public  float mpu_x,mpu_y,mpu_z,mpu_z_last;
    //-----------------------------------------------
    public boolean mpu_movestus=false,mpu_moveup =false,swing_stus =false;
    public static  List<String>mBleName;
    public static  ArrayAdapter<String> btArrayAdapter;
    public bluetooth_40(Activity activity1, Activity activity) {
    }

    public bluetooth_40(Activity activity) {
        this.activity = activity;
        //Log.e("money","one1");
        final BluetoothManager mBluetoothManager = (BluetoothManager) activity.getSystemService(activity.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
      //  mBleName =new  ArrayList<>();
        searchForDevices();


    }


    public static void bluetoothset(String blumessage) {
        sendMessage(blumessage);
    }



    private static void statusUpdate(final String msg) {
        new Runnable() {
            @Override
            public void run() {
                Log.w("BLE", msg);
            }
        };

    }


    private void searchForDevices() {
        mTimer = new Timer();
        scanLeDevice();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                findGroveBLE();
                //Log.e("money","two");
            }
        }, SCAN_PERIOD);


    }
//----------------rssi

    public static void sendMessage(String _msg) {
        if (mBluetoothGattService == null)
            return;
       // Log.i("SS", "22");

       // statusUpdate("Finding Characteristic...");

        BluetoothGattCharacteristic gattCharacteristic =
                mBluetoothGattService.getCharacteristic(UUID.fromString(CHARACTERISTIC_TX));

        if (gattCharacteristic == null) {
          //  statusUpdate("Couldn't find TX characteristic: " + CHARACTERISTIC_TX);
            return;
        }

        //statusUpdate("Found TX characteristic: " + CHARACTERISTIC_TX);

        //statusUpdate("Sending message 'Hello Grove BLE'");

        String msg = _msg;

        byte b = 0x00;
        byte[] temp = msg.getBytes();
        byte[] tx = new byte[temp.length + 1];
        tx[0] = b;

        for (int i = 0; i < temp.length; i++)
            tx[i + 1] = temp[i];

        gattCharacteristic.setValue(tx);
        mBluetoothGatt.writeCharacteristic(gattCharacteristic);

    }
   public static void  sendMessage_byte(byte[] bbytes) {
     //  Log.i("jim","ble_sentin");
       if (mBluetoothGattService == null)
           return;
       BluetoothGattCharacteristic gattCharacteristic =
               mBluetoothGattService.getCharacteristic(UUID.fromString(CHARACTERISTIC_TX));

       if (gattCharacteristic == null) {
          // statusUpdate("Couldn't find TX characteristic: " + CHARACTERISTIC_TX);
           return;
       }
       byte b1 = 0x00;
       byte[] temp1 = bbytes;
       byte[] tx1 = new byte[temp1.length + 1];
       for (int i = 0; i < temp1.length; i++)
           tx1[i + 1] = temp1[i];
       gattCharacteristic.setValue(tx1);
       mBluetoothGatt.writeCharacteristic(gattCharacteristic);
   }

    private void scanLeDevice() {
        new Thread() {

            @Override
            public void run() {
                mBluetoothAdapter.startLeScan(mLeScanCallback);

                try {
                    Log.e("money","scan");
                    Thread.sleep(SCAN_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mBluetoothAdapter.stopLeScan(mLeScanCallback);
            }
        }.start();

    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            if (device != null) {
                if (mDevices.indexOf(device) == -1)//to avoid duplicate entries
                {
                    //if (DEVICE_NAME.equals(device.getName()))
                   // Log.i(" money ", "Adress:   " + device.getAddress());
                   /* if (DEVICE_adress.equals(device.getAddress()))
                    {
                        mDevice = device;//we found our device!
                      //  Log.i(" money ", "Added " + device);
                       // Log.i(" money ", "Added " + device.getName() + ": " + device.getAddress());
                    }*/
                //    mDevices.add(device); //原先
                 //   statusUpdate("Found device " + device.getName());
                    //-----------------------------------------------------------------
                    if(!mDevices.contains(device)){
                        mDevices.add(device);
                        mBleName.add(device.getAddress());
                        btArrayAdapter.notifyDataSetChanged();
                        serch_stus =true;

                    }
                }
                //--------添加陣列


            }
        }
    };

    private void findGroveBLE() {
        if (mDevices == null || mDevices.size() == 0) {
           // Log.i("money","no dvicw");
           // statusUpdate("No BLE devices found");
            return;
        } else if (mDevice == null) {
            //Log.i("money","dvice null");
          //  statusUpdate("Unable to find Grove BLE");
            return;
        } else {
            //Log.i("money","find");
           // statusUpdate("Found Grove BLE V1");
            //statusUpdate("Address: " + mDevice.getAddress());
          //  connectDevice();
        }
    }
    public void Connectoutside(){
        if(serch_stus ==true){
            //收尋完成
            for(int i=0;i<mDevices.size();i++){
                //  Log.i(" money ", "get" +blearray.get(i));

                if(DEVICE_adress.equals(mDevices.get(i).getAddress())){
                    Log.i(" money ", "連接" +mDevices.get(i));
                    // mDevice = device;
                    mDevice = mDevices.get(i);
                    connectDevice();

                }
            }
        }
    }
    private boolean connectDevice() {
         device = mBluetoothAdapter.getRemoteDevice(mDevice.getAddress());
        if (device == null) {
           // Log.i("money","device_null");
           // statusUpdate("Unable to connect");
            return false;
        }
        // directly connect to the device
        //statusUpdate("Connecting ...");

      mBluetoothGatt = device.connectGatt(activity, false, mGattCallback);///jim這是自動連接的藍芽
        BLe_stus =true;

        return true;
    }
    public void Ble_Disconnect(){
        mBluetoothGatt.disconnect();
        mBluetoothGatt.disconnect();
        mBluetoothGatt.disconnect();
        mBluetoothGatt.disconnect();
        A_connectstus =false;

    }
    public void Ble_Reconnect(){
        mBluetoothGatt = device.connectGatt(activity, false, mGattCallback);


    }

public void Read_Rssi(){
    mBluetoothGatt.readRemoteRssi();//rssi
}
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                //Log.i("money","Connected");
                //statusUpdate("Connected");
                //statusUpdate("Searching for services");
                mBluetoothGatt.discoverServices();
                A_disable=false;
                A_connectstus =true;

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                //statusUpdate("Device disconnected");
                //Log.i("money","disconnected");
                A_disable =true;
//                mBluetoothGatt.close();
                rssi_int1 =-60;
            }
        }


        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                List<BluetoothGattService> gattServices = mBluetoothGatt.getServices();

                for (BluetoothGattService gattService : gattServices) {
                    //statusUpdate("Service discovered: " + gattService.getUuid());
                    if (GROVE_SERVICE.equals(gattService.getUuid().toString())) {
                     //   Log.i("money","communication Service");
                        mBluetoothGattService = gattService;
                      //  statusUpdate("Found communication Service");
                        sendMessage("");
                    }
                }
                BluetoothGattCharacteristic GattCharacteristic_RX = mBluetoothGattService.getCharacteristic(UUID.fromString(CHARACTERISTIC_RX));
                mBluetoothGatt.setCharacteristicNotification(GattCharacteristic_RX, true);
            } else {
               // statusUpdate("onServicesDiscovered received: " + status);
            }
        }


        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            //broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            //Log.i("onCharacteristicChanged",TAG);
            byte[] data = characteristic.getValue();
            try {
                String str = new String(data, "UTF-8");
               // statusUpdate(str);
              //  Log.i("jim", "Fin11111111d");
                //jim 讀取資料
              readdata_ble =str;
                spilt_data(readdata_ble);
               // Log.i("Neo", "readdata_ble" +readdata_ble);

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {

            super.onReadRemoteRssi(gatt, rssi, status);
            //this.rssi =rssi;
            rssi_string =""+ rssi;
            rssi_int1 =rssi;
        }
    };
    private  void spilt_data(String readuse){
        String[] split_ary = readuse.split(",");
       // Log.i("jim","readuse :  "+readuse);
        readdata_ble =split_ary[0];
        readdata_ble1 =split_ary[2];
        readdata_ble2 =split_ary[3];
        readdata_ble3 =split_ary[4];
        blee =Float.valueOf(readdata_ble);
        blee1 =Float.valueOf(readdata_ble1);
        blee2 =Float.valueOf(readdata_ble2);
        blee3 =Float.valueOf(readdata_ble3);
        //--------------------------------------------------
        mpu_x =Float.valueOf(readdata_ble1);

        mpu_y =Float.valueOf(readdata_ble2);

        mpu_z =Float.valueOf(readdata_ble3);
       // Log.i("jim","mpu_z :  "+mpu_z);
      /* Log.i("jim","mpu6050_x :  "+mpu_x);
        Log.i("jim","mpu6050_y :  "+mpu_y);
        Log.i("jim","mpu6050_z :  "+mpu_z);*/

        drawstus=false;
        gameuse(mpu_x,mpu_y,mpu_z);
    }
    private  void gameuse(float movedataX,float movedataY,float movedataZ){
        //若x軸為正值 往右移動
        if(movedataX>0){
            mpu_movestus =true;
          //  Log.i("jim","左移");
        }else{
            mpu_movestus =false;
        }
        if(movedataY<0){
            mpu_moveup =true;
           // Log.i("jim","右移");
        }
        if(movedataY>0)
        {
            mpu_moveup =false;

        }
        //揮擊成立
        //Log.i("jim","movedataZ"+movedataZ);
        //若最新減去上一筆
        if(movedataZ-mpu_z_last>0.5 &&mpu_z_last!=0 &&swip_stus==true){
            //Log.i("jim","揮擊");
            swing_stus =true;
        }else{
            swing_stus =false;
        }
        mpu_z_last =movedataZ;
        if(movedataZ<0.4){
            swip_stus =true;
        }else{
            swip_stus =false;
        }


    }





}



