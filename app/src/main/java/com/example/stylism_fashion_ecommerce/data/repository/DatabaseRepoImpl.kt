package com.example.stylism_fashion_ecommerce.data.repository

import com.example.stylism_fashion_ecommerce.data.result_listeners.AddUserResultListener
import com.example.stylism_fashion_ecommerce.data.result_listeners.CreateWalletResultListener
import com.example.stylism_fashion_ecommerce.domain.models.User
import com.example.stylism_fashion_ecommerce.domain.models.Wallet
import com.example.stylism_fashion_ecommerce.domain.repository.DatabaseRepo
import com.example.stylism_fashion_ecommerce.utils.CONSTANTS
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DatabaseRepoImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : DatabaseRepo {
    override fun addUser(user: User, addUserResultListener: AddUserResultListener) {
        firebaseFirestore.collection(CONSTANTS.USERS_COLLECTION).document(user.userId).set(user).addOnCompleteListener {
            if (it.isSuccessful) {
                addUserResultListener.onSuccess()
            }
            else {
                addUserResultListener.onFailure(it.exception!!)
            }
        }
    }

    override fun createUserWallet(wallet: Wallet, createWalletResultListener: CreateWalletResultListener) {
        firebaseFirestore.collection(CONSTANTS.WALLETS_COLLECTION).document(wallet.walletId).set(wallet).addOnCompleteListener {
            if (it.isSuccessful) {
                createWalletResultListener.onSuccess()
            }
            else {
                createWalletResultListener.onFailure(it.exception!!)
            }
        }
    }
}