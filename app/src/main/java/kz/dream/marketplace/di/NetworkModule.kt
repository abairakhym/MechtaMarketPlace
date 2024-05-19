package kz.dream.marketplace.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kz.dream.marketplace.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.dream.marketplace.MechtaService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context) = ChuckerInterceptor.Builder(context).build()

    @Provides
    @Singleton
    fun providesOkHttpClient(chucker: ChuckerInterceptor) = OkHttpClient.Builder().apply {
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(chucker)
    }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(Constants.BASE_URL)
        client(okHttpClient)
    }.build()

    @Provides
    @Singleton
    fun provideMechtaService(retrofit: Retrofit) = retrofit.create<MechtaService>()
}