package id.asep.breedscat.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import id.asep.breedscat.MyApplication
import id.asep.breedscat.R
import id.asep.breedscat.data.model.Status
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.databinding.ActivityListBreedsBinding
import id.asep.breedscat.utils.ViewModelUtil
import id.asep.breedscat.viewmodel.ListBreedsViewModel

class ListBreedsActivity : AppCompatActivity(), BreedsAdapter.BreedsItemClickListener {

    private lateinit var listBreedsViewModel: ListBreedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init data binding and view model
        val binding: ActivityListBreedsBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_breeds)
        binding.lifecycleOwner = this

        listBreedsViewModel = ViewModelProvider(this,
            ViewModelUtil.viewModelFactory {
                ListBreedsViewModel((application as MyApplication).appContainer.breedsRepository)
            }
        ).get(ListBreedsViewModel::class.java)
        binding.listBreedsViewModel = listBreedsViewModel

        //set adapter
        val adapter = BreedsAdapter(this)
        binding.breedList.adapter = adapter

        //observe data
        listBreedsViewModel.breedsLiveData.observe(this, Observer { resourcesBreedsResponse ->
            if (resourcesBreedsResponse.status == Status.ERROR || resourcesBreedsResponse.status == Status.EMPTY || resourcesBreedsResponse.status == Status.SUCCESS) {
                adapter.submitList(resourcesBreedsResponse.data)
            }
            if (resourcesBreedsResponse.status == Status.ERROR) {
                Snackbar.make(binding.root, resourcesBreedsResponse.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            listBreedsViewModel.getListBreeds()
        }

    }

    override fun onBreedsItemClicked(breeds: Breeds) {
        val intent = DetailBreedsActivity.getIntent(this, breeds.id)
        startActivity(intent)
    }

}