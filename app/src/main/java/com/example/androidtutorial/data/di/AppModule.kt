package com.example.androidtutorial.data.di

import android.app.Application
import com.example.androidtutorial.data.remote.MyApi
import com.example.androidtutorial.data.repository.DashboardRepositoryImpl
import com.example.androidtutorial.domain.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        val interceptor = HttpLoggingInterceptor();
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
    fun providedDashboardRepository(api: MyApi, appContext: Application): DashboardRepository {
        return DashboardRepositoryImpl(api, appContext)
    }
}