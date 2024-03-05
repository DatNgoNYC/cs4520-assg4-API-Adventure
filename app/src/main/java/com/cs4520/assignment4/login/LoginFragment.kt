package com.cs4520.assignment4.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment4.R
import com.cs4520.assignment4.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginSuccess.observe(viewLifecycleOwner) { loginSuccess ->
            if (loginSuccess) {
                findNavController().navigate(R.id.action_login_fragment_to_product_list_fragment)
            } else {
                Toast.makeText(context, "wrong username/password or the app just started", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(username, password)

            binding.username.text.clear()
            binding.password.text.clear()


        }
    }

}










