package com.example.flashfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CatergoryRVAdapter(
    private val categoryRVModel: ArrayList<CategoryRVModel>,
    private val context:Context,
    private val categoryClickInterface: CategoryClickInterface
) : RecyclerView.Adapter<CatergoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryRVModel1 = categoryRVModel[position]
        holder.categoryTV.text = categoryRVModel1.category
        Picasso.get().load(categoryRVModel1.categoryImageUrl).into(holder.categoryIV)
        holder.itemView.setOnClickListener { categoryClickInterface.onCategoryClick(position) }
    }

    override fun getItemCount(): Int {
        return categoryRVModel.size
    }

    interface CategoryClickInterface {
        fun onCategoryClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTV: TextView
        val categoryIV: ImageView

        init {
            categoryTV = itemView.findViewById(R.id.idTVCategories)
            categoryIV = itemView.findViewById(R.id.idIVCategories)
        }
    }
}