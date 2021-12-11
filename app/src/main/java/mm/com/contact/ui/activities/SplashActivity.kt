package mm.com.contact.ui.activities


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import mm.com.contact.R

class SplashActivity : BaseActivity() {
    private val SPLASH_TIME_OUT: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            startActivity(MainActivity.newIntent(this))
        }, SPLASH_TIME_OUT)


    }
}