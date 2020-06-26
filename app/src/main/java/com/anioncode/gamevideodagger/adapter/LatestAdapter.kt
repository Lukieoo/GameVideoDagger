package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.popularModel.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class LatestAdapter(var itemClick: OnClickAdapterListner) : RecyclerView.Adapter<LatestAdapter.ViewHolder>() {

    lateinit var items: ArrayList<Result>

    // Gets the number of games in the list
    override fun getItemCount(): Int {
        if (::items.isInitialized) {

            return items.size
        } else {
            return 0
        }

    }

    interface OnClickAdapterListner {
        fun onClick(game: Result)
    }

    fun setPosts(items: List<Result>) {
        this.items = items as ArrayList<Result>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_latest,
                parent,
                false
            )
        )

    }

    // Binds each item  in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = items.get(position)
        holder.itemView.setOnClickListener {
            itemClick.onClick(model)
        }
        Picasso.get()

            .load(model.background_image)
            .resize(400, 300)
            //  .networkPolicy(NetworkPolicy.OFFLINE)
            .into(holder.photoGame);

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to
        val photoGame = view.photoGame

    }
}

