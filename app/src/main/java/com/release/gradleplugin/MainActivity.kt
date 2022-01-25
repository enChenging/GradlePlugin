package com.release.gradleplugin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager

import android.content.pm.ApplicationInfo
import android.util.Log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        val channel = appInfo.metaData.getString("CHANNEL")
        Log.i("cyc", "channel: $channel")
    }
}