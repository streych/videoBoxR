package com.example.videoboxr

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
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
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_up, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery(query , false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about -> {
                aboutDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun aboutDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("О программе")
        builder.setMessage("Первая программа с применением MVVM и Kotlin")
        builder.show()
    }

}