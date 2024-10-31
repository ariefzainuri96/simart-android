package com.example.androidtutorial.data.repository

import com.example.androidtutorial.data.remote.MyApi
import com.example.androidtutorial.pages.login.model.LoginFormModel
import retrofit2.Response

class LoginRepositoryImpl(private val api: MyApi): LoginRepository {
    override suspend fun login(form: LoginFormModel): Response<String> = api.login(form)
}

interface LoginRepository {
    suspend fun login(form: LoginFormModel): Response<String>
}