package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.ranked.Result
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class LatestAdapter: RecyclerView.Adapter<ViewHolder1>() {

    lateinit var items: ArrayList<Result>
    // Gets the number of games in the list
    override fun getItemCount(): Int {
        if(::items.isInitialized){

            return items.size
        }else{
         return 0
        }

    }

    fun setPosts(items: List<Result>) {
        this.items = items as ArrayList<Result>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {

        return ViewHolder1(LayoutInflater.from(parent.context).inflate(R.layout.item_latest, parent, false))

    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        var model=items.get(position)
        Picasso.get()

            .load(model.background_image)
//            .load("https://media.rawg.io/media/games/2ad/2ad87a4a69b1104f02435c14c5196095.jpg")
            .resize(400,300)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into( holder.photoGame);

    }
}

class ViewHolder1 (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each video to
    val photoGame = view.photoGame

}