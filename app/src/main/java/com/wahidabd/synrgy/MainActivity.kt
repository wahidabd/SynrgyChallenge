package com.wahidabd.synrgy

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.wahidabd.synrgy.databinding.ActivityMainBinding
import com.wahidabd.synrgy.utils.formatCurrencyRp
import com.wahidabd.synrgy.utils.getPercentage
import com.wahidabd.synrgy.utils.toCurrencyTextWatcher
import java.math.BigDecimal
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var percentage = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleSetSelectionRadioButton()

        binding.edtInput.toCurrencyTextWatcher()
        binding.btnCalculate.setOnClickListener { handleCalculate() }
    }

    private fun handleCalculate() = with(binding) {
        val input = edtInput.text?.toString()
        val doubleValue = input?.replace(".", "")?.toDouble() ?: 0.0

        val result =
            if (switchButton.isChecked) (doubleValue + (doubleValue * percentage)).roundToInt()
                .toDouble()
            else doubleValue + (doubleValue * percentage)
        tvResult.text = result.formatCurrencyRp()
    }

    private fun handleSetSelectionRadioButton() {
        binding.rg.setOnCheckedChangeListener { _, id ->
            val selected = findViewById<RadioButton>(id)
            percentage = selected.text.toString().getPercentage()
        }
    }

}