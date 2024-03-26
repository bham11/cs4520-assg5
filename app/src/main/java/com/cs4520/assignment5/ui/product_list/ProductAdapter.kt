package com.cs4520.assignment5.ui.product_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment5.model.Product
import com.cs4520.assignment5.R

class ProductAdapter :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var dataSet: List<Product> = arrayListOf()
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView
        private val holder: View
        private val productName: TextView
        private val date: TextView
        private val price: TextView

        init {
            imageView = view.findViewById(R.id.image)
            productName = view.findViewById(R.id.productName)
            date = view.findViewById(R.id.expDate)
            price = view.findViewById(R.id.price)
            holder = view.findViewById(R.id.holder)

        }
        fun bind(product: Product) {
            if (this.validProduct(product)) {

                productName.text = product.name
                date.isVisible = false
                product.expiryDate?.let {
                    date.text = product.expiryDate
                    date.isVisible = true
                }
                val priceText = "$${product.price}"
                price.text = priceText
                if (product.type == "Equipment") {

                    holder.setBackgroundColor(Color.parseColor("#E06666"))
                    imageView.setImageResource(R.drawable.equipment)

                } else {
                    holder.setBackgroundColor(Color.parseColor("#FFD965"))
                    imageView.setImageResource(R.drawable.food)
                }

            }
        }
        private fun validProduct(product: Product): Boolean {
            return product.name.isNotEmpty()
                    && product.type.isNotEmpty()
                    && product.price.toString().isNotEmpty()

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_activity_row_layout, viewGroup, false)

        return ProductViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        val product = dataSet[position]
        viewHolder.bind(product)

    }

    override fun getItemCount() = dataSet.size
    fun updateData(newProductList: List<Product>) {
        dataSet = newProductList
        notifyDataSetChanged()

    }

}