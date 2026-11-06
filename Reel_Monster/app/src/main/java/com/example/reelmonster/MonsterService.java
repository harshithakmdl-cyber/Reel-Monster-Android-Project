package com.example.reelmonster;
import android.app.*; import android.content.*; import android.graphics.Color; import android.graphics.PixelFormat; import android.os.*; import android.view.*; import android.widget.*;
public class MonsterService extends Service {
 WindowManager wm; View bubble; int count=0; WindowManager.LayoutParams lp;
 public IBinder onBind(Intent i){return null;}
 public void onCreate(){super.onCreate(); wm=(WindowManager)getSystemService(WINDOW_SERVICE); showBubble();}
 void showBubble(){
  TextView t=new TextView(this); t.setText("👾\nTap me +1"); t.setTextSize(16); t.setTextColor(Color.WHITE); t.setGravity(17); t.setPadding(18,12,18,12); t.setBackgroundColor(Color.rgb(202,55,140)); bubble=t;
  int type=Build.VERSION.SDK_INT>=26?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:WindowManager.LayoutParams.TYPE_PHONE;
  lp=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,PixelFormat.TRANSLUCENT); lp.gravity=Gravity.TOP|Gravity.END; lp.x=20;lp.y=180;
  t.setOnClickListener(v->{count++; t.setText("👾 "+count+"\nTap me +1"); getSharedPreferences("monster",0).edit().putInt("today",getSharedPreferences("monster",0).getInt("today",0)+1).apply(); if(count%5==0)t.setText("👾 "+count+"\nYou're on a roll!");});
  t.setOnTouchListener(new View.OnTouchListener(){int x,y; public boolean onTouch(View v,MotionEvent e){if(e.getAction()==0){x=(int)e.getRawX();y=(int)e.getRawY();return false;} if(e.getAction()==2){lp.x=Math.max(0,lp.x+(int)(e.getRawX()-x));lp.y=Math.max(0,lp.y+(int)(e.getRawY()-y));x=(int)e.getRawX();y=(int)e.getRawY();wm.updateViewLayout(bubble,lp);return true;}return false;}});
  wm.addView(bubble,lp);
 }
 public int onStartCommand(Intent i,int f,int id){return START_STICKY;}
 public void onDestroy(){if(bubble!=null)wm.removeView(bubble);super.onDestroy();}
}
