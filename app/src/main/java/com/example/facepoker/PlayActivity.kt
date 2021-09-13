package com.example.facepoker

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.facepoker.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private var mBinding: ActivityPlayBinding? = null
    private val binding get() = mBinding!!

    //게임에 필요한 변수
    private var mybetcount = 0
    private var enemy_coin = 0
    private var my_coin = 0
    private var betcount = 0
    private var leastbetcount = 0
    private var myturn = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPage()
        initButton()

        initGame()
    }
    override fun onResume() {
        super.onResume()
        initPage()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        initPage()
        return super.onTouchEvent(event)
    }

    //게임 초기화
    private fun initGame(){
        mybetcount = 0
        enemy_coin = 30
        my_coin = 30
        betcount = 0
        leastbetcount = 0
        myturn = true
        binding.betcount.text = mybetcount.toString()
        binding.enemyCoin.text = enemy_coin.toString()
        binding.myCoin.text = my_coin.toString()
        binding.allBetCoin.text = betcount.toString()
    }

    // 상태바 없애주는 옵션
    private fun initPage() {
        val decorView = window.decorView
        var uiOption = decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) uiOption = uiOption or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) uiOption = uiOption or View.SYSTEM_UI_FLAG_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) uiOption = uiOption or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOption

        binding.betcount.text = mybetcount.toString()
    }

    //버튼 세팅
    private fun initButton() {
        binding.betbtn.setOnClickListener {
            binding.betFrame.visibility = View.INVISIBLE
            binding.countFrame.visibility = View.VISIBLE
        }

        binding.diebtn.setOnClickListener {
            /*Toast.makeText(this, "die", Toast.LENGTH_SHORT).show()
            binding.betFrame.visibility = View.INVISIBLE
            binding.countFrame.visibility = View.VISIBLE*/
        }

        binding.okbtn.setOnClickListener {
            binding.betFrame.visibility = View.VISIBLE
            binding.countFrame.visibility = View.INVISIBLE
        }

        binding.upBtn.setOnClickListener {
            mybetcount++
            binding.betcount.text = mybetcount.toString()
        }
        binding.donwBtn.setOnClickListener {
            mybetcount--
            binding.betcount.text = mybetcount.toString()
        }
    }
}