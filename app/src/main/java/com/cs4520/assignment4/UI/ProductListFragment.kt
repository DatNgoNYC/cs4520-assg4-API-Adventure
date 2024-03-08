package com.cs4520.assignment4.UI

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs4520.assignment4.ViewModels.ProductListViewModel
import com.cs4520.assignment4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment() {

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            // Here you can log the products to test your API call
            Log.d("ApiService","plf.kgt - Fetched products: $products")
            (binding.recyclerView.adapter as ProductAdapter).updateData(products)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.noProducts.observe(viewLifecycleOwner, Observer { noProducts ->
            binding.textViewNoProducts.visibility = if (noProducts) View.VISIBLE else View.GONE
        })

        viewModel.loadProducts()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ProductAdapter(emptyList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
