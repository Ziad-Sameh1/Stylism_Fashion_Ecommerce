package com.example.stylism_fashion_ecommerce.domain.use_cases

import com.example.stylism_fashion_ecommerce.data.result_listeners.AddUserResultListener
import com.example.stylism_fashion_ecommerce.domain.models.User
import com.example.stylism_fashion_ecommerce.domain.repository.DatabaseRepo
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repo: DatabaseRepo
) {
    operator fun invoke(user: User, addUserResultListener: AddUserResultListener) =
        repo.addUser(user = user, addUserResultListener = addUserResultListener)
}