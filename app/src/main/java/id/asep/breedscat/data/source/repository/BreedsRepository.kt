package id.asep.breedscat.data.source.repository

import androidx.lifecycle.LiveData
import id.asep.breedscat.data.model.Resource
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.data.model.breeds.Image
import id.asep.breedscat.data.model.breeds.Weight
import id.asep.breedscat.data.model.detailbreeds.DetailBreeds
import id.asep.breedscat.data.source.NetworkBoundResource
import id.asep.breedscat.data.source.local.BreedsDAO
import id.asep.breedscat.data.source.remote.BreedsService
import retrofit2.Response

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class BreedsRepository(private val breedsDAO: BreedsDAO, private val breedsService: BreedsService) {

    suspend fun getAllBreeds(): LiveData<Resource<List<Breeds>>> {
        return object : NetworkBoundResource<List<Breeds>, List<Breeds>>() {

            override fun processResponse(response: List<Breeds>): List<Breeds> {
                return response
            }

            override suspend fun saveCallResult(item: List<Breeds>) {
                breedsDAO.refreshBreedsData(item)
            }

            override suspend fun createCall(): Response<List<Breeds>> {
                return breedsService.getAllBreeds()
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override suspend fun loadFromDb(): List<Breeds> {
                return breedsDAO.getAllBreeds()
            }

        }.build().asLiveData()
    }

    suspend fun getBreeds(breedsId: String): LiveData<Resource<Breeds?>> {
        return object : NetworkBoundResource<Breeds?, List<DetailBreeds>>() {
            override fun processResponse(response: List<DetailBreeds>): Breeds? {
                if (!response.isNullOrEmpty()) {
                    val detailBreeds = response[0]
                    if (!detailBreeds.breeds.isNullOrEmpty()) {
                        //for image
                        val imageBreeds =
                            Image(null, detailBreeds.height, detailBreeds.url, detailBreeds.width)
                        //for weight
                        var weight: Weight? = null
                        val breedsDetailBreeds = detailBreeds.breeds[0]
                        if (breedsDetailBreeds.weightDetailBreeds != null) {
                            weight = Weight(
                                breedsDetailBreeds.weightDetailBreeds.imperial,
                                breedsDetailBreeds.weightDetailBreeds.metric
                            )
                        }
                        return Breeds(
                            breedsDetailBreeds.id,
                            breedsDetailBreeds.adaptability,
                            breedsDetailBreeds.affection_level,
                            breedsDetailBreeds.alt_names,
                            breedsDetailBreeds.cfa_url,
                            breedsDetailBreeds.child_friendly,
                            breedsDetailBreeds.country_code,
                            breedsDetailBreeds.country_codes,
                            breedsDetailBreeds.description,
                            breedsDetailBreeds.dog_friendly,
                            breedsDetailBreeds.energy_level,
                            breedsDetailBreeds.experimental,
                            breedsDetailBreeds.grooming,
                            breedsDetailBreeds.hairless,
                            breedsDetailBreeds.health_issues,
                            breedsDetailBreeds.hypoallergenic,
                            breedsDetailBreeds.indoor,
                            breedsDetailBreeds.intelligence,
                            breedsDetailBreeds.lap,
                            breedsDetailBreeds.life_span,
                            breedsDetailBreeds.name,
                            breedsDetailBreeds.natural,
                            breedsDetailBreeds.origin,
                            breedsDetailBreeds.rare,
                            breedsDetailBreeds.reference_image_id,
                            breedsDetailBreeds.rex,
                            breedsDetailBreeds.shedding_level,
                            breedsDetailBreeds.short_legs,
                            breedsDetailBreeds.social_needs,
                            breedsDetailBreeds.stranger_friendly,
                            breedsDetailBreeds.suppressed_tail,
                            breedsDetailBreeds.temperament,
                            breedsDetailBreeds.vcahospitals_url,
                            breedsDetailBreeds.vetstreet_url,
                            breedsDetailBreeds.vocalisation,
                            breedsDetailBreeds.wikipedia_url,
                            imageBreeds,
                            weight
                        )
                    } else {
                        return null
                    }
                }
                return null
            }

            override suspend fun saveCallResult(item: Breeds?) {
                if (item != null) {
                    breedsDAO.insert(item)
                }
            }

            override suspend fun createCall(): Response<List<DetailBreeds>> {
                return breedsService.getBreeds(breedsId)
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override suspend fun loadFromDb(): Breeds? {
                return breedsDAO.getBreeds(breedsId)
            }
        }.build().asLiveData()
    }


}