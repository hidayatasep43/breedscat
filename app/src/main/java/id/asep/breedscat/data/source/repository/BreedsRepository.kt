package id.asep.breedscat.data.source.repository

import androidx.lifecycle.LiveData
import id.asep.breedscat.data.model.Resource
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.data.source.NetworkBoundResource
import id.asep.breedscat.data.source.local.BreedsDAO
import id.asep.breedscat.data.source.remote.BreedsService
import retrofit2.Response

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class BreedsRepository (private val breedsDAO: BreedsDAO, private val breedsService: BreedsService) {

    suspend fun getBreeds(): LiveData<Resource<List<Breeds>>> {
        return object : NetworkBoundResource<List<Breeds>, List<Breeds>>() {

            override fun processResponse(response: List<Breeds>): List<Breeds> {
                return response
            }

            override suspend fun saveCallResult(item: List<Breeds>) {
                breedsDAO.refreshBreedsData(item)
            }

            override suspend fun createCall(): Response<List<Breeds>> {
                return breedsService.getBreeds()
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override suspend fun loadFromDb(): List<Breeds> {
                return breedsDAO.getAllBreeds()
            }

        }.build().asLiveData()
    }

}