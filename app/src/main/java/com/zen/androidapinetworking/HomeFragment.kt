package com.zen.androidapinetworking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zen.androidapinetworking.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var preferences: PrefHelper
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = PrefHelper(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHello.text = preferences.getString(Constant.PREF_IS_USERNAME)
        binding.btnLogout.setOnClickListener{
            preferences.clear()
            Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show()
            val fragment = LoginFragment()
            val transaction = fragmentManager?.beginTransaction()

            transaction?.replace(R.id.fragmentContainer, fragment)?.commit()

        }
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }
}