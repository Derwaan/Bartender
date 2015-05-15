package m.groupe.bartender;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Trong-Vu on 07-05-15.
 */

public class BartenderApp extends Application {

    private static BartenderApp context;

    public static BartenderApp getContext(){
        return context;
    }

    public void onCreate(){
        super.onCreate();
        context = (BartenderApp) getApplicationContext();
    }

    public static void notifyShort(int resId){
        notify(resId, Toast.LENGTH_SHORT);
    }

    public static void notifyLong(int resId){
        notify(resId, Toast.LENGTH_LONG);
    }

    private static void notify(int resId, int duration){
        Toast msg = Toast.makeText(getContext(), getContext().getString(resId), duration);
        msg.setGravity(Gravity.CENTER, 0, 0);
        msg.show();
    }
}
