package com.example.dead_mfcs_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        WorkRequest uploadWorkRequest =
                new PeriodicWorkRequest.Builder(DeadRussiansWorker.class, 4, TimeUnit.HOURS)
                        .setInputData(new Data.Builder().putIntArray("ids", appWidgetIds).build())
                        .build();
        WorkManager
                .getInstance(context)
                .enqueue(uploadWorkRequest);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}