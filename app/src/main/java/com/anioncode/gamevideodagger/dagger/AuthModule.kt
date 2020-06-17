package com.anioncode.gamevideodagger.dagger

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    companion object {
        @Provides
        @JvmStatic

        fun provideSessionApi(retrofit: Retrofit): AuthApi {
            return retrofit.create<AuthApi>(AuthApi::class.java)
        }
    }
}
