package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.PatchUserDTO
import com.example.netlib.data.remote.DTO.UserDTO
import com.example.netlib.domain.model.PatchUser
import com.example.netlib.domain.model.User

/**
 * Перевод из UserDTO в User
 */
internal fun UserDTO.toDomain() =
    User(
        id = id,
        email = email ,
        name = name,
        phone = phone,
        avatar = avatar,
        verified = verified,
        created = created,
        updated = updated
    )


/**
 * Перевод из PatchUser в PatchUserDTO
 */
internal fun PatchUser.toRequest(): PatchUserDTO =
    PatchUserDTO(
        name = name,
        phone = phone
    )