package com.example.flashfeed

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsRVAdapter(
    private val articlesArrayList: ArrayList<Articles>,
    private val context: Context
) : RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articles = articlesArrayList[position]
        holder.subTitleTV.text = articles.title
        holder.titleTV.text = articles.content
        Picasso.get().load(articles.urlToImage).into(holder.newsIV)
        holder.itemView.setOnClickListener {
            val i = Intent(context, NewsDetailActivity::class.java)
            i.putExtra("title", articles.title)
            i.putExtra("content", articles.content)
            i.putExtra("desc", articles.description)
            i.putExtra("image", articles.urlToImage)
            i.putExtra("url", articles.url)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return articlesArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView
        val subTitleTV: TextView
        val newsIV: ImageView

        init {
            titleTV = itemView.findViewById(R.id.idTVNewsHeading)
            subTitleTV = itemView.findViewById(R.id.idTVSubHeading)
            newsIV = itemView.findViewById(R.id.idIVNews)
        }
    }
}