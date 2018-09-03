package com.example.money.bee_game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;


public class MainActivity extends Activity {

    ImageView bee_imgleft,ball_img;
    bluetooth_40 Bl_0;
    RelativeLayout bu_xml,top_xml,righ_xml,lef_xml,main_xml,relayout2,relayout1;
    float setfirst_xR,setfirst_xL,setfirst_y,setfirst_zH,setfirst_zL;
    DisplayMetrics v;
    ImageView []beeimg =new ImageView[6];
    ImageView catch_usenet;
    int randomshow =0;
    Thread thread1,thread2,thread3,thread4,thread5,thread6,thread7;
    RelativeLayout.LayoutParams layoutParams,bee1Params,bee2Params,bee3Params,bee4Params,bee5Params,bee6Params;
    private MediaPlayer mediaPlayer;
    int pc_height,pc_width;
    DisplayMetrics displaymetrics;
    ListView listView_use;
    int RorL =0 , random_count,another_count,couunt,random_top;
    boolean catch_stus =false,chang_stus=false;
    int [] mapping=new int[]{0,1,2,3,4,5};
    Timer timer ;
    ArrayList<RelativeLayout.LayoutParams>useAry_parms=new ArrayList<>();
    //ArrayAdapter<String> btArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Find_alluse();
         v =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(v);

        //righ_xml.setVisibility(View.VISIBLE);
       Bl_0 =new bluetooth_40(this);
        timer =new Timer();
        timer.schedule(tsk,0,1000);
        thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        while (true){
                            Message s1 =new Message();
                            s1.what =1;
                            mHandler.sendMessage(s1);
                            Thread.sleep(100);

                        }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        listView_use.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("'jim"," onItemClick :"+Bl_0.mBleName.get(position));
                Bl_0.DEVICE_adress =Bl_0.mBleName.get(position);
                Bl_0.Connectoutside();
                changepage();
            }
        });
        thread_beeall();



    }
    private TimerTask tsk =new TimerTask() {
        @Override
        public void run() {
         /*   couunt++;
            if(couunt %5==0){{
                random_count  =(int)(Math.random()*6);
            }}

            Message s1 =new Message();
            s1.what =8;
            mHandler.sendMessage(s1);*/


        }
    };
    private void thread_beeall(){
        thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =2;
                        mHandler.sendMessage(s1);
                        Thread.sleep(400);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread3 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =3;
                        mHandler.sendMessage(s1);
                        Thread.sleep(100);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread4 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =4;
                        mHandler.sendMessage(s1);
                        Thread.sleep(600);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread5 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =5;
                        mHandler.sendMessage(s1);
                        Thread.sleep(100);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread6 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =6;
                        mHandler.sendMessage(s1);
                        Thread.sleep(200);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread7 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =7;
                        mHandler.sendMessage(s1);
                        Thread.sleep(250);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

      thread1.start();
        thread2.start();
       thread3.start();
       /* thread4.start();
        thread5.start();
        thread6.start();*/
       // thread7.start();

    }
    private  void changepage(){
        if(Bl_0.BLe_stus==true){

            relayout2.setVisibility(View.GONE);
            relayout1.setVisibility(View.VISIBLE);

        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
               // bee_imgleft.layout(bee_imgleft.getLeft(),bee_imgleft.getTop(),bee_imgleft.getRight()+50,bee_imgleft.getBottom());

                randomshow =(int)(Math.random()*3);
               // Log.i("jim","random"+randomshow);

                break;
            case R.id.right_btn:
                setfirst_xR =Bl_0.mpu_x;
                Log.i("jim","setright:   "+setfirst_xR);
                Log.i("jim","densityDpi:   "+v.widthPixels);
                Log.i("jim","densityDpi:   "+v.heightPixels);  //平板1920*1200
                righ_xml.setVisibility(View.GONE);
                lef_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.left_btn:
                setfirst_xL=Bl_0.mpu_x;
                Log.i("jim","setleft:   "+setfirst_xL);
                lef_xml.setVisibility(View.GONE);
                top_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.top_btn:
                setfirst_zH=Bl_0.mpu_z;
                Log.i("jim","settop:   "+setfirst_zH);
                top_xml.setVisibility(View.GONE);
                bu_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.buttom_btn_btn:
                setfirst_zL=Bl_0.mpu_z;
                Log.i("jim","setdown:   "+setfirst_zL);
                bu_xml.setVisibility(View.GONE);
                main_xml.setVisibility(View.VISIBLE);
                break;
        }
    }
    private  void Find_alluse(){
        bu_xml    =(RelativeLayout)findViewById(R.id.buttom_xml);
        top_xml   =(RelativeLayout)findViewById(R.id.top_xml);
        righ_xml  =(RelativeLayout)findViewById(R.id.right_xml);
        lef_xml   =(RelativeLayout)findViewById(R.id.left_xml);
        main_xml =(RelativeLayout)findViewById(R.id.main_xml);
        catch_usenet=(ImageView)findViewById(R.id.catch_net);
        ball_img =(ImageView)findViewById(R.id.ball_img);
        listView_use=(ListView)findViewById(R.id.listView);
        //bee_imgleft=(ImageView)findViewById(R.id.bee_lftig);
        beeimg[0]=(ImageView)findViewById(R.id.bee_lftig1);
        beeimg[1]=(ImageView)findViewById(R.id.bee_lftig2);
        beeimg[2]=(ImageView)findViewById(R.id.bee_lftig3);
        beeimg[3]=(ImageView)findViewById(R.id.bee_rgtig1);
        beeimg[4]=(ImageView)findViewById(R.id.bee_rgtig2);
        beeimg[5]=(ImageView)findViewById(R.id.bee_rgtig3);

       // beeimg[0].setVisibility(View.VISIBLE);
        relayout2=(RelativeLayout)findViewById(R.id.bluelist) ;
        relayout1=(RelativeLayout)findViewById(R.id.main_xml) ;
        //-------------------------------------------------------------
        layoutParams =(RelativeLayout.LayoutParams) catch_usenet.getLayoutParams();
        //Log.i("jim","網子右邊"+layoutParams.width);
        bee1Params =(RelativeLayout.LayoutParams) beeimg[0].getLayoutParams();
        //Log.i("jim","網子右2邊"+bee1Params.width);
        bee2Params =(RelativeLayout.LayoutParams) beeimg[1].getLayoutParams();
       // Log.i("jim","網子右3邊"+bee2Params.width);
        bee3Params =(RelativeLayout.LayoutParams) beeimg[2].getLayoutParams();
        bee4Params =(RelativeLayout.LayoutParams) beeimg[3].getLayoutParams();
        bee5Params =(RelativeLayout.LayoutParams) beeimg[4].getLayoutParams();
        bee6Params =(RelativeLayout.LayoutParams) beeimg[5].getLayoutParams();
        useAry_parms.add(bee1Params);
        useAry_parms.add(bee2Params);
        useAry_parms.add(bee3Params);
        useAry_parms.add(bee4Params);
        useAry_parms.add(bee5Params);
        useAry_parms.add(bee6Params);
        displaymetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        pc_height =displaymetrics.heightPixels;
        pc_width =displaymetrics.widthPixels;
        //Log.i("'jim"," pc_height :"+pc_height);
       // Log.i("'jim"," pc_width :"+pc_width);
       /* mediaPlayer =new MediaPlayer();
        mediaPlayer =MediaPlayer.create(this, R.raw.music1);
        mediaPlayer.start();*/
        //---------------------------------------------------------------
        Bl_0.mBleName =new  ArrayList<>();
        Bl_0.btArrayAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,Bl_0.mBleName );
        listView_use.setAdapter(Bl_0.btArrayAdapter);
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                   movehand();
                 //  Random_obj();
                   Catch_function(layoutParams,bee1Params,beeimg[0]);
                    break;
                case  2:
                    RorL=1;
                    move_useobj(bee1Params,beeimg[0],RorL,useAry_parms);
                    break;
                case 3:
                    RorL=1;
                    move_useobj(bee2Params,beeimg[1],RorL,useAry_parms);
                    break;
                case 4:
                    RorL=1;
                    move_useobj(bee3Params,beeimg[2],RorL,useAry_parms);
                    break;
                case 5:
                    RorL=2;
                    move_useobj(bee4Params,beeimg[3],RorL,useAry_parms);
                    break;
                case  6:
                    RorL=2;
                    move_useobj(bee5Params,beeimg[4],RorL,useAry_parms);
                    break;
                case  7:
                    RorL=2;
                    move_useobj(bee6Params,beeimg[5],RorL,useAry_parms);
                    break;
                case 8:
                    //Random_obj();
                    break;

            }
        }
    };
    private  void Random_obj(){
           // Log.i("jim","random_count :" +random_count);
           for(int a=0;a<mapping.length;a++){
               beeimg[mapping[a]].setVisibility(View.GONE);


           }
        random_count  =(int)(Math.random()*2);
           beeimg[random_count].setVisibility(View.VISIBLE);

    }
    private  void movehand(){
       if(Bl_0.mpu_movestus==true){
           // catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop(), catch_usenet.getRight()+50,catch_usenet.getBottom());

          if(layoutParams.leftMargin + 50<pc_width - layoutParams.width){
              layoutParams.leftMargin+=50;
              catch_usenet.setLayoutParams(layoutParams);
           }
        }else{
            //catch_usenet.layout( catch_usenet.getLeft()-50, catch_usenet.getTop(), catch_usenet.getRight(),catch_usenet.getBottom());
           if(layoutParams.leftMargin - 50>0){
               layoutParams.leftMargin-=50;
               catch_usenet.setLayoutParams(layoutParams);
           }

        }
        if(Bl_0.mpu_moveup==true){
            //catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop()-10, catch_usenet.getRight(),catch_usenet.getBottom());
            if(layoutParams.topMargin - 50>0){
                layoutParams.topMargin-=50;
                catch_usenet.setLayoutParams(layoutParams);
            }

        }
        if(Bl_0.mpu_moveup==false){
           // catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop(), catch_usenet.getRight(),catch_usenet.getBottom()+10);
            if(layoutParams.topMargin +50<1200-layoutParams.height){
                layoutParams.topMargin+=50;
                catch_usenet.setLayoutParams(layoutParams);
            }
        }
        if(Bl_0.swing_stus==true){

        }else{
           // ball_img.setVisibility(View.VISIBLE);
        }

    }
    private  void move_useobj(RelativeLayout.LayoutParams layoutParams1 , ImageView imageView1,int direction,ArrayList<RelativeLayout.LayoutParams> FF){
        //移動物件，6個物件
        //
        switch (direction){
            case  1:
                //左
                if(layoutParams1.leftMargin+ 50<pc_width - bee1Params.width){
                    layoutParams1.leftMargin+=50;
                    imageView1.setLayoutParams(layoutParams1);
                   // chang_stus=false;
                }else{
                    //layoutParams1.leftMargin=0;
                   // imageView1.setLayoutParams(layoutParams1);
                    for (int g=0;g<FF.size();g++){
                        FF.get(g).leftMargin =0;
                        imageView1.setLayoutParams( FF.get(g));
                    }
                  //  random_count  =(int)(Math.random()*2);
                    Random_obj();
                }
                if(layoutParams1.leftMargin+ 50 ==pc_width- bee1Params.width ){
                    chang_stus=true;
                }
                random_top  =50-((int)(Math.random()*100));
                if(layoutParams1.topMargin>0){
                    layoutParams1.topMargin+=random_top;
                    imageView1.setLayoutParams(layoutParams1);

                }
                if(layoutParams1.topMargin==0){
                    layoutParams1.topMargin=100;
                    imageView1.setLayoutParams(layoutParams1);

                }
                break;
            case 2:
                //右
                if(layoutParams1.leftMargin- 50>0){
                    layoutParams1.leftMargin-=50;
                    imageView1.setLayoutParams(layoutParams1);
                }else{
                    layoutParams1.leftMargin=1900;
                    imageView1.setLayoutParams(layoutParams1);
                }
                break;
        }
    }
    private  void Catch_function(RelativeLayout.LayoutParams net_Params,RelativeLayout.LayoutParams bee_Params,ImageView obj_game){
        /*
        *  jim 捕抓
        *  若捕抓物件左右在網子的左右間，及物件上下載往子的上下之間
        *  物件寬度為105，網子為240
        * */
    if(obj_game.getVisibility() ==View.VISIBLE){
        if(bee_Params.leftMargin>net_Params.leftMargin &&(bee_Params.leftMargin + 105 < net_Params.leftMargin +240)){
            //Log.i("jim","捕抓成功");
        }
    }else{

    }



    }




}
