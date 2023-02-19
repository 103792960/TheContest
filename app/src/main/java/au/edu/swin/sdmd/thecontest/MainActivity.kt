package au.edu.swin.sdmd.thecontest

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        Log.i("TAG", "onStart")
    }

    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.i("TAG", "onCreate")

        val score = findViewById<Button>(R.id.btn_score);
        val steal = findViewById<Button>(R.id.btn_steal);
        val reset = findViewById<Button>(R.id.btn_reset);
        val result = findViewById<TextView>(R.id.result);

        val sharePref = getSharedPreferences("pref", MODE_PRIVATE);

        count = sharePref.getInt("num", 0)

        Log.i("TAG", "" + count);

        result.setText(Integer.toString(count))

        check(count, result)

        if (count > 14) {

            sharePref.edit().putInt("num", count).apply()
            count = 0
        }

        fun checkBounds() {
            score.setEnabled(count < 15);
            steal.setEnabled(count > 0);
        }

        var mediaPlayer = MediaPlayer.create(this, R.raw.success_1_6297);

        if (count == 15) {
            mediaPlayer.start()
        }

        score.setOnClickListener {
            count++
            result.text = count.toString()
            sharePref.edit().putInt("num", count).apply()

            check(count, result)
            checkBounds()
        }

        steal.setOnClickListener {
            count--
            result.text = count.toString()
            sharePref.edit().putInt("num", count).apply()

            check(count, result)
            checkBounds();
        }

        reset.setOnClickListener {
            count = 0

            result.text = count.toString()
            sharePref.edit().putInt("num", count).apply()
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