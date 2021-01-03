package id.asep.breedscat.data.model.detailbreeds

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class BreedsDetailBreeds (

	@SerializedName("weight") val weightDetailBreeds : WeightDetailBreeds?,
	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String?,
	@SerializedName("cfa_url") val cfa_url : String?,
	@SerializedName("vetstreet_url") val vetstreet_url : String?,
	@SerializedName("vcahospitals_url") val vcahospitals_url : String?,
	@SerializedName("temperament") val temperament : String?,
	@SerializedName("origin") val origin : String?,
	@SerializedName("country_codes") val country_codes : String?,
	@SerializedName("country_code") val country_code : String?,
	@SerializedName("description") val description : String?,
	@SerializedName("life_span") val life_span : String?,
	@SerializedName("indoor") val indoor : Int?,
	@SerializedName("lap") val lap : Int?,
	@SerializedName("alt_names") val alt_names : String?,
	@SerializedName("adaptability") val adaptability : Int?,
	@SerializedName("affection_level") val affection_level : Int?,
	@SerializedName("child_friendly") val child_friendly : Int?,
	@SerializedName("dog_friendly") val dog_friendly : Int?,
	@SerializedName("energy_level") val energy_level : Int?,
	@SerializedName("grooming") val grooming : Int?,
	@SerializedName("health_issues") val health_issues : Int?,
	@SerializedName("intelligence") val intelligence : Int?,
	@SerializedName("shedding_level") val shedding_level : Int?,
	@SerializedName("social_needs") val social_needs : Int?,
	@SerializedName("stranger_friendly") val stranger_friendly : Int?,
	@SerializedName("vocalisation") val vocalisation : Int?,
	@SerializedName("experimental") val experimental : Int?,
	@SerializedName("hairless") val hairless : Int?,
	@SerializedName("natural") val natural : Int?,
	@SerializedName("rare") val rare : Int?,
	@SerializedName("rex") val rex : Int?,
	@SerializedName("suppressed_tail") val suppressed_tail : Int?,
	@SerializedName("short_legs") val short_legs : Int?,
	@SerializedName("wikipedia_url") val wikipedia_url : String?,
	@SerializedName("hypoallergenic") val hypoallergenic : Int?,
	@SerializedName("reference_image_id") val reference_image_id : String?
)