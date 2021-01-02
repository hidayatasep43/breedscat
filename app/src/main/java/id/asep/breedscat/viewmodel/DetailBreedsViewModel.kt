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
class DetailBreedsViewModel(val breeds: Breeds) : ViewModel() {

    private var _breedsLiveData = MutableLiveData<Breeds?>()
    val breedsLiveData: LiveData<Breeds?>
        get() = _breedsLiveData

    init {
        _breedsLiveData.value = breeds
    }

}