package com.rakapermanaputra.popcorn

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.rakapermanaputra.popcorn.feature.discover.DiscoverFragment
import com.rakapermanaputra.popcorn.feature.login.LoginActivity
//import com.rakapermanaputra.popcorn.feature.home.HomeFragment
import com.rakapermanaputra.popcorn.feature.movies.MoviesFragment
import com.rakapermanaputra.popcorn.feature.popular_people.PopularPeopleFragment
import com.rakapermanaputra.popcorn.feature.tvshows.TvShowsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            var fragment: Fragment = MoviesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.queryHint = "Search Movie"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                toast("query : " + query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_search -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                toast("Home")
//                fragment = HomeFragment()
            }
            R.id.nav_movies -> {
                // Handle the camera action
                toast("Movie")
                fragment = MoviesFragment()
            }
            R.id.nav_tv_shows -> {
                toast("Tv Shows")
                fragment = TvShowsFragment()
            }
            R.id.nav_discover -> {
                toast("Discover")
                fragment = DiscoverFragment()

            }
            R.id.nav_popular_people -> {
                toast("Popular People")
                fragment = PopularPeopleFragment()

            }
            R.id.nav_reminder -> {
                toast("Reminder")

            }
            R.id.nav_settings -> {
                toast("Settings")
                startActivity<LoginActivity>()
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
