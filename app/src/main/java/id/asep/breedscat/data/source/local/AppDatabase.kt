package id.asep.breedscat.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.asep.breedscat.data.model.breeds.Breeds

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
@Database(entities = arrayOf(Breeds::class), version = 1, exportSchema = false)
public abstract class AppDatabase: RoomDatabase() {

    abstract fun breedsDao(): BreedsDAO

    companion object {

        //singleton prevents multiple instanse of database opening at the same time
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "breeds_database").build()
                INSTANCE = instance
                return instance
            }
        }

    }
}