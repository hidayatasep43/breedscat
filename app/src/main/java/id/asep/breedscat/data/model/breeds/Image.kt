package id.asep.breedscat.data.model.breeds

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Image (
	@ColumnInfo(name = "imageId")
	@SerializedName("id") val id : String?,
	@SerializedName("height") val height : Int?,
	@SerializedName("url") val url : String?,
	@SerializedName("width") val width : Int?
)