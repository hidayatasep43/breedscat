package id.asep.breedscat

import android.content.Context
import id.asep.breedscat.BuildConfig
import id.asep.breedscat.data.source.local.AppDatabase
import id.asep.breedscat.data.source.remote.BreedsServiceFactory
import id.asep.breedscat.data.source.repository.BreedsRepository

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class AppContainer(context: Context) {

    //for rest api
    private val httpLoggingInterceptor = BreedsServiceFactory.provideHttpLoggingInterceptor()
    private val authInterceptor = BreedsServiceFactory.provideAuthInterceptor(BuildConfig.API_KEY)
    private val okHttpClient = BreedsServiceFactory.provideOkHttpClient(httpLoggingInterceptor, authInterceptor)
    private val breedsService = BreedsServiceFactory.provideBreedService(okHttpClient, BuildConfig.BASE_URL)

    //for database
    private val breedsDAO = AppDatabase.getDatabase(context).breedsDao()

    //repository
    val breedsRepository = BreedsRepository(breedsDAO, breedsService)

}