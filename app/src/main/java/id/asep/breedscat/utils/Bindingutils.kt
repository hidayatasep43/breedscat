package id.asep.breedscat.utils

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
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



