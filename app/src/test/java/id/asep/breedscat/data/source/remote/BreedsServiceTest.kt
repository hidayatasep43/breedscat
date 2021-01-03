package id.asep.breedscat.data.source.remote

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/*
* Created by Asep Hidayat on 1/3/2021, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/

/* Unit test for get data from api use retrofit
*  Unit test uset Mock WEbserver
*
*
*  */
class BreedsServiceTest {

    lateinit var breedsService: BreedsService
    lateinit var mockServer: MockWebServer

    @Before
    fun init() {
        this.configureMockServer()
        breedsService = BreedsServiceFactory.provideBreedService(
            BreedsServiceFactory.provideOkHttpClient(),
            mockServer.url("/").toString()
        )
    }

    @Test
    fun `fetch list breeds`() {
        mockHttpResponse(mockServer, getResponseStringBreedsList(), HttpURLConnection.HTTP_OK)
        val breedsListResponse = runBlocking {
            breedsService.getAllBreeds()
        }
        val breedsList = breedsListResponse.body()
        assertEquals(breedsListResponse.code(), HttpURLConnection.HTTP_OK)
        assertEquals(breedsList?.get(0)?.name, "Abyssinian")
        assertEquals(breedsList?.get(0)?.id, "abys")
        assertEquals(breedsList?.get(0)?.intelligence, 5)
    }

    @Test()
    fun `fetch list breeds and fail`() {
        mockHttpResponse(
            mockServer,
            getResponseStringBreedsList(),
            HttpURLConnection.HTTP_FORBIDDEN
        )
        val breedsListResponse = runBlocking {
            breedsService.getAllBreeds()
        }
        assertEquals(breedsListResponse.code(), HttpURLConnection.HTTP_FORBIDDEN)
    }

    @Test
    fun `fetch detail breeds`() {
        mockHttpResponse(mockServer, getResponseStringDetailBreeds(), HttpURLConnection.HTTP_OK)
        val detailBreedsResponse = runBlocking {
            breedsService.getBreeds("")
        }
        val detailBreeds = detailBreedsResponse.body()
        assertEquals(detailBreedsResponse.code(), HttpURLConnection.HTTP_OK)
        assertEquals(detailBreeds?.get(0)?.breeds?.get(0)?.name, "Abyssinian")
        assertEquals(detailBreeds?.get(0)?.breeds?.get(0)?.id, "abys")
        assertEquals(detailBreeds?.get(0)?.breeds?.get(0)?.intelligence, 5)
        assertEquals(detailBreeds?.get(0)?.url, "https://cdn2.thecatapi.com/images/unPP08xOZ.jpg")
    }

    @Test()
    fun `fetch detail breeds and fail`() {
        mockHttpResponse(
            mockServer,
            getResponseStringDetailBreeds(),
            HttpURLConnection.HTTP_BAD_REQUEST
        )
        val detailBreedsResponse = runBlocking {
            breedsService.getBreeds("")
        }
        assertEquals(detailBreedsResponse.code(), HttpURLConnection.HTTP_BAD_REQUEST)
    }


    @After
    fun tearDown() {
        this.stopMockServer()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(mockServer: MockWebServer, body: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(body)
        )

    fun getResponseStringBreedsList(): String =
        """
            [
                { 
                    "adaptability": 5,
                    "affection_level": 5,
                    "alt_names": "",
                    "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
                    "child_friendly": 3,
                    "country_code": "EG",
                    "country_codes": "EG",
                    "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                    "dog_friendly": 4,
                    "energy_level": 5,
                    "experimental": 0,
                    "grooming": 1,
                    "hairless": 0,
                    "health_issues": 2,
                    "hypoallergenic": 0,
                    "id": "abys",
                    "image": {
                      "height": 1445,
                      "id": "0XYvRd7oD",
                      "url": "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
                      "width": 1204
                    },
                    "indoor": 0,
                    "intelligence": 5,
                    "lap": 1,
                    "life_span": "14 - 15",
                    "name": "Abyssinian",
                    "natural": 1,
                    "origin": "Egypt",
                    "rare": 0,
                    "reference_image_id": "0XYvRd7oD",
                    "rex": 0,
                    "shedding_level": 2,
                    "short_legs": 0,
                    "social_needs": 5,
                    "stranger_friendly": 5,
                    "suppressed_tail": 0,
                    "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
                    "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
                    "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
                    "vocalisation": 1,
                    "weight": {
                      "imperial": "7  -  10",
                      "metric": "3 - 5"
                    },
                    "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
                }
            ]
        """

    fun getResponseStringDetailBreeds(): String =
        """
            [
                {
                    "breeds":[
                                {
                                    "weight":{
                                      "imperial":"7 - 10",
                                      "metric":"3 - 5"
                                    },
                                    "id":"abys",
                                    "name":"Abyssinian",
                                    "cfa_url":"http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
                                    "vetstreet_url":"http://www.vetstreet.com/cats/abyssinian",
                                    "vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
                                    "temperament":"Active, Energetic, Independent, Intelligent, Gentle",
                                    "origin":"Egypt",
                                    "country_codes":"EG",
                                    "country_code":"EG",
                                    "description":"The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                                    "life_span":"14 - 15",
                                    "indoor":0,
                                    "lap":1,
                                    "alt_names":"",
                                    "adaptability":5,
                                    "affection_level":5,
                                    "child_friendly":3,
                                    "dog_friendly":4,
                                    "energy_level":5,
                                    "grooming":1,
                                    "health_issues":2,
                                    "intelligence":5,
                                    "shedding_level":2,
                                    "social_needs":5,
                                    "stranger_friendly":5,
                                    "vocalisation":1,
                                    "experimental":0,
                                    "hairless":0,
                                    "natural":1,
                                    "rare":0,
                                    "rex":0,
                                    "suppressed_tail":0,
                                    "short_legs":0,
                                    "wikipedia_url":"https://en.wikipedia.org/wiki/Abyssinian_(cat)",
                                    "hypoallergenic":0,
                                    "reference_image_id":"0XYvRd7oD"
                                 }
                            ],
                        "id":"unPP08xOZ",
                        "url":"https://cdn2.thecatapi.com/images/unPP08xOZ.jpg",
                        "width":2136,
                        "height":2848
                }
            ]
    """

}