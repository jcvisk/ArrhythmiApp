package com.iunis.arrhythmiapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iunis.arrhythmiapp.databinding.ItemHeartDataBinding
import com.iunis.arrhythmiapp.domain.model.HeartData

class HeartDataListAdapter: ListAdapter<HeartData, HeartDataListAdapter.HeartDataViewHolder>(Companion){

    companion object : DiffUtil.ItemCallback<HeartData>() {
        override fun areItemsTheSame(oldItem: HeartData, newItem: HeartData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HeartData, newItem: HeartData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HeartDataViewHolder(val binding: ItemHeartDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeartDataViewHolder {
        return HeartDataViewHolder(
            ItemHeartDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeartDataViewHolder, position: Int) {
        val heartData = currentList[position]

        holder.binding.tvSystolic.text = heartData.systolic.toString()
        holder.binding.tvDiastolic.text = heartData.diastolic.toString()
        holder.binding.tvPulse.text = heartData.pulse.toString()
        holder.binding.tvDate.text = heartData.fecha.toString()

        holder.itemView.apply {

            setOnClickListener {
                onHeartDataClickListener?.let { click ->
                    click(heartData)
                }
            }

            setOnLongClickListener {
                onHeartDataLongClickListener?.let { click ->
                    click(heartData)
                }
                true
            }
        }
    }

    protected var onHeartDataClickListener: ((HeartData) -> Unit)? = null
    protected var onHeartDataLongClickListener: ((HeartData) -> Unit)? = null

    fun setHeartDataClickListener(listener: (HeartData) -> Unit){
        onHeartDataClickListener = listener
    }

    fun setHeartDataLongClickListener(listener: (HeartData) -> Unit){
        onHeartDataLongClickListener = listener
    }
}