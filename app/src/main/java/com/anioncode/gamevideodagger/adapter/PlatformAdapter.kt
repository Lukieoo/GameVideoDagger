package com.anioncode.smogu.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anioncode.gamevideodagger.R
import com.anioncode.gamevideodagger.model.plaformModel.Result
import kotlinx.android.synthetic.main.item_ganger.view.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class PlatformAdapter(var  itemClick:OnClickAdapterListner) : RecyclerView.Adapter<PlatformAdapter.ViewHolder>() {


    lateinit var items: ArrayList<Result>
//    lateinit var  itemClick:OnClickAdapterListner


    // Gets the number of games in the list

    interface  OnClickAdapterListner{
        fun onClick( game:Result,items: ArrayList<Result>)
    }
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
    fun getPost():ArrayList<Result> {
       return this.items
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ganger, parent, false))

    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=items.get(position)
        holder.titleType.text=model.name
        if (model.clicked){
            holder.titleBack.setBackgroundColor(Color.parseColor("#c43e00"))
        }else{

            holder.titleBack.setBackgroundColor(Color.parseColor("#342A24"))
        }
        holder.itemView.setOnClickListener {
            itemClick.onClick(model,items)
            println("CLICK")
            if (model.clicked){
                holder.titleBack.setBackgroundColor(Color.parseColor("#c43e00"))
            }else{

                holder.titleBack.setBackgroundColor(Color.parseColor("#342A24"))
            }
        }

    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to

        val titleBack = view.titleBack
        val titleType = view.titleType

    }
}

