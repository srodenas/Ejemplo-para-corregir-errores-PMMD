package com.example.srodenas.example_with_catalogs.domain.users.usecase

import com.example.srodenas.example_with_catalogs.domain.users.models.RepositoryUsers
import com.example.srodenas.example_with_catalogs.domain.users.models.User

class UseCaseRegisterLogin (val repositoryUsers: RepositoryUsers){

    suspend fun register(user: User): Boolean {
        return repositoryUsers.registerEntity(user)
    }
}