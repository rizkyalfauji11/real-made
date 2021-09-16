package id.code.alpha.realmade.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.ActivityMainBinding
import id.code.alpha.realmade.databinding.ActivitySplashScreenBinding
import id.code.alpha.realmade.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private var viewBinding: ActivitySplashScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
            },
            3000
        )
    }
}