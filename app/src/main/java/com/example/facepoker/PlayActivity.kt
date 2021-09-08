package com.example.facepoker

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        initPage()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
    }
    override fun onResume() {
        super.onResume()
        initPage()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        initPage()
        return super.onTouchEvent(event)
    }

    // 상태바 없애주는 옵션
    private fun initPage() {
        val decorView = window.decorView
        var uiOption = decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) uiOption = uiOption or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) uiOption = uiOption or View.SYSTEM_UI_FLAG_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) uiOption = uiOption or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOption
    }
}