package com.shubham.brainyexplorers.util

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

/**
 * Utility object for Firebase Authentication (Phone Auth).
 */
object FirebaseUtils {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var verificationId: String? = null
    var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    /**
     * Start phone number verification. Calls the provided callbacks on events.
     */
    fun startPhoneNumberVerification(
        activity: Activity,
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber") // Change country code as needed
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * Verify the OTP code and sign in.
     */
    fun verifyOtpCode(
        verificationId: String,
        code: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential, onSuccess, onFailure)
    }

    /**
     * Sign in with the given credential.
     */
    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(auth.currentUser?.uid ?: "")
                } else {
                    onFailure(task.exception ?: Exception("Unknown error"))
                }
            }
    }
} 