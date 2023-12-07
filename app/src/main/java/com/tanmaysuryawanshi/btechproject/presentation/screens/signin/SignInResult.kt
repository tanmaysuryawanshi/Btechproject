package com.tanmaysuryawanshi.btechproject.presentation.screens.signin

import com.tanmaysuryawanshi.btechproject.domain.model.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)


