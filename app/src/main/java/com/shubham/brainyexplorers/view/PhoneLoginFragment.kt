package com.shubham.brainyexplorers.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.shubham.brainyexplorers.databinding.FragmentPhoneLoginBinding

class PhoneLoginFragment : Fragment() {
    private var _binding: FragmentPhoneLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhoneLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sendOtpButton.setOnClickListener {
            val phone = binding.phoneEditText.text.toString().trim()
            if (phone.length == 10) {
                // Notify activity to start OTP flow
                (activity as? LoginActivity)?.startOtpVerification(phone)
            } else {
                Toast.makeText(requireContext(), "Enter valid 10-digit phone number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 