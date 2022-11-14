package com.zen.androidapinetworking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zen.androidapinetworking.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var preferences: PrefHelper
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PrefHelper(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            preferences.put(Constant.PREF_IS_USERNAME, binding.etUsername.text.toString())
            preferences.put(Constant.PREF_IS_PASSWORD, binding.etPassword.text.toString())
            preferences.put(Constant.PREF_IS_LOGIN, true)
            Toast.makeText(context, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
            val fragment = HomeFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer, fragment)?.commit()
        }

//        binding.btnRegister.setOnClickListener {
//            val fragment = RegisterFragment()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fragmentContainer, fragment)?.commit()
//        }
    }

    override fun onStart() {
        super.onStart()
        if (preferences.getBoolean(Constant.PREF_IS_LOGIN, false)){
            val fragment = HomeFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainer, fragment)?.commit()
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {

            }
    }
}