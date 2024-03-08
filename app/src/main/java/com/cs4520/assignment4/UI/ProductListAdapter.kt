package com.cs4520.assignment4.UI

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.R
import com.cs4520.assignment4.databinding.ProductListItemBinding


class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    @SuppressLint("NotifyDataSetChanged") // todo(). it works for now.
    fun updateData(newData: List<Product>) {
        productList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(private val binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                productName.text = product.name
                productPrice.text = product.price

                if (!product.expiryDate.isNullOrBlank()) {
                    productExpiryDate.text = product.expiryDate
                } else {
                    productExpiryDate.text = ""
                }

                val backgroundColor = when (product.type) {
                    "Food" -> R.color.light_yellow
                    "Equipment" -> R.color.light_red
                    else -> R.color.light_red
                }
                root.setBackgroundColor(ContextCompat.getColor(root.context, backgroundColor))

                val image = when (product.type) {
                    "Food" -> R.drawable.food
                    "Equipment" -> R.drawable.equipment
                    else -> R.drawable.equipment
                }
                productImage.setImageResource(image)
            }
        }
    }
}