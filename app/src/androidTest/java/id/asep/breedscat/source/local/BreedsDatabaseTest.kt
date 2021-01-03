package id.asep.breedscat.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.data.model.breeds.Image
import id.asep.breedscat.data.model.breeds.Weight
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/*
* Created by Asep Hidayat on 1/3/2021, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
/*
* Instrument test for room
*
* */
@RunWith(AndroidJUnit4::class)
class BreedsDatabaseTest {

    private lateinit var breedsDAO: BreedsDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        breedsDAO = db.breedsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /*
    * Test check for insert breeds and get breed in room database
    * */
    @Test
    @Throws(Exception::class)
    fun addBreedsDataAndRead() {
        runBlocking {
            breedsDAO.insert(getBreedsDummy())
        }
        val breeds = runBlocking {
            breedsDAO.getBreeds("aege")
        }
        if (breeds != null) {
            Assert.assertEquals(breeds.name, "Aegean")
            Assert.assertEquals(breeds.origin, "Greece")
            Assert.assertEquals(breeds.adaptability, 5)
            Assert.assertEquals(breeds.image?.url, "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg")
        }
    }

    fun getBreedsDummy(): Breeds {
        val image = Image("ozEvzdVM-", 800, "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",1200)
        val weight = Weight("7 - 10", "3 - 5")
        return Breeds(
            "aege",
            5,
            4,
            "",
            "",
            4,
            "GR",
            "GR",
            "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
            4,
            3,
            3,
            0,
            0,
            0,
            1,
            0,
            3,
            0,
            "9 - 12",
            "Aegean",
            null,
            "Greece",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            image,
            weight,
        )
    }

}