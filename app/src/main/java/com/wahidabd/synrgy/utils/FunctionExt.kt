package com.wahidabd.synrgy.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wahidabd.synrgy.R
import java.text.NumberFormat
import java.util.Locale


/**
 * Created by Wahid on 9/30/2023.
 * Github github.com/wahidabd.
 */

val imageUrl = "https://image.tmdb.org/t/p/w500"
val localeIndonesia = Locale("in", "ID")

fun EditText.toCurrencyTextWatcher() {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            s?.let { string ->
                if (string.isNotEmpty()) {
                    this@toCurrencyTextWatcher.removeTextChangedListener(this)

                    val price = fromCurrencyStringToDouble(string.toString())
                    val priceString = price.toCurrencyValue()

                    this@toCurrencyTextWatcher.setText(priceString)
                    this@toCurrencyTextWatcher.setSelection(priceString.length)
                    this@toCurrencyTextWatcher.addTextChangedListener(this)
                }
            }
        }

    })
}

private fun Double.toCurrencyValue(): String {
    val formatted = NumberFormat.getCurrencyInstance(localeIndonesia)
    formatted.maximumFractionDigits = 0
    val format = formatted.format(this)
    return getNormalizedValue(format)
}

private fun fromCurrencyStringToDouble(currencyString: String): Double =
    currencyString.replace(".", "").toDouble()


private fun getNormalizedValue(value: String): String =
    value.substring(2, value.length)

fun Double.formatCurrencyRp(): String {
    val format = NumberFormat.getCurrencyInstance(localeIndonesia)
    return format.format(this)
}

fun String.getPercentage(): Double {
    return when {
        this.contains("20") -> 0.20
        this.contains("18") -> 0.18
        this.contains("15") -> 0.15
        else -> 0.0
    }
}

fun ImageView.loadImageUrl(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.img_logo)
        .into(this)

fun Fragment.showToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.navigateArgs(nav: NavDirections) =
    findNavController().navigate(nav)
