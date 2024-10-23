package com.example.spotifycopy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.spotifycopy.databinding.FragmentAuthBinding
import com.example.spotifycopy.databinding.FragmentDistributionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DistributionFragment : Fragment() {
    private var _binding: FragmentDistributionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDistributionBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()

        binding.btnDistributeAuthToAccount.setOnClickListener {
            navController.navigate(R.id.action_distributionFragment_to_authFragment)
        }

        binding.btnDistributeRegAccount.setOnClickListener {
            navController.navigate(R.id.action_distributionFragment_to_regFragment)
        }

        return view
    }

}