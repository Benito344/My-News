package com.behague.benjamin.mynews.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.behague.benjamin.mynews.controllers.activities.MainActivity;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.R;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Benjamin BEHAGUE on 24/01/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    SharedPreferences notifParams;

    private final String KEY_TERMS = "KEY_TERMS";

    private Context context;

    //It for receive alarm
    @Override
    public void onReceive(Context context, Intent intent){
        final String PARAMS = "PARAMS";
        notifParams = context.getSharedPreferences(PARAMS, Context.MODE_PRIVATE);

        this.context = context;
        this.executeHttpRequest();
    }

    //It fir execute Http request
    public void executeHttpRequest(){
        final String KEY_SECTION_CHECKED = "KEY_SECTION_CHECKED";
        String inputTerms = notifParams.getString(KEY_TERMS,"");
        String inputSections = notifParams.getString(KEY_SECTION_CHECKED,"");

        NYTStreams.streamSearchArticlesWhitoutDate(inputTerms, inputSections)
                .subscribeWith(new DisposableObserver<SearchArticlesMain>(){

                    @Override
                    public void onNext(SearchArticlesMain searchArticlesMain){
                        if(searchArticlesMain.getResponse().getDocs().isEmpty()){
                            Log.e("TAG", "Empty");
                        }
                        else{
                            callNotification();
                        }

                    }

                    @Override
                    public void onError(Throwable e){
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete(){
                        Log.e("TAG","Completed");
                    }
                });
    }

    //It for display notification
    public void callNotification(){
        String NOTIFICATION_CHANNEL_ID = "1993";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("You have news articles to read !")
                .setContentText("your get articles with the terms : " + notifParams.getString(KEY_TERMS,""))
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher_nyt)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher_nyt))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        if(notificationManager != null){
            notificationManager.notify(0, builder.build());
        }
    }
}
