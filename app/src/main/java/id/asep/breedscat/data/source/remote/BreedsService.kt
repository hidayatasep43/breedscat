package id.asep.breedscat.data.source.remote

import id.asep.breedscat.data.model.breeds.Breeds
import retrofit2.Response
import retrofit2.http.GET

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
interface BreedsService {

    @GET("/v1/breeds")
    suspend fun getBreeds(): Response<List<Breeds>>

}