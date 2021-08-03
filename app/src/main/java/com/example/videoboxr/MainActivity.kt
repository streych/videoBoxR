package com.example.videoboxr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoboxr.databinding.MainActivityBinding
import com.example.videoboxr.ui.main.favorite.FavoriteFragment
import com.example.videoboxr.ui.main.home.MainFragment
import com.example.videoboxr.ui.main.reting.RetingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
        menuBottom()

    }

    private fun menuBottom(){
        binding.bottomMenuNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, MainFragment.newInstance())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.favoritesFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, FavoriteFragment())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.retingFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, RetingFragment())
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }


}