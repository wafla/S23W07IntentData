package kr.ac.kumoh.ce.s20211391.s23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kr.ac.kumoh.ce.s20211391.s23w07intentdata.databinding.ActivityImageBinding
import kr.ac.kumoh.ce.s20211391.s23w07intentdata.databinding.ActivityMainBinding

class ImageActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"
        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }
    private lateinit var main: ActivityImageBinding
    private lateinit var imageName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityImageBinding.inflate(layoutInflater)
        setContentView(main.root)

        // Log.i("Image!!!", intent.getStringExtra("location") ?: "없음")
        // 먼저 값이 잘 넘어 오는지 Log로 확인하고 코드 짜기, ?: 엘비스 연산자
        imageName = intent.getStringExtra(MainActivity.KEY_NAME) ?: "none"
        val res = when(imageName){
            MainActivity.IMAGE_MOUNTAIN -> R.drawable.mountain
            MainActivity.IMAGE_SEA -> R.drawable.sea
            else -> R.drawable.ic_launcher_foreground
        }
        main.image.setImageResource(res)
        main.btnLike.setOnClickListener(this)
        main.btnDislike.setOnClickListener(this)
    }

    override fun onClick(v: View?) { // 인터페이스인데 임플리먼트가 하나 밖에 없어, 펑셔널인터페이스, singleabstractmethod
        val intent = Intent()
        val value = when(v?.id){
            main.btnLike.id -> LIKE
            main.btnDislike.id -> DISLIKE
            else -> NONE
        }
        intent.putExtra(IMAGE_NAME, imageName) // 오버로드
        intent.putExtra(IMAGE_RESULT, value)
        setResult(RESULT_OK, intent)
        finish()
    }
}