package com.celis.ceibaapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.celis.ceibaapplication.adapters.UsersRvAdapter
import com.celis.ceibaapplication.databinding.FragmentUsersBinding
import com.celis.ceibaapplication.persistence.model.User
import com.celis.ceibaapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.getUserList().observe(viewLifecycleOwner){
            viewModel.userList.addAll(it)
            initUserList()
        }
    }

    private fun initUserList() {
        binding.rvUsersList.apply {
            localUserList.addAll(viewModel.userList)
            usersAdapter = UsersRvAdapter(localUserList)
            usersAdapter.userCallback = object: UsersRvAdapter.UserCallback {
                override fun showUserPublications(user: User) {

                }
            }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }
        initSearch()
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