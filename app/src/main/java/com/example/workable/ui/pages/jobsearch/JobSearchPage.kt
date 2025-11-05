package com.example.workable.ui.pages.jobsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workable.databinding.PageJobSearchBinding
import com.example.workable.ui.pages.jobs.JobAdapter
import com.example.workable.viewmodel.JobSearchViewModel

class JobSearchPage : Fragment() {

    private lateinit var binding: PageJobSearchBinding
    private val viewModel: JobSearchViewModel by viewModels()
    private lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageJobSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = JobAdapter(emptyList()) { job ->
            // TODO: Navigate to JobDetailsPage or show job info
            // Example:
            // findNavController().navigate(R.id.action_jobSearchPage_to_jobDetailsPage)
        }

        binding.jobResultsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.jobResultsRecycler.adapter = adapter

        // Observe jobs from ViewModel
        viewModel.filteredJobs.observe(viewLifecycleOwner) { jobs ->
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

        // Handle search input
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchJobs(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
