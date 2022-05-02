package com.celis.ceibaapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.celis.ceibaapplication.R
import com.celis.ceibaapplication.adapters.UsersRvAdapter
import com.celis.ceibaapplication.databinding.FragmentUsersBinding
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.ui.dialogs.LoadingPopUpDialog
import com.celis.ceibaapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()
    private val localUserList = arrayListOf<User>()
    private lateinit var binding: FragmentUsersBinding
    private lateinit var usersAdapter: UsersRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserListFromDB().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                requestUserListFromApi()
            }else{
                viewModel.userList.apply {
                    clear()
                    addAll(it)
                }
                initUserList()
            }
        }
    }

    private fun requestUserListFromApi() {
        LoadingPopUpDialog.startLoading(requireContext())
        viewModel.getUserListFromApi().observe(viewLifecycleOwner) {
            viewModel.insertUsersIntoDB(it).invokeOnCompletion {
                lifecycleScope.launch(Dispatchers.Main) {
                    LoadingPopUpDialog.closePopup()
                    initUserList()
                }
            }
        }
    }

    private fun initUserList() {
        binding.rvUsersList.apply {
            localUserList.addAll(viewModel.userList)
            usersAdapter = UsersRvAdapter(localUserList)
            usersAdapter.userCallback = object: UsersRvAdapter.UserCallback {
                override fun showUserPublications(user: User) {
                    navigateToUserPublications(user)
                }
            }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
        initSearch()
    }

    private fun navigateToUserPublications(user: User) {
        findNavController().navigate(R.id.action_usersFragment_to_userPostsFragment, bundleOf("user" to user))
    }

    private fun initSearch() {
        binding.tilUsersSearch.editText!!.addTextChangedListener {
            it?.let { search ->
                localUserList.apply {
                    if (search.isEmpty()){
                        clear()
                        addAll(viewModel.userList)
                    }else{
                        clear()
                        viewModel.userList.forEach { user ->
                            if (user.name.contains(search, true)){
                                add(user)
                            }
                        }
                    }
                    binding.tvListIsEmpty.visibility = if (isEmpty()) View.VISIBLE else View.GONE
                }
                usersAdapter.notifyDataSetChanged()
            }
        }
    }
}