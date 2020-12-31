package id.asep.breedscat.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.asep.breedscat.MyApplication
import id.asep.breedscat.R
import id.asep.breedscat.data.model.Status
import id.asep.breedscat.utils.ViewModelUtil
import id.asep.breedscat.viewmodel.ListBreedsViewModel
import timber.log.Timber

class ListBreedsActivity : AppCompatActivity() {

    private lateinit var listBreedsViewModel: ListBreedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_breeds)

        listBreedsViewModel = ViewModelProvider(this,
            ViewModelUtil.viewModelFactory {
                ListBreedsViewModel((application as MyApplication).appContainer.breedsRepository)
            }
        ).get(ListBreedsViewModel::class.java)

        listBreedsViewModel.breedsLiveData.observe(this, Observer { resourcesBreedsResponse ->
            if (resourcesBreedsResponse.status == Status.LOADING) {
                Timber.d("LOADING")
            } else if (resourcesBreedsResponse.status == Status.SUCCESS) {
                Timber.d("SUCCESS")
            } else if (resourcesBreedsResponse.status == Status.ERROR){
                Timber.d("ERROR")
            }
        })

        listBreedsViewModel.getBreeds()
    }


}