package com.arioki.belanjaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arioki.belanjaapp.R
import com.arioki.belanjaapp.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_product.view.*

class ListProductAdapter(val listProduct:ArrayList<Product>) :
    RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_product,parent,false)
        return ListProductViewHolder(view)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback =onItemClickCallback
    }


    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
        holder.itemView.setOnClickListener(){
            onItemClickCallback
                .onItemClick(listProduct[holder.adapterPosition])
        }
    }


    inner class ListProductViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        fun bind(product: Product){
            with(itemView){
                tvProductName.text = product.name
                tvProductPrice.text = product.price.toString()
                Glide.with(this).load(product.image).into(imageProduct)
            }
        }
    }
    interface OnItemClickCallback{
        fun onItemClick(data:Product)
    }
}