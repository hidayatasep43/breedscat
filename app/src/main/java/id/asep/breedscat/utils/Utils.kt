package id.asep.breedscat.utils

import android.content.Context
import android.content.res.Resources

/*
* Created by Asep Hidayat on 1/2/2021, 
* for Rolling Glory
* you can contact me at hidayatasep@rollingglory.com
*/
object Utils {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}