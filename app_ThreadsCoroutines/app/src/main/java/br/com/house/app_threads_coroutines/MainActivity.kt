package br.com.house.app_threads_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        buttonThread.setOnClickListener {
            Thread {
                runOnUiThread {
                    Thread.sleep(2000)
                    textView.text = "Click Thread"
                }
            }.start()
        }

        buttonHandler.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                textView.text = "Click Handler"
            }, 4000)
        }

        buttonCoroutines.setOnClickListener {
            scope.launch {
                delay(3000)
                textView.text = "Click Coroutines"
            }
        }
    }
}