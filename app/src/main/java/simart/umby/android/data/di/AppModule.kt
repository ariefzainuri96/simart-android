package simart.umby.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.repository.DashboardRepository
import simart.umby.android.data.repository.DashboardRepositoryImpl
import simart.umby.android.data.repository.LoginRepository
import simart.umby.android.data.repository.LoginRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun providedDashboardRepository(api: MyApi): DashboardRepository {
        return DashboardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providedLoginRepository(api: MyApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }
}