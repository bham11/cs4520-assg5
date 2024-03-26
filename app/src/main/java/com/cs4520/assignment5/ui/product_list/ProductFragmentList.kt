package com.cs4520.assignment5.ui.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.cs4520.assignment5.databinding.ProductActivityLayoutBinding
import com.cs4520.assignment5.model.Product


class ProductFragmentList : Fragment() {

    private lateinit var binding: ProductActivityLayoutBinding
    private  lateinit var viewModel: ProductListViewModel

    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductActivityLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]



        productAdapter = ProductAdapter()
        binding.productRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }


        val productListObserver = Observer<List<Product>> { newProductList ->
            productAdapter.updateData(newProductList)

        }

        viewModel.getProductList().observe(viewLifecycleOwner, productListObserver)
        viewModel.getIsLoading().observe(viewLifecycleOwner) { loaded ->
            if (loaded) {
                this.displayProductMessage()
            }
        }

        viewModel.getErrorMessage().observe(viewLifecycleOwner) {msg ->
            binding.errorMessage.visibility = View.VISIBLE
            binding.errorMessage.text = msg

        }
        viewModel.fetchProducts()






    }

    private fun displayProductMessage() {
        if(viewModel.getProductList().value?.isEmpty() == true) {
            val noProducts = "No products available :("
            binding.loadingMessage.visibility = View.VISIBLE
            binding.loadingMessage.text = noProducts
            binding.errorMessage.visibility = View.INVISIBLE
        }
        else {
            binding.loadingMessage.visibility = View.INVISIBLE

        }
        binding.plProgressBar.visibility = View.INVISIBLE
    }

}
