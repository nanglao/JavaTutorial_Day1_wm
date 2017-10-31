package com.snsh.dev.Willmel;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.hawk.Hawk;

import me.myatminsoe.mdetect.MDetect;
import timber.log.Timber;

/**
 * Created by android_development on 14/10/2017.
 */

public class App extends Application {

    private static Context appContext;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();

        Hawk.init(this).build();
        MDetect.INSTANCE.init(this);
        Utils.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            /*Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();*/
        }
    }
}
