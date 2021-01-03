package id.asep.breedscat.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import id.asep.breedscat.MyApplication
import id.asep.breedscat.R
import id.asep.breedscat.data.model.Status
import id.asep.breedscat.databinding.ActivityDetailBreedsBinding
import id.asep.breedscat.utils.ViewModelUtil
import id.asep.breedscat.viewmodel.DetailBreedsViewModel

class DetailBreedsActivity : AppCompatActivity() {

    private lateinit var detailBreedsViewModel: DetailBreedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init data binding and view model
        val binding: ActivityDetailBreedsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_breeds)
        binding.lifecycleOwner = this

        val breedsId = intent.getStringExtra(BREEDS_EXTRA)
        detailBreedsViewModel = ViewModelProvider(this,
            ViewModelUtil.viewModelFactory {
                DetailBreedsViewModel(
                    breedsId,
                    (application as MyApplication).appContainer.breedsRepository)
            }
        ).get(DetailBreedsViewModel::class.java)
        binding.detailBreedsViewModel = detailBreedsViewModel

        detailBreedsViewModel.breedsLiveData.observe(this, { resourcesBreedsResponse ->
            if (resourcesBreedsResponse.status == Status.ERROR) {
                Snackbar.make(binding.root, resourcesBreedsResponse.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        })

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.swipeRefresh.setOnRefreshListener {
            detailBreedsViewModel.getBreeds()
        }

    }


    companion object {

        val BREEDS_EXTRA = "breeds"

        fun getIntent(context: Context, breedsId: String): Intent {
            val intent = Intent(context, DetailBreedsActivity::class.java)
            intent.putExtra(BREEDS_EXTRA, breedsId)
            return intent
        }

    }

}