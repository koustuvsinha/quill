package me.vickychijwani.spectre;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.squareup.otto.DeadEvent;
import com.squareup.otto.Subscribe;

import me.vickychijwani.spectre.event.ApiErrorEvent;
import me.vickychijwani.spectre.event.BusProvider;
import me.vickychijwani.spectre.network.NetworkService;

public class SpectreApplication extends Application {

    public static final String TAG = "SpectreApplication";
    private static SpectreApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Crashlytics.start(this);
        Crashlytics.log(Log.DEBUG, TAG, "APP LAUNCHED");
        BusProvider.getBus().register(this);
        sInstance = this;

        NetworkService networkService = new NetworkService();
        networkService.start(this);
    }

    public static SpectreApplication getInstance() {
        return sInstance;
    }

    @Subscribe
    public void onApiErrorEvent(ApiErrorEvent event) {
        Log.e(TAG, Log.getStackTraceString(event.error));
    }

    @Subscribe
    public void onDeadEvent(DeadEvent event) {
        Log.e(TAG, "Dead event ignored: " + event.source.getClass().getName());
    }

}
