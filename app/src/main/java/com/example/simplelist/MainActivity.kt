package com.example.simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var numberType: RadioGroup
    private lateinit var numberList: ListView
    private lateinit var showBtn: Button
    private lateinit var errorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputNumber = findViewById(R.id.input_number)
        numberType = findViewById(R.id.radioNumberType)
        numberList = findViewById(R.id.numberList)
        showBtn = findViewById(R.id.showBtn)
        errorMessage = findViewById(R.id.errorMessage)

        showBtn.setOnClickListener {
            val input = inputNumber.text.toString()
            if (input.isNotEmpty() && input.toIntOrNull() != null) {
                val n = input.toInt()
                if (n > 0) {
                    val selectedTypeId = numberType.checkedRadioButtonId
                    val numbers = when (selectedTypeId) {
                        R.id.radioOddNumber -> getOddNumberList(n)
                        R.id.radioEvenNumber -> getEvenNumberList(n)
                        R.id.radioSquareNumber -> getSquareNumberList(n)
                        else -> emptyList()
                    }

                    val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, numbers)
                    numberList.adapter = adapter
                    errorMessage.text = ""
                } else {
                    errorMessage.text = "Please enter a positive number."
                }
            } else {
                errorMessage.text = "Invalid input. Please enter a valid number."
            }
        }
    }

    private fun getOddNumberList(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1 until n) {
            if (i % 2 != 0) {
                oddNumbers.add(i)
            }
        }
        return oddNumbers
    }

    private fun getEvenNumberList(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 1 until n) {
            if (i % 2 == 0) {
                evenNumbers.add(i)
            }
        }
        return evenNumbers
    }

    private fun getSquareNumberList(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 1
        while (i * i < n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }

}