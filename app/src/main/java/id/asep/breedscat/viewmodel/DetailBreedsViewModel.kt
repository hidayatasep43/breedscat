package id.asep.breedscat.viewmodel

import android.app.Application
import androidx.lifecycle.*
import id.asep.breedscat.data.model.Resource
import id.asep.breedscat.data.model.Status
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.data.source.repository.BreedsRepository
import kotlinx.coroutines.launch

/*
* Created by Asep Hidayat on 12/31/2020, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
class DetailBreedsViewModel(val breedsId: String, val breedsRepository: BreedsRepository) : ViewModel() {

    private var _breedsLiveData: LiveData<Resource<Breeds?>> = MutableLiveData()
    val breedsLiveData = MediatorLiveData<Resource<Breeds?>>()

    init {
        getBreeds()
    }

    fun getBreeds() {
        viewModelScope.launch {
            breedsLiveData.removeSource(_breedsLiveData)
            _breedsLiveData = breedsRepository.getBreeds(breedsId)
            breedsLiveData.addSource(_breedsLiveData) {
                breedsLiveData.value = it
            }
        }
    }

}