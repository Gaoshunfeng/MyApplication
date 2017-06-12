package com.example.vivi.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnValueChangeListener,Formatter,OnScrollListener{


    private NumberPicker numberpicker;
    private Handler myhandle;
    private int num=10;
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
         card= (CardView) findViewById(R.id.Cardview);
        SeekBar s1= (SeekBar) findViewById(R.id.seek1);
        SeekBar s2= (SeekBar) findViewById(R.id.seek2);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    card.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        card.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void init() {
        numberpicker=(NumberPicker)findViewById(R.id.numberPicker);
        numberpicker.setFormatter(this);
        numberpicker.setOnValueChangedListener(this);
        numberpicker.setOnScrollListener(this);
        numberpicker.setMaxValue(5);
        numberpicker.setMinValue(0);
        numberpicker.setValue(num);
        myhandle=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1)
                {
                    num++;
                    if(num>5)
                    {
                        num=0;
                    }
                    numberpicker.setValue(num);
                }
            }
        };

        new Thread(){

            @Override
            public void run() {
                super.run();
               // Toast.makeText(MainActivity.this, "1111", Toast.LENGTH_SHORT).show();
                while(true) {
                    System.out.println("1111");
                    Message msg = new Message();
                    msg.what = 1;
                    myhandle.sendMessage(msg);
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }
    public void RecyclerView(View v)
    {
        Intent intent=new Intent(MainActivity.this,Recycler.class);
        startActivity(intent);
    }

    @Override
    public String format(int value) {
        String tmpStr=String.valueOf(value);
        if(value<10)
        {
            tmpStr="0"+tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {

        switch (scrollState)
        {
            case OnScrollListener.SCROLL_STATE_FLING:
                Toast.makeText(this, "scroll state fling", Toast.LENGTH_LONG)
                        .show();
                break;
            case OnScrollListener.SCROLL_STATE_IDLE:
                Toast.makeText(this, "scroll state idle", Toast.LENGTH_LONG).show();
                break;
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Toast.makeText(this, "scroll state touch scroll", Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Toast.makeText(
                this,
                "number changed --> oldValue: " + oldVal + " ; newValue: "
                        + newVal, Toast.LENGTH_SHORT).show();
    }
}
