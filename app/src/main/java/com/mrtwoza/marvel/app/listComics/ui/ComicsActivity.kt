package com.mrtwoza.marvel.app.listComics.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrtwoza.marvel.R
import com.mrtwoza.marvel.app.comicDetail.ui.ComicDetailActivity
import com.mrtwoza.marvel.app.listComics.ui.adapter.ComicsAdapter
import com.mrtwoza.marvel.app.listComics.viewModel.ListComicsViewModel
import com.mrtwoza.marvel.utils.Config
import com.mrtwoza.marvel.utils.Loader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_comics.*

@AndroidEntryPoint
class ComicsActivity : AppCompatActivity(),View.OnClickListener,Loader {

    private val viewModel: ListComicsViewModel by viewModels()
    private lateinit var adapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)
        setRecyclerView()
        setupViewModel()
        setupToolbar()
        showLoader()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    fun setupViewModel(){
        viewModel.getListComics().observe(this, Observer { listComics ->
            adapter.updateList(listComics)
            hideLoader()
        })
        viewModel.getErrorMsg().observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            hideLoader()
        })
    }

    fun setRecyclerView(){
        adapter = ComicsAdapter(this,this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView_of_comics.layoutManager = layoutManager
        recyclerView_of_comics.adapter = adapter
    }

    fun hideLoader(){
        progress_bar.visibility = View.GONE
    }

    fun showLoader(){
        progress_bar.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        val pos : Int = v?.tag as Int
        var bundle = Bundle()
        bundle.putSerializable(Config.COMIC_TAG,adapter.getItem(pos))
        val intent = Intent(this, ComicDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun loadData() {
        showLoader()
        viewModel.loadData()
    }
}