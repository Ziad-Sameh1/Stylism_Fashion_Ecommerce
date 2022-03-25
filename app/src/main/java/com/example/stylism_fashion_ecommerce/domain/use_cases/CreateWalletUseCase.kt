package com.example.stylism_fashion_ecommerce.domain.use_cases

import com.example.stylism_fashion_ecommerce.data.result_listeners.CreateWalletResultListener
import com.example.stylism_fashion_ecommerce.domain.models.Wallet
import com.example.stylism_fashion_ecommerce.domain.repository.DatabaseRepo
import javax.inject.Inject

class CreateWalletUseCase @Inject constructor(
    private val repo: DatabaseRepo
) {
    operator fun invoke(wallet: Wallet, createWalletResultListener: CreateWalletResultListener) =
        repo.createUserWallet(
            wallet = wallet,
            createWalletResultListener = createWalletResultListener
        )
}