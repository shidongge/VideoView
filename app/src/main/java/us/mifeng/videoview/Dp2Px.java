package us.mifeng.videoview;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by shido on 2017/7/3.
 */

public class Dp2Px {
    public static int Dp2Ps(Activity activity, int dp){
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display dis = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        dis.getMetrics(dm);
        float density = dm.density;
        float pxf = density*dp+0.5f;
        int px  = (int) pxf;
        return px;
    }
}
