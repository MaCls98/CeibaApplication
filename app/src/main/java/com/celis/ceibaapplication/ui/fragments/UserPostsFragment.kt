package com.celis.ceibaapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.celis.ceibaapplication.adapters.PostsRvAdapter
import com.celis.ceibaapplication.databinding.FragmentUserPostsBinding
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.ui.dialogs.LoadingPopUpDialog
import com.celis.ceibaapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPostsFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var binding: FragmentUserPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserPostsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.get("user")?.let {
            loadUserInfo(it as User)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack()
        }
    }

    private fun loadUserInfo(user: User) {
        binding.cvUser.apply {
            tvUsername.text = user.name
            tvUserPhone.text = user.phone
            tvUserEmail.text = user.email
            btnShowUserPublications.visibility = View.GONE
        }
        loadUserPosts(user)
    }

    private fun loadUserPosts(user: User) {
        LoadingPopUpDialog.startLoading(requireContext())
        viewModel.getPostsByUserId(user.id).observe(viewLifecycleOwner){
            LoadingPopUpDialog.closePopup()
            binding.rvUserPosts.apply {
                adapter = PostsRvAdapter(it)
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}