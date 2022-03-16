package com.example.stylism_fashion_ecommerce.feature_login.domain.use_cases

import com.example.stylism_fashion_ecommerce.SignedInUser
import com.example.stylism_fashion_ecommerce.feature_login.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetSignedInUserUseCase @Inject constructor(
    private val repo: AuthRepo
) {
    operator fun invoke() : FirebaseUser? {
        SignedInUser.user = repo.getSignedInUser()
       return SignedInUser.user
    }
}