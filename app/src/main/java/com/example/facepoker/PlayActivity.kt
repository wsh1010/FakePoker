package com.example.facepoker

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.example.facepoker.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {
    private var mBinding: ActivityPlayBinding? = null
    private val binding get() = mBinding!!

    //게임에 필요한 변수
    private var mybetcount = 0
    private var holdingenemycoin = 0
    private var holdingmycoin = 0
    private var needbetCoin = 0
    private var betcount = 0
    private var leastbetcount = 0
    private var myturn = false
    private var enemyturn = false

    private var useNetwork = false

    private var handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when{
                myturn->{
                    myturn = false
                }
                enemyturn->{
                    enemyturn = false
                }
            }
        }
    }
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
        holdingenemycoin = 30
        holdingmycoin = 30
        betcount = 0
        leastbetcount = 0
        myturn = true
        binding.betcount.text = mybetcount.toString()
        binding.enemyCoin.text = holdingenemycoin.toString()
        binding.myCoin.text = holdingmycoin.toString()
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
            mybetcount = needbetCoin // 적이 bet 한 금액보다 크거나 같아야 함!
        }

        binding.diebtn.setOnClickListener {
            /*Toast.makeText(this, "die", Toast.LENGTH_SHORT).show()
            binding.betFrame.visibility = View.INVISIBLE
            binding.countFrame.visibility = View.VISIBLE*/
            endMyTurn( 0 )
        }

        binding.okbtn.setOnClickListener {
            binding.betFrame.visibility = View.VISIBLE
            binding.countFrame.visibility = View.INVISIBLE
            
            if (mybetcount == 0) {
                //실행 취소
            } else {
                //턴 종료
                endMyTurn( mybetcount )
            }
            //상태 초기화
            mybetcount = 0
            binding.betcount.text = mybetcount.toString()
        }

        binding.upBtn.setOnClickListener {
            mybetcount++
            binding.betcount.text = mybetcount.toString()
        }
        binding.donwBtn.setOnClickListener {
            mybetcount--
            if ( mybetcount < needbetCoin ) mybetcount = needbetCoin
            binding.betcount.text = mybetcount.toString()
        }
    }

    private fun initPlay() {
        binding.myCard.setImageResource(getResourceCard())
        binding.enemyCard.setImageResource(getResourceCard())
    }
    private fun startGame(enemyCard: Int) {
        binding.enemyCard.setImageResource(getResourceCard(enemyCard))
    }

    private fun startMyTurn(enemyBetCoin: Int = 0, needBet: Int = 0) {
        myturn = true
        holdingenemycoin -= enemyBetCoin
        binding.enemyCoin.text = holdingenemycoin.toString()
        needbetCoin = needBet
    }
    private fun endMyTurn(betCoin: Int) {
        myturn = false
    }
    private fun setCoin(myCoin: Int, enemyCoin: Int, betCoin: Int) {
        holdingmycoin = myCoin
        holdingenemycoin = enemyCoin
        betcount = betCoin
        binding.enemyCoin.text = holdingenemycoin.toString()
    }
    private fun endGame(myCard: Int, win: Boolean, getCoin: Int) {
        binding.myCard.setImageResource(getResourceCard(myCard))
        holdingmycoin += getCoin
        if(!win) {
            if (myCard == 10)
                holdingmycoin -= 10
        }
        binding.myCoin.text = holdingmycoin.toString()
    }

    private fun getResourceCard(cardNumber: Int = -1): Int{
        return when(cardNumber){
            1 -> R.drawable.card1
            2 -> R.drawable.card2
            3 -> R.drawable.card3
            4 -> R.drawable.card4
            5 -> R.drawable.card5
            6 -> R.drawable.card6
            7 -> R.drawable.card7
            8 -> R.drawable.card8
            9 -> R.drawable.card9
            10 -> R.drawable.card10
            else -> R.drawable.cardask
        }
    }

}