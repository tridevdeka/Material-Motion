package com.tridev.materialcontainertransform

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.tridev.materialcontainertransform.databinding.ItemPosterLineBinding
import kotlinx.parcelize.Parcelize

class ItemAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val mBinding: ItemPosterLineBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemPosterLineBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.mBinding.itemPosterPost.loadImage(item.drawable)
        holder.mBinding.itemPosterTitle.text = item.title

        holder.mBinding.itemContainer.setOnClickListener {
            val transitionName=it.context.getString(R.string.container_item_card,position)
            ViewCompat.setTransitionName(it,transitionName )
            listener.onClick(it, item,transitionName)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private fun ImageView.loadImage(image: Int) {
        val circularProgressDrawable = CircularProgressDrawable(this.context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
        Glide.with(this).load(image).placeholder(circularProgressDrawable)
            .error(com.google.android.material.R.drawable.mtrl_ic_error).into(this)
    }

    interface ClickListener {
        fun onClick(startView: View, item: Item,transitionName:String)
    }
}

@Parcelize
data class Item(var id: Int, var drawable: Int, var title: String) : Parcelable