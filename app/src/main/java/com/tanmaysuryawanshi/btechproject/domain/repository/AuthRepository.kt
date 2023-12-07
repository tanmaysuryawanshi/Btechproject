package com.tanmaysuryawanshi.btechproject.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.tanmaysuryawanshi.btechproject.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginuser(email:String,password:String):Flow<Resource<AuthResult>>
    fun registerUser(email: String,password: String):Flow<Resource<AuthResult>>

}