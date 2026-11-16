package com.example.netlib.domain.repository

import com.example.netlib.domain.model.FileUpload
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.PatchUser
import com.example.netlib.domain.model.User

interface ProfileRepository {
    suspend fun getProfile(
        userId: String
    ): NetworkResult<User>



    suspend fun patchProfile(
        userId: String,
        data: PatchUser,
        avatar: FileUpload? = null
    ): NetworkResult<User>
}