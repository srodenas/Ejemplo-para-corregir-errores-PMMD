package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.RepositoryUsers
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseLogin (val repositoryUsers: RepositoryUsers){


    suspend fun login(email: String, password: String): User?{
        return repositoryUsers.isLoginEntity(email, password)
    }

}
