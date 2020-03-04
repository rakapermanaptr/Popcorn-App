package com.rakapermanaputra.popcorn

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import com.rakapermanaputra.popcorn.db.SharedPreference
import com.rakapermanaputra.popcorn.feature.discover.DiscoverFragment
import com.rakapermanaputra.popcorn.feature.favorite.FavoriteFragment
import com.rakapermanaputra.popcorn.feature.home.HomeFragment
import com.rakapermanaputra.popcorn.feature.login.LoginActivity
import com.rakapermanaputra.popcorn.feature.movies.MoviesFragment
import com.rakapermanaputra.popcorn.feature.popular_people.PopularPeopleFragment
import com.rakapermanaputra.popcorn.feature.search.SearchActivity
import com.rakapermanaputra.popcorn.feature.tvshows.TvShowsFragment
import com.rakapermanaputra.popcorn.feature.watchlist.WatchlistFragment
import com.rakapermanaputra.popcorn.model.Account
import com.rakapermanaputra.popcorn.model.RequestToken
import com.rakapermanaputra.popcorn.model.repository.LoginRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HomeContract.View {

    private lateinit var presenter: HomePresenter
    private lateinit var sharedPreference: SharedPreference
    private lateinit var requestToken: RequestToken
    private var sessionId: String? = null

    private lateinit var navigationView: NavigationView
    private lateinit var headerView: View
    private lateinit var tvUsername: TextView
    private lateinit var menu: Menu
    private lateinit var menuLogin: MenuItem
    private lateinit var menuLogout: MenuItem
    private lateinit var menuFavorite: MenuItem
    private lateinit var menuWatchlist: MenuItem

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
            val fragment: androidx.fragment.app.Fragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit()
        }

        //SharedPref Token
        sharedPreference = SharedPreference(this)
        val token = sharedPreference.getValueString("TOKEN")
        requestToken = RequestToken(token)
        Log.d("Data", "token on sharedPref: $token")

        //SharedPref Session
        sessionId = sharedPreference.getValueString("SESSION_ID")
        Log.d("Data", "sessionId on sharedPref: $sessionId")

        //Request
        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = LoginRepoImpl(service)
        presenter = HomePresenter(this, request)
        if (sessionId != null) presenter.getAccountUser(sessionId!!)

        //Display username on NavHeader
        navigationView = findViewById(R.id.nav_view)
        headerView = navigationView.getHeaderView(0)
        tvUsername = headerView.findViewById(R.id.tvUsername)

        //Setting Menu
        menu = navigationView.menu
        if (sessionId != null) {
            menuLogin = menu.findItem(R.id.nav_login).setVisible(false)

            menuFavorite = menu.findItem(R.id.nav_favorite).setVisible(true)

            menuWatchlist = menu.findItem(R.id.nav_watchlist).setVisible(true)

            menuLogout = menu.findItem(R.id.nav_logout).setVisible(true)
        } else{
            menuLogin = menu.findItem(R.id.nav_login).setVisible(true)

            menuFavorite = menu.findItem(R.id.nav_favorite).setVisible(false)

            menuWatchlist = menu.findItem(R.id.nav_watchlist).setVisible(false)

            menuLogout = menu.findItem(R.id.nav_logout).setVisible(false)
        }

    }

    override fun showAccountUser(userAccount: Account) {
        val userId = userAccount.id
        sharedPreference.saveInt("ACCOUNT_ID", userId)
        toast("Hello, " + userAccount.username)
        Log.i("Data", "User id: $userId")

        //show username on NavHeader
        tvUsername.text = userAccount.username
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            finishAffinity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.queryHint = "Search..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity<SearchActivity>("query" to query)

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
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: androidx.fragment.app.Fragment? = null
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                toast("Home")
                fragment = HomeFragment()
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
            R.id.nav_favorite ->{
                fragment = FavoriteFragment()
                toast("Favorite")
            }
            R.id.nav_watchlist -> {
                fragment = WatchlistFragment()
                toast("Watchlist")
            }
            R.id.nav_login -> {
                startActivity<LoginActivity>()
            }
            R.id.nav_logout -> {
                sharedPreference.clearSharedPreference()
                tvUsername.text = ""

                menuLogin = menu.findItem(R.id.nav_login).setVisible(true)
                menuFavorite = menu.findItem(R.id.nav_watchlist).setVisible(false)
                menuFavorite = menu.findItem(R.id.nav_favorite).setVisible(false)
                menuLogout = menu.findItem(R.id.nav_logout).setVisible(false)
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
