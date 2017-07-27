package us.mifeng.videoview;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by shido on 2017/7/3.
 */

public class MyToast {
    private static Toast toast;
    public static  void show(Activity activity,String str){
        if (toast==null){
            toast = Toast.makeText(activity,str,Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }

}
