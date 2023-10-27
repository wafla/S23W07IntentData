package kr.ac.kumoh.ce.s20211391.s23w07intentdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.s20211391.s23w07intentdata.ImageActivity.Companion.IMAGE_RESULT
import kr.ac.kumoh.ce.s20211391.s23w07intentdata.ImageActivity.Companion.NONE
import kr.ac.kumoh.ce.s20211391.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val KEY_NAME = "location"
        const val IMAGE_MOUNTAIN = "mountain" // const val 실행시에 초기화 x
        const val IMAGE_SEA = "sea"
    } // static

    private lateinit var main : ActivityMainBinding
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        val result = it.data?.getIntExtra(IMAGE_RESULT, NONE) // 뒤에껀 디폴트 값

        val str = when(result){
            ImageActivity.LIKE ->"좋아요"
            ImageActivity.DISLIKE->"싫어요"
            else -> "error"
        }
        when(it.data?.getStringExtra(ImageActivity.IMAGE_NAME)){
            IMAGE_MOUNTAIN -> main.btnMountain.text="산 ($str)"
            IMAGE_SEA -> main.btnSea.text = "바다 ($str)"
        }
        Log.i("Result!!!", str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)
        //setContentView(R.layout.activity_main)
        main.btnMountain.setOnClickListener(this)
        main.btnSea.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val value = when(v?.id){
            main.btnMountain.id->{
                Toast.makeText(this, "산 이미지", Toast.LENGTH_SHORT).show()
                IMAGE_MOUNTAIN
            }
            main.btnSea.id->{
                Toast.makeText(this, "바다 이미지", Toast.LENGTH_SHORT).show()
                IMAGE_SEA
            }
            else -> return
        }
        val intent = Intent(this, /*ImageActivity*/AnotherActivity::class.java)
        intent.putExtra(KEY_NAME, value)
        //startActivity(intent)
        startForResult.launch(intent)
    }
}