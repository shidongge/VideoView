package us.mifeng.videoview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.provider.Settings;

/**
 * Created by shido on 2017/7/3.
 */

public class BrightNessUtils {
    public static void setLightManual(Activity activity) throws Settings.SettingNotFoundException {
        ContentResolver contentResolver = activity.getContentResolver();
        int anInt = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
        if (Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC==anInt){
            Settings.System.putInt(contentResolver,Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
    }
    public static int getBrightNess(Activity activity){
        ContentResolver cr = activity.getContentResolver();
        int def = 125;
        int anInt = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS, def);
        return anInt;

    }
    public static void setBrightNess(Activity activity,int valus){
        ContentResolver cr = activity.getContentResolver();
        Settings.System.putInt(cr,Settings.System.SCREEN_BRIGHTNESS,valus);
    }

}
