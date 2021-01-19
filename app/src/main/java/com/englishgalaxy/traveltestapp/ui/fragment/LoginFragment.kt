package com.englishgalaxy.traveltestapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.englishgalaxy.traveltestapp.R
import com.englishgalaxy.traveltestapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance(): LoginFragment =
            LoginFragment()
    }

    private var clickListener: ClickListenerButton? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerButton)
            clickListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorizUser(view)
    }

    private fun authorizUser(view: View) {
        binding.login.setOnClickListener {
            if (binding.username.text.isNotEmpty()) {
                clickListener?.onClickListener(email = binding.username.text.toString())
            } else {
                val invalidEmail: String = resources.getString(R.string.invalid_username)
                Toast.makeText(view.context, invalidEmail, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
        clickListener = null
    }
}

interface ClickListenerButton {
    fun onClickListener(email: String)
}