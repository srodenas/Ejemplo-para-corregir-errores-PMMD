package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.RepositoryUsers
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseLoginApi(val repositoryUsers: RepositoryUsers) {

    suspend fun login(email: String, passw: String) : User?{
        return repositoryUsers.isLoginApi(email, passw)
    }
}