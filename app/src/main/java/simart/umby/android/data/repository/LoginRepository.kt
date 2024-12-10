package simart.umby.android.data.repository

import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.model.login.LoginFormModel

class LoginRepositoryImpl(private val api: MyApi): LoginRepository {
    override suspend fun login(form: LoginFormModel): Response<String> = api.login(form)
}

interface LoginRepository {
    suspend fun login(form: LoginFormModel): Response<String>
}