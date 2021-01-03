package id.asep.breedscat.data.source.remote

import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.data.model.detailbreeds.DetailBreeds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
interface BreedsService {

    @GET("/v1/breeds")
    suspend fun getAllBreeds(): Response<List<Breeds>>

    @GET("/v1/images/search")
    suspend fun getBreeds(
        @Query("breed_ids") breedsId: String
    ): Response<List<DetailBreeds>>

}