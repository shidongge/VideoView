package us.mifeng.videoview;

import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView vv;
    private Button stop,start,puase;
    private int duration;
    private File file;
    private Timer timer;
    private SeekBar seekBar;
    private int video_progress = 0;
    private Runnable runnable;
    private PlayStatus playing_status;
    private FrameLayout left,right;
    private int oldLeftY = 0;
    private int oldRightY = 0;
    private int dp = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVideoListener();
        file = new File(Environment.getExternalStorageDirectory() + "");
        if (file.exists()){
            vv.setVideoPath(file.getAbsolutePath());
            duration = vv.getDuration();
            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }else {
            Toast.makeText(MainActivity.this,"该视频不存在",Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        vv = (VideoView) findViewById(R.id.vv);
        stop = (Button) findViewById(R.id.stop);
        start = (Button) findViewById(R.id.start);
        puase = (Button) findViewById(R.id.pause);
        left = (FrameLayout) findViewById(R.id.left);
        right= (FrameLayout) findViewById(R.id.right);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
            initEvent();

    }

    private void initVideoListener() {
        runnable = new Runnable() {
            @Override
            public void run() {
                video_progress = vv.getCurrentPosition();
                if (duration==0){
                    seekBar.setProgress(video_progress);
                }
            }
        };
    }

    private void initEvent() {
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        puase.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    vv.seekTo(progress);
                    startVideo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        try {
            BrightNessUtils.setLightManual(this);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        initBrightManager();
        initVolumeManager();
    }

    private void initVolumeManager() {
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (oldRightY==0){
                        oldRightY = (int) event.getRawY();
                    }
                }
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    float newRightY = event.getRawY();
                    float degree = newRightY - oldRightY;
                    float abs = Math.abs(degree);
                    int brightNess = BrightNessUtils.getBrightNess(MainActivity.this);
                    if (newRightY-oldRightY>0){
                        if (abs>=4*dp){
                            brightNess-=240;
                        }else if (abs>=3*dp){
                            brightNess-=180;
                        }else if (abs>=2*dp){
                            brightNess-=120;
                        }else if (abs>=1*dp){
                            brightNess-=60;
                        }
                        if (brightNess<0){
                            brightNess=0;
                        }
                        BrightNessUtils.setBrightNess(MainActivity.this,brightNess);
                    }else {
                        if (abs>=4*dp){
                            //四个
                            brightNess+=240;
                            MyToast.show(MainActivity.this,"sige");
                        }else if (abs>=3*dp){
                            brightNess+=180;
                            MyToast.show(MainActivity.this,"sange");

                        }else if (abs>=2*dp){
                            brightNess+=120;
                            MyToast.show(MainActivity.this,"liangge");
                        }
                        else if (abs>=1*dp){
                            MyToast.show(MainActivity.this,"yige");
                            brightNess=brightNess+60;
                        }
                        if (brightNess>=255){
                            brightNess=255;
                        }
                        BrightNessUtils.setBrightNess(MainActivity.this,brightNess);
                    }
                }
                return true;
            }
        });
    }

    private void initBrightManager() {
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    if (oldLeftY==0){
                        oldLeftY = (int) event.getRawY();
                    }
                }
                if (event.getAction()==MotionEvent.ACTION_MOVE){
                    float newLeft = event.getRawY();
                    float degree = newLeft - oldLeftY;
                    float abs = Math.abs(degree);
                    int brightNess = BrightNessUtils.getBrightNess(MainActivity.this);
                    if (newLeft-oldLeftY>0){
                        if (abs>=4*dp){
                            brightNess-=240;
                        }else if (abs>=3*dp){
                            brightNess-=180;
                        }else if (abs>=2*dp){
                            brightNess-=120;
                        }else if (abs>=1*dp){
                            brightNess-=60;
                        }
                        if (brightNess<0){
                            brightNess=0;
                        }
                        BrightNessUtils.setBrightNess(MainActivity.this,brightNess);
                    }else {
                        if (abs>=4*dp){
                            //四个
                            brightNess+=240;
                            MyToast.show(MainActivity.this,"sige");
                        }else if (abs>=3*dp){
                            brightNess+=180;
                            MyToast.show(MainActivity.this,"sange");

                        }else if (abs>=2*dp){
                            brightNess+=120;
                            MyToast.show(MainActivity.this,"liangge");
                        }
                        else if (abs>=1*dp){
                            MyToast.show(MainActivity.this,"yige");
                            brightNess=brightNess+60;
                        }
                        if (brightNess>=255){
                            brightNess=255;
                        }
                        BrightNessUtils.setBrightNess(MainActivity.this,brightNess);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                duration=vv.getDuration();
                seekBar.setMax(duration);
                Toast.makeText(this, "duration="+duration, Toast.LENGTH_SHORT).show();
                startVideo(0);
                seekBar.setVisibility(View.VISIBLE);
                break;
            case R.id.stop:
                if ((vv!=null)&&file.exists()&&vv.canPause()){
                    if(timer!=null){
                        timer.purge();
                        timer=null;
                    }
                    seekBar.setProgress(0);
                    vv.pause();
                    playing_status=PlayStatus.STOP;
                }
                break ;
            case R.id.pause:
                if ((vv!=null)&&(file.exists())&&vv.canPause()){
                    if (timer!=null){
                        timer.purge();
                        timer=null;
                    }
                    vv.pause();
                        playing_status =PlayStatus.PAUSE;
                }
                break;
        }
    }

    private void startVideo(int position) {
        if ((vv!=null)&&(file.exists())&&(PlayStatus.START!=playing_status)){
            if (playing_status==PlayStatus.STOP){
                vv.seekTo(0);
            }
            if (position==0){
                vv.start();
            }else {
                vv.seekTo(position);
                vv.start();
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    video_progress = vv.getCurrentPosition();
                    if (duration!=0){
                        seekBar.setProgress(video_progress);
                    }
                }
            },0,100);
            playing_status = PlayStatus.START;
        }
    }
    public enum PlayStatus{
        STOP,PAUSE,START;
    }

}
