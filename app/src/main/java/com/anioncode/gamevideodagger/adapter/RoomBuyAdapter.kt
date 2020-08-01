package com.anioncode.smogu.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.main.databaseFragment.entity.Game
import com.anioncode.gamevideodagger.model.popularModel.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_buy.view.*
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_popular.view.photoGame

class RoomBuyAdapter(var itemClick: OnClickAdapterListner) : RecyclerView.Adapter<RoomBuyAdapter.ViewHolder>() {

    lateinit var items: ArrayList<Game>

    // Gets the number of games in the list
    override fun getItemCount(): Int {
        if (::items.isInitialized) {

            return items.size
        } else {
            return 0
        }

    }

    interface OnClickAdapterListner {
        fun onClick(game: Game)
        fun onStore(game: Game)
        fun onDelete(game: Game)
    }

    fun setPosts(items: List<Game>) {
        this.items = items as ArrayList<Game>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_buy,
                parent,
                false
            )
        )

    }

    // Binds each item  in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = items.get(position)
        holder.delete.setOnClickListener {
            itemClick.onDelete(model)
            holder.swipe.close(true)
        }
        holder.cardstates.setOnClickListener {
            itemClick.onClick(model)
        }
        holder.store.setOnClickListener {
            itemClick.onStore(model)
            holder.swipe.close(true)
        }
        Picasso.get()
            .load(model.image)
            .resize(750,500)
            .centerCrop()
            .into( holder.photoGame)
            //  .networkPolicy(NetworkPolicy.OFFLINE

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to
        val photoGame = view.photoGame
        val delete = view.deleteThis
        val store = view.store
        val swipe = view.swipe
        val cardstates = view.cardstates

    }
}

