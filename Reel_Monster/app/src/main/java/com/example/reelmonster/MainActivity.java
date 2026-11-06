package com.example.reelmonster;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.provider.Settings;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import java.text.*;
import java.util.*;

public class MainActivity extends Activity {
    TextView count, stats; EditText manual; Button start, stop, reset; SharedPreferences prefs;
    @Override public void onCreate(Bundle b){super.onCreate(b); prefs=getSharedPreferences("monster",0);
        LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setPadding(24,30,24,20); l.setBackgroundColor(Color.rgb(248,244,250));
        TextView title=new TextView(this); title.setText("👾 Reel Monster"); title.setTextSize(28); title.setTextColor(Color.rgb(150,40,110)); l.addView(title);
        TextView desc=new TextView(this); desc.setText("Your tiny scrolling buddy. Count reels, track time, and take a break."); desc.setTextSize(16); l.addView(desc);
        count=new TextView(this); count.setTextSize(48); count.setTextColor(Color.rgb(40,50,90)); count.setGravity(17); l.addView(count);
        manual=new EditText(this); manual.setHint("Manual count: tap monster after each reel"); manual.setInputType(2); l.addView(manual);
        Button plus=new Button(this); plus.setText("Count +1"); plus.setOnClickListener(v->{addCount(1);}); l.addView(plus);
        Button level=new Button(this); level.setText("Level 2: Automatic (Accessibility setup)"); level.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("Automatic counting").setMessage("Android restricts reading other apps. This starter project uses manual counting. To implement automatic detection, add an AccessibilityService and enable it in Settings. It may not reliably detect every reel and requires user permission.").setPositiveButton("OK",null).show()); l.addView(level);
        start=new Button(this); start.setText("Start floating monster"); start.setOnClickListener(v->startOverlay()); l.addView(start);
        stop=new Button(this); stop.setText("Stop floating monster"); stop.setOnClickListener(v->stopService(new Intent(this,MonsterService.class))); l.addView(stop);
        reset=new Button(this); reset.setText("Reset today's count"); reset.setOnClickListener(v->{prefs.edit().putInt("today",0).apply(); refresh();}); l.addView(reset);
        stats=new TextView(this); stats.setTextSize(16); l.addView(stats);
        Button permission=new Button(this); permission.setText("Allow display over other apps"); permission.setOnClickListener(v->{if(Build.VERSION.SDK_INT>=23&&!Settings.canDrawOverlays(this)) startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())));}); l.addView(permission);
        setContentView(l); refresh();
    }
    void addCount(int n){int c=prefs.getInt("today",0)+n; prefs.edit().putInt("today",c).putLong("last",System.currentTimeMillis()).apply(); refresh();}
    void refresh(){int c=prefs.getInt("today",0); count.setText(""+c+" reels"); long mins=prefs.getLong("minutes",0); stats.setText("Today: "+c+" reels\nEstimated time lost: "+(c*30/60)+" min (30 sec/reel estimate)\nLast 7 days: "+prefs.getString("week","No history yet")); }
    void startOverlay(){if(Build.VERSION.SDK_INT>=23&&!Settings.canDrawOverlays(this)){Toast.makeText(this,"Allow overlay permission first",0).show();return;} startService(new Intent(this,MonsterService.class));}
}
