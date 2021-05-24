package com.mrtwoza.marvel.app.comicDetail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mrtwoza.marvel.R
import com.mrtwoza.marvel.app.listComics.model.Comic
import com.mrtwoza.marvel.utils.Config
import kotlinx.android.synthetic.main.activity_comic_detail.*
import kotlinx.android.synthetic.main.comic_row.comic_image

class ComicDetailActivity : AppCompatActivity() {

    private lateinit var comic: Comic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        setupToolbar()

        comic = intent.extras?.getSerializable(Config.COMIC_TAG) as Comic
        setupViews()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setupViews(){
        val url = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
        Glide.with(this).load(url).transition(DrawableTransitionOptions.withCrossFade(500)).into(comic_image)

        comic_title.setText(comic.title)
        btn_events.setText(comic.events.items.size.toString())
        btn_series.setText(comic.issueNumber.toString())
        btn_variants.setText(comic.variants.size.toString())
    }
}