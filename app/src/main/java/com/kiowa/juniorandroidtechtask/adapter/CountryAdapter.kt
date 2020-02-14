package com.kiowa.juniorandroidtechtask.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.kiowa.juniorandroidtechtask.ExtraDetailsDialog
import com.kiowa.juniorandroidtechtask.R
import com.kiowa.juniorandroidtechtask.models.Country
import kotlinx.android.synthetic.main.country_base_display.view.*

class CountryAdapter(private val apiResponse: Array<Country>, private var manager: FragmentManager):RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.country_base_display,parent,false)
        return CountryViewHolder(row)
    }

    override fun getItemCount(): Int {
        return apiResponse.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

      val apiResponseValue = apiResponse[position]
        holder.itemView.name.text = apiResponseValue.name
        holder.itemView.capital.text = apiResponseValue.capital
        holder.itemView.region.text = apiResponseValue.region

        holder.itemView.setOnClickListener {
            var transitionName = "recycler_to_details"

            val dialog = ExtraDetailsDialog(apiResponseValue)
            dialog.show(manager,"ExtraDetailsFragment")

        }

    }
    class CountryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
}