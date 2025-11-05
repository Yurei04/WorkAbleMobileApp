package com.example.workable.ui.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workable.databinding.PageHomeBinding
import com.example.workable.viewmodel.HomeViewModel
import com.example.workable.ui.pages.jobs.JobAdapter

class HomePage : Fragment() {

    private lateinit var binding: PageHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView adapter
        adapter = JobAdapter(emptyList()) { job ->
            // TODO: Navigate to JobDetailsPage or show bottom sheet
            // Example:
            // findNavController().navigate(R.id.action_homePage_to_jobDetailsPage)
        }

        binding.featuredJobsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.featuredJobsRecycler.adapter = adapter

        // Observe data from ViewModel
        viewModel.welcomeMessage.observe(viewLifecycleOwner) { message ->
            binding.welcomeText.text = message
        }

        viewModel.featuredJobs.observe(viewLifecycleOwner) { jobs ->
            adapter.updateData(jobs)
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

        // Example of refresh button (if included in layout)
        binding.refreshButton?.setOnClickListener {
            viewModel.refreshHome()
        }
    }
}
