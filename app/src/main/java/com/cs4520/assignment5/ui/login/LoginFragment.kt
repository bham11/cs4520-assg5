package com.cs4520.assignment5.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment5.R
import com.cs4520.assignment5.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]




        this.setEditTextColors()
        this.setOnClickListener()






    }

    private fun setEditTextColors() {
        binding.username.setOnClickListener {
            binding.username.setTextColor(R.color.teal_200.toInt())
        }


        binding.password.setOnClickListener {
            binding.password.setTextColor(R.color.teal_200.toInt())
        }
    }

    private fun setOnClickListener() {
        binding.loginButton.setOnClickListener {
            loginViewModel.setUser(binding.username.text.toString(),
                binding.password.text.toString() )

            if (loginViewModel.isUserValid(loginViewModel.user.value)) {
                // move to the next page
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                binding.username.text.clear()
                binding.password.text.clear()

                findNavController().navigate(R.id.action_loginFragment_to_productFragmentList)


            } else {
                binding.username.text.clear()
                binding.password.text.clear()
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}