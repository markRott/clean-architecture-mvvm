package com.example.clean_architecture_mvvm.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clean_architecture_mvvm.databinding.FragmentLoginBinding
import com.example.clean_architecture_mvvm.extensions.autoDestroy
import com.example.clean_architecture_mvvm.extensions.data
import com.example.clean_architecture_mvvm.extensions.observe
import com.example.clean_architecture_mvvm.instant.UpdateButtonState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding by autoDestroy()

    private val loginVM by activityViewModels<LoginVM>()
    private val updateButtonState by activityViewModels<UpdateButtonState>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateLoginButtonState()
        onLoginClick()
        receiveLoginStatus()
    }

    private fun updateLoginButtonState() {
        addTextChangedListener(binding.edtEmail) { updateButtonState.setEmail(it) }
        addTextChangedListener(binding.edtPassword) { updateButtonState.setPassword(it) }

        viewLifecycleOwner.observe {
            updateButtonState.updateButtonFlow.collect {
                binding.btnLogin.isEnabled = it
            }
        }
    }

    private fun addTextChangedListener(edt: EditText, block: (String) -> Unit) {
        edt.addTextChangedListener {
            block.invoke(it?.data() ?: "")
        }
    }

    private fun onLoginClick() {
        binding.btnLogin.setOnClickListener {
            loginVM.executeLogin(
                binding.edtEmail.data(),
                binding.edtPassword.data()
            )
        }
    }

    private fun receiveLoginStatus() {
        viewLifecycleOwner.observe {
            loginVM.loginFlow.collect { state ->
                when (state) {
                    LoginUiState.Loading -> Unit
                    LoginUiState.SuccessLogin -> Unit
                    LoginUiState.FailLogin -> Unit
                }
            }
        }
    }
}