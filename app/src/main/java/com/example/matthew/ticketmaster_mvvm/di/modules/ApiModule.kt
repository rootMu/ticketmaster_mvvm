package com.example.matthew.ticketmaster_mvvm.di.modules

import com.example.matthew.ticketmaster_mvvm.BuildConfig
import com.example.matthew.ticketmaster_mvvm.network.TicketMasterApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * API Specific Dependancies
 *
 * @author Matthew Howells
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideHubServiceApi(retrofit: Retrofit): TicketMasterApiService {
        return retrofit.create(TicketMasterApiService::class.java)
    }
}