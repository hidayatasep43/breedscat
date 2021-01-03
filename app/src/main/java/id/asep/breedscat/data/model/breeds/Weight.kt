package id.asep.breedscat.data.model.breeds

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight (
	@SerializedName("imperial") val imperial : String?,
	@SerializedName("metric") val metric : String?
): Parcelable