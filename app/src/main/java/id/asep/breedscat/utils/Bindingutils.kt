package id.asep.breedscat.utils

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import id.asep.breedscat.R
import id.asep.breedscat.data.model.Resource
import id.asep.breedscat.data.model.Status
import id.asep.breedscat.data.model.breeds.Breeds
import java.util.zip.GZIPOutputStream

/*
* Created by Asep Hidayat on 1/1/2021, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
@BindingAdapter("setImageBreeds")
fun setImageBreeds(imageView: ImageView, breeds: Breeds) {
    if (breeds.image != null && breeds.image.url != null && !breeds.image.url.isEmpty()) {
        Picasso.get()
            .load(breeds.image.url)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .resize(Utils.dpToPx(60), Utils.dpToPx(60))
            .transform(RoundedCornersTransformation(Utils.dpToPx(4), 0))
            .into(imageView)
    } else {
        imageView.setImageResource(R.drawable.ic_placeholder)
    }
}

@BindingAdapter("showEmptyView")
fun showEmptyView(linearLayout: LinearLayout, resource: MediatorLiveData<Resource<List<Breeds>>>) {
    if ((resource.value?.status == Status.ERROR || resource.value?.status == Status.SUCCESS || resource.value?.status == Status.EMPTY)
        && resource.value?.data.isNullOrEmpty()) {
        linearLayout.visibility = View.VISIBLE
    } else {
        linearLayout.visibility = View.GONE
    }
}

@BindingAdapter("showRefresh")
fun showRefresh(swipeRefreshLayout: SwipeRefreshLayout, resource: MediatorLiveData<Resource<List<Breeds>>>) {
    if (resource.value?.status == Status.LOADING) {
        swipeRefreshLayout.isRefreshing = true
    } else {
        swipeRefreshLayout.isRefreshing = false
    }
}

@BindingAdapter("setImageDetailBreeds")
fun setImageDetailBreeds(imageView: ImageView, breedsLiveData: MediatorLiveData<Resource<Breeds>>) {
    if ((breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY)
        && breedsLiveData.value!!.data != null) {
        if (breedsLiveData.value?.data?.image != null && !breedsLiveData.value?.data!!.image!!.url.isNullOrEmpty()) {
            Picasso.get()
                .load(breedsLiveData.value?.data!!.image!!.url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.ic_placeholder)
        }
    }
}

@BindingAdapter("showDetailView")
fun showDetailView(view: View, breedsLiveData: MediatorLiveData<Resource<Breeds?>>) {
    if (breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY) {
        if (breedsLiveData.value!!.data != null) {
            if (view.visibility == View.GONE) {
                view.visibility = View.VISIBLE
            }
        }
    }
}

@BindingAdapter("showRefreshDetailBreeds")
fun showRefreshDetailBreeds(swipeRefreshLayout: SwipeRefreshLayout, resource:MediatorLiveData<Resource<Breeds?>>) {
    if (resource.value?.status == Status.LOADING) {
        swipeRefreshLayout.isRefreshing = true
    } else {
        swipeRefreshLayout.isRefreshing = false
    }
}

@BindingAdapter("setTitleDetailBreeds")
fun setTitleDetailBreeds(textView: TextView, breedsLiveData:MediatorLiveData<Resource<Breeds?>>) {
    if (breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY) {
        if (breedsLiveData.value!!.data != null) {
            textView.text = breedsLiveData.value!!.data!!.name
        }
    }
}

@BindingAdapter("setDescDetailBreeds")
fun setDescDetailBreeds(textView: TextView, breedsLiveData:MediatorLiveData<Resource<Breeds?>>) {
    if (breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY) {
        if (breedsLiveData.value!!.data != null) {
            textView.text = breedsLiveData.value!!.data!!.description
        }
    }
}


@BindingAdapter("setOriginDetailBreeds")
fun setOriginDetailBreeds(textView: TextView, breedsLiveData:MediatorLiveData<Resource<Breeds?>>) {
    if (breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY) {
        if (breedsLiveData.value!!.data != null) {
            textView.text = breedsLiveData.value!!.data!!.origin
        }
    }
}

@BindingAdapter("setTempramentDetailBreeds")
fun setTempramentDetailBreeds(textView: TextView, breedsLiveData:MediatorLiveData<Resource<Breeds?>>) {
    if (breedsLiveData.value?.status == Status.ERROR || breedsLiveData.value?.status == Status.SUCCESS || breedsLiveData.value?.status == Status.EMPTY) {
        if (breedsLiveData.value!!.data != null) {
            textView.text = breedsLiveData.value!!.data!!.temperament
        }
    }
}



