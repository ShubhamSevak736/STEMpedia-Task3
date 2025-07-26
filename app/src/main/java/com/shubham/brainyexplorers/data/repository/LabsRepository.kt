package com.shubham.brainyexplorers.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shubham.brainyexplorers.data.model.LabItem
import kotlinx.coroutines.tasks.await

/**
 * Repository to fetch labs from Firestore.
 */
class LabsRepository(private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {
    /**
     * Fetches the list of labs from Firestore asynchronously.
     * @return Result containing the list of LabItem or an exception.
     */
    suspend fun getLabs(): Result<List<LabItem>> {
        return try {
            val snapshot = firestore.collection("labs").get().await()
            val labs = snapshot.documents.mapNotNull { it.toObject(LabItem::class.java) }
            Result.success(labs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 