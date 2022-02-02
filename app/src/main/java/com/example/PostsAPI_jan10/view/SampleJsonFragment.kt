package com.example.PostsAPI_jan10.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.PostsAPI_jan10.databinding.FragmentTodoBinding
import com.example.PostsAPI_jan10.model.network.ApiManager
import com.example.PostsAPI_jan10.model.network.models.Post
import com.example.PostsAPI_jan10.model.repository.SampleJsonRepository
import com.example.PostsAPI_jan10.utils.Resource
import com.example.PostsAPI_jan10.view.adapter.PostAdapter
import com.example.PostsAPI_jan10.viewmodel.SampleJsonViewModel

class SampleJsonFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private val viewModel: SampleJsonViewModel by activityViewModels {
        SampleJsonViewModel.Factory(
            SampleJsonRepository(
                ApiManager()
            )
        )
    }

    private val postAdapter by lazy {
        PostAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            jsonRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }

            // Click Listeners
            postBtn.setOnClickListener {
                viewModel.selectedDataType = SelectedData.POSTS
            }

            viewModel.data.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                        btnGroup.isEnabled = false
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        btnGroup.isEnabled = true
                        when (viewModel.selectedDataType) {
                            SelectedData.POSTS -> {
                                jsonRv.adapter = postAdapter
                                postAdapter.submitList(resource.data as List<Post>)
                            }
                        }
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        btnGroup.isEnabled = true
                        showToast(resource.message)
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}