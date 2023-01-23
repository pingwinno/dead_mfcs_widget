package com.example.dead_mfcs_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.util.Arrays;

public class DeadRussiansWorker extends Worker {

    private final static String TEMPLATE = "Сдохло свинособак: усього - %s \n сьогодні - %s";
    private Context context;

    public DeadRussiansWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;

    }

    @NonNull
    @Override
    public Result doWork() {
        int[] viewIds = getInputData().getIntArray("ids");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        try {
            DeadRussians deadRussians = DeadRussianService.getDeadRussians().get("Личный состав");
            views.setTextViewText(R.id.appwidget_text, String.format(TEMPLATE, deadRussians.getQuantity(), deadRussians.getIncome()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.stream(viewIds).forEach(appWidgetId -> AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views));
        return Result.success();
    }
}
