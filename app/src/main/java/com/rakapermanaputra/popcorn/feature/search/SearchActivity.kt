package com.rakapermanaputra.popcorn.feature.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.rakapermanaputra.popcorn.R
import com.rakapermanaputra.popcorn.adapter.SearchAdapter
import com.rakapermanaputra.popcorn.model.Search
import com.rakapermanaputra.popcorn.model.repository.SearchRepoImpl
import com.rakapermanaputra.popcorn.network.ApiRest
import com.rakapermanaputra.popcorn.network.ApiService
import com.rakapermanaputra.popcorn.utils.invisible
import com.rakapermanaputra.popcorn.utils.visible
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private lateinit var presenter: SearchPresenter
    private var results: MutableList<Search> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val query = intent.getStringExtra("query")

        val service = ApiService.getClient().create(ApiRest::class.java)
        val request = SearchRepoImpl(service)
        presenter = SearchPresenter(this, request)
        presenter.getResultSearch(query)
    }

    override fun showLoading() {
        recyclerView.invisible()
        loadingAnim.visible()
    }

    override fun hideLoading() {
        recyclerView.visible()
        loadingAnim.invisible()
    }

    override fun showResultSearch(listResult: List<Search>) {
        results.clear()
        results.addAll(listResult)
        val linearLayoutManager =
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = SearchAdapter(this, results)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.queryHint = "Search..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getResultSearch(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

}
