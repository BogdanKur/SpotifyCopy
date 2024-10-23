package com.example.spotifycopy

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spotifycopy.databinding.FragmentRegBinding

class RegFragment : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            destination.label = "Регистрация"
            binding.toolbar.title = destination.label
        }
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnRegAccount.setOnClickListener {
            viewModel.addUser(requireContext(), navController)
        }

        return view
    }


}