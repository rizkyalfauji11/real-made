package id.code.alpha.realmade.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.ActivityMainBinding
import id.code.alpha.realmade.main.home.HomeFragment
import id.code.alpha.realmade.profile.ProfileActivity
import id.code.alpha.realmade.util.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        replaceFragment(R.id.container, HomeFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFavorite -> {
                val uri = Uri.parse("alpha://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }

            R.id.menuProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}