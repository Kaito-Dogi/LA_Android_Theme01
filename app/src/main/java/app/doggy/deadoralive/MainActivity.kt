package app.doggy.deadoralive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //リセット用の数値を用意。
    companion object {
        const val RESET = -2
    }

    //画像を用意。
    val images: Array<Int> = arrayOf(
        R.drawable.baby1,
        R.drawable.baby2,
        R.drawable.boy1,
        R.drawable.boy2,
        R.drawable.boy3,
        R.drawable.boy4,
        R.drawable.man1,
        R.drawable.man2,
        R.drawable.game_clear1
    )

    //現状を管理する変数。
    var status = 0

    //生死を決める変数。
    var destiny = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //クリックリスナを設定。
        button.setOnClickListener {

            if (status == RESET) {
                //スタート画面にリセット。
                imageView.setImageResource(R.drawable.born)
                textView.setText(R.string.app_name)
                button.setText(R.string.button)
                status = 0
                destiny = RESET

            } else {
                //生死を決める。
                destiny = Random.nextInt(3)
                //destiny = 1
            }

            //生死によって処理を変える。
            when(destiny) {
                0 -> {
                    imageView.setImageResource(R.drawable.game_over)
                    textView.setText(R.string.game_over)
                    button.setText(R.string.button_game_over)
                    status = RESET
                }

                in 1..2 -> {
                    //statusの値によって処理を変える。
                    when(status) {
                        in 0..images.size-2 -> {
                            imageView.setImageResource(images[status])
                            textView.setText(R.string.app_name)
                            button.setText(R.string.button)
                            status += 1
                        }

                        images.size-1 -> {
                            destiny = Random.nextInt(10)
                            when(destiny) {
                                in 0..9 -> imageView.setImageResource(images[status])
                                10 -> imageView.setImageResource(R.drawable.game_clear0)
                            }
                            textView.setText(R.string.game_clear)
                            button.setText(R.string.button_game_over)
                            status = RESET
                        }
                    }
                }
            }
        }
    }
}