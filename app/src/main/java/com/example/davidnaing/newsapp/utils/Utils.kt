package com.example.davidnaing.newsapp.utils

import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.*


/**
 * Created by yepyaesonetun on 10/8/18.
 **/
class Utils {
    companion object {
        fun getCommaSeparatedAmount(amount: Int): String {
            val numberFormat = NumberFormat.getNumberInstance(Locale.US)
            return numberFormat.format(amount)
        }

        fun getFormattedDate(isoDateTime: String): String {

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val output = SimpleDateFormat("d-MMM-yyyy, EEEE, hh:mm aaa")
            var d: Date? = null
            try {
                d = sdf.parse(isoDateTime)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return output.format(d)
        }

        fun encodeImage(bm: Bitmap): String {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val b = baos.toByteArray()

            return android.util.Base64.encodeToString(b, Base64.DEFAULT)
        }
    }
}