package com.vanzar.visibleone.data.network.dataSource

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vanzar.visibleone.data.network.mapper.toShoeModel
import com.vanzar.visibleone.domain.shoe.Shoe
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ShoeDataSourceImpl @Inject constructor() : ShoeDataSource {
    private val db = Firebase.firestore
    private val shoeCollection = db.collection("shoes")
    private var localShoes = mutableListOf<Shoe>()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPopularShoes(): List<Shoe> {
        delay(500) // for demo network call
        return suspendCancellableCoroutine { continuation ->
            shoeCollection.get()
                .addOnSuccessListener {
                    continuation.resume(
                        it.documents.toList().mapNotNull { it.data }
                            .map {
                                it.toShoeModel()
                            }.also {
                                localShoes.addAll(it)
                            })
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                    Log.e("error", it.message.toString())
                }
        }
    }

    override suspend fun getShoeByID(id: String): Shoe? {
        delay(400) // for loading dummy network call
        return localShoes.firstOrNull { it.id == id }
    }

    private fun Task<DocumentSnapshot>.addContinuationListener(continuation: CancellableContinuation<DocumentSnapshot>) {
        this
            .addOnSuccessListener {
                continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }
}