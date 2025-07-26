package com.shubham.brainyexplorers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import com.shubham.brainyexplorers.databinding.ActivityLoginBinding
import android.content.Intent
import com.shubham.brainyexplorers.util.FirebaseUtils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthProvider
import android.widget.Toast
import android.app.ProgressDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var progressDialog: ProgressDialog? = null
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var pendingPhone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show PhoneLoginFragment by default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.loginFragmentContainer.id, PhoneLoginFragment())
                .commit()
        }
    }

    // Called by PhoneLoginFragment when phone is entered
    fun startOtpVerification(phone: String) {
        pendingPhone = phone
        showProgress("Sending OTP...")
        FirebaseUtils.startPhoneNumberVerification(this, phone, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: com.google.firebase.auth.PhoneAuthCredential) {
                hideProgress()
                // Auto-retrieval or instant verification
                Toast.makeText(this@LoginActivity, "Verification completed automatically", Toast.LENGTH_SHORT).show()
                // Optionally sign in directly
            }
            override fun onVerificationFailed(e: FirebaseException) {
                hideProgress()
                Toast.makeText(this@LoginActivity, "Verification failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                hideProgress()
                storedVerificationId = verificationId
                resendToken = token
                FirebaseUtils.verificationId = verificationId
                FirebaseUtils.resendToken = token
                // Show OTP fragment
                supportFragmentManager.beginTransaction()
                    .replace(binding.loginFragmentContainer.id,
                        OtpVerificationFragment.newInstance(phone)
                    )
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(this@LoginActivity, "OTP sent!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Called by OtpVerificationFragment when OTP is entered
    fun verifyOtp(phone: String, otp: String) {
        val verificationId = storedVerificationId ?: FirebaseUtils.verificationId
        if (verificationId == null) {
            Toast.makeText(this, "No verification ID. Please retry.", Toast.LENGTH_SHORT).show()
            return
        }
        showProgress("Verifying OTP...")
        FirebaseUtils.verifyOtpCode(verificationId, otp, onSuccess = {
            hideProgress()
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, onFailure = { e ->
            hideProgress()
            Toast.makeText(this, "OTP verification failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        })
    }

    private fun showProgress(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setCancelable(false)
        }
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }
    private fun hideProgress() {
        progressDialog?.dismiss()
    }
} 