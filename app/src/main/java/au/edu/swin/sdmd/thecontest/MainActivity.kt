package au.edu.swin.sdmd.thecontest

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("TAG", "onCreate")

        var media = MediaPlayer.create(this, R.raw.success_1_6297)

        val score = findViewById<Button>(R.id.btn_score);
        val steal = findViewById<Button>(R.id.btn_steal);
        val reset = findViewById<Button>(R.id.btn_reset);
        val result = findViewById<TextView>(R.id.result);

        if(savedInstanceState != null) {
            count = savedInstanceState.getInt("MyInt")
            result.text = count.toString()
            check(count, result)
        }

        fun checkBounds() {
            score.setEnabled(count < 15)
            steal.setEnabled(count > 0)
        }

        score.setOnClickListener {
            count++
            result.text = count.toString()

            check(count, result)
            checkBounds()

            if (count == 15) {
                media.start()
            }
        }

        steal.setOnClickListener {
            count--
            result.text = count.toString()

            check(count, result)
            checkBounds()
        }

        reset.setOnClickListener {
            count = 0

            result.text = count.toString()
            check(count, result)
        }
    }

    fun check(increment_number: Int, result: TextView) {


        if (increment_number >= 10) {
            result.setTextColor(Color.GREEN)
        } else if (increment_number >= 5) {
            result.setTextColor(Color.BLUE)
        }
        else {
            result.setTextColor(Color.BLACK)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("MyInt", count)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("MyInt")
    }
}