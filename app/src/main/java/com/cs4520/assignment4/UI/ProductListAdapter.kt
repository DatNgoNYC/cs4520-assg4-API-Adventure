package com.cs4520.assignment4.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.R
import com.cs4520.assignment4.databinding.ProductListItemBinding


class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun updateData(newData: List<Product>) {

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
                productPrice.text = String.format("$%.2f", product.price)

                val backgroundColor = when (product.type) {
                    "food" -> R.color.light_yellow
                    "equipment" -> R.color.light_red
                    else -> 0
                }
                root.setBackgroundColor(ContextCompat.getColor(root.context, backgroundColor))

                val image = when (product.type) {
                    "food" -> R.drawable.food
                    "equipment" -> R.drawable.equipment
                    else -> 0
                }

                productImage.setImageResource(image)
            }
        }
    }
}