package com.example.workable.ui.pages.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workable.databinding.PageBlogBinding
import com.example.workable.viewmodel.BlogViewModel

class BlogPage : Fragment() {

    private lateinit var binding: PageBlogBinding
    private val viewModel: BlogViewModel by viewModels()
    private lateinit var adapter: BlogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BlogAdapter(emptyList()) { blog ->
            // Handle click — open blog details or navigate
            // Example:
            // findNavController().navigate(R.id.action_blogPage_to_blogDetailsPage)
        }

        binding.blogRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.blogRecyclerView.adapter = adapter

        // Observe LiveData from BlogViewModel
        viewModel.blogs.observe(viewLifecycleOwner) { blogs ->
            adapter.updateData(blogs)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            binding.errorText.apply {
                visibility = if (error != null) View.VISIBLE else View.GONE
                text = error ?: ""
            }
        }
    }
}
