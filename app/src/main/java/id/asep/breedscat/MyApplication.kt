package id.asep.breedscat

import androidx.multidex.MultiDexApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class MyApplication: MultiDexApplication() {

    val appContainer by lazy {
        AppContainer(this);
    }

    override fun onCreate() {
        super.onCreate()

        //Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }
}