package id.asep.breedscat.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.asep.breedscat.MyApplication
import id.asep.breedscat.R
import id.asep.breedscat.data.model.breeds.Breeds
import id.asep.breedscat.databinding.ActivityDetailBreedsBinding
import id.asep.breedscat.databinding.ActivityListBreedsBinding
import id.asep.breedscat.utils.ViewModelUtil
import id.asep.breedscat.viewmodel.DetailBreedsViewModel
import id.asep.breedscat.viewmodel.ListBreedsViewModel

class DetailBreedsActivity : AppCompatActivity() {

    private lateinit var detailBreedsViewModel: DetailBreedsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init data binding and view model
        val binding: ActivityDetailBreedsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_breeds)
        binding.lifecycleOwner = this

        val breeds = intent.getParcelableExtra<Breeds>(BREEDS_EXTRA)
        detailBreedsViewModel = ViewModelProvider(this,
            ViewModelUtil.viewModelFactory {
                DetailBreedsViewModel(breeds)
            }
        ).get(DetailBreedsViewModel::class.java)
        binding.detailBreedsViewModel = detailBreedsViewModel

        binding.btnBack.setOnClickListener {
            finish()
        }

    }


    companion object {

        val BREEDS_EXTRA = "breeds"

        fun getIntent(context: Context, breeds: Breeds): Intent {
            val intent = Intent(context, DetailBreedsActivity::class.java)
            intent.putExtra(BREEDS_EXTRA, breeds)
            return intent
        }

    }

}