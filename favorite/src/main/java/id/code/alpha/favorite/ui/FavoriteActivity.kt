package id.code.alpha.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.code.alpha.favorite.util.replaceFragment
import id.code.alpha.profile.R
import id.code.alpha.profile.databinding.ActivityFavoriteBinding
import id.code.alpha.realmade.main.favorite.FavoriteFragment

class FavoriteActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        replaceFragment(R.id.container, FavoriteFragment())
    }

    override fun onBackPressed() {
        finish()
    }
}