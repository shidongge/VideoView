package us.mifeng.videoview;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

/**
 * Created by shido on 2017/7/3.
 */

public class SoundUtils {
    public static int getMaxVolume(Activity activity){
        AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return max;
    }
    public static int getCurrentVolume(Activity activity){
        AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        int stream = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return  stream;
    }
    public static void setVolume(Activity activity,int volume){
        AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(am.STREAM_MUSIC,volume,0);

    }

}
