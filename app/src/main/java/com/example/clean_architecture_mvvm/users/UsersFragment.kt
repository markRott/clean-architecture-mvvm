package com.example.clean_architecture_mvvm.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clean_architecture_mvvm.extensions.observe
import com.example.clean_architecture_mvvm.ui_state.UiResult
import com.example.clean_architecture_mvvm.ui_state.Status
import com.example.domain.models.User

class UsersFragment : Fragment() {

    private val usersVM by activityViewModels<UsersVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun fetchUsersRequest() {
        usersVM.fetchUsers()
    }

    private fun receiveUsers() {
        viewLifecycleOwner.observe {
            usersVM.usersUiFlow.collect { state: UiResult<List<User>> ->
                when (state.status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> { println("UI User: ${state.data}") }
                    Status.ERROR -> { println("Fetch users exception: ${state.errorMsg}") }
                    else -> Unit
                }
            }
        }
    }
}