package com.furkankurt.workmanagerjava;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {
    Context context;

    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data=getInputData();
        int myNumber=data.getInt("intKey",0);
        refreshDatabase(myNumber);
        return Result.success();
    }

    private void refreshDatabase(int myNumber)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("com.furkankurt.workmanagerjava",Context.MODE_PRIVATE);
        int mySavedNumber=sharedPreferences.getInt("myNumber",0);
        mySavedNumber=mySavedNumber+myNumber;
        System.out.println(mySavedNumber);
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply();

    }
}
