package id.asep.breedscat.data.source.local

import androidx.room.*
import id.asep.breedscat.data.model.breeds.Breeds

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
@Dao
interface BreedsDAO {

    @Query("SELECT * FROM Breeds")
    suspend fun getAllBreeds(): List<Breeds>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breeds: Breeds)

    @Query("DELETE FROM Breeds")
    suspend fun deleteAll()

    @Transaction
    suspend fun refreshBreedsData(items: List<Breeds>) {
        deleteAll()
        for (item in items) {
            insert(item)
        }
    }

}