package id.asep.breedscat.data.source.remote

import id.asep.breedscat.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
object BreedsServiceFactory {

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun provideAuthInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val oldRequest: Request = chain.request()
            val newRequestBuilder: Request.Builder = oldRequest.newBuilder()
            newRequestBuilder.header("x-api-key", apiKey)
            val newRequest = newRequestBuilder.build()
            chain.proceed(newRequest)
        }
    }

    fun provideOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS);
        for (interceptor in interceptors) {
            client.addInterceptor(interceptor)
        }
        return client.build()
    }

    fun provideBreedService(okHttpClient: OkHttpClient, URL: String): BreedsService {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreedsService::class.java)
    }


}
