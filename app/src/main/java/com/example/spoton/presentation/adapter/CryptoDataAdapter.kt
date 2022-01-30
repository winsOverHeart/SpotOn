package com.example.spoton.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spoton.R
import com.example.spoton.data.model.Data
import com.example.spoton.databinding.ListItemBinding

class CryptoDataAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    private val dataList = ArrayList<Data>()

    fun setList(dataList: List<Data>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }
}

class MyViewHolder(val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Data, position: Int) {
        binding.index.text = position.toString()
        binding.fundName.text = data.name
        binding.increasePercent?.apply {
            text = data.changePercent24Hr ?: "0"
            data.changePercent24Hr?.let {
                try {
                    val value: Double = data.changePercent24Hr.toDouble()
                    if (value < 0) {
                        setTextColor(ContextCompat.getColor(context, R.color.red))
                    } else {
                        setTextColor(ContextCompat.getColor(context, R.color.teal_700))
                    }
                } catch (e: NumberFormatException) {
                }
            }
        }
        binding.fundPrice.text = data.priceUsd
    }
}
