package com.example.laba6// Ваша назва пакету

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.laba6.R


class MainActivity : AppCompatActivity() {

    // Змінні для UI елементів
    private lateinit var btnMale: LinearLayout
    private lateinit var btnFemale: LinearLayout
    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var spinnerAge: Spinner

    // Змінна для збереження обраної статі (true - чоловік, false - жінка, null - не обрано)
    private var isMaleSelected: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Знаходимо елементи
        btnMale = findViewById(R.id.btnMale)
        btnFemale = findViewById(R.id.btnFemale)
        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)
        spinnerAge = findViewById(R.id.spinnerAge)

        // 2. Налаштовуємо Spinner (список віку від 1 до 100)
        val ageList = (1..100).toList().map { it.toString() }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ageList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAge.adapter = adapter
        spinnerAge.setSelection(24) // Встановити 25 років як дефолт (індекс 24)

        // 3. Обробка натискання на кнопки статі
        btnMale.setOnClickListener {
            isMaleSelected = true
            updateGenderColors()
        }

        btnFemale.setOnClickListener {
            isMaleSelected = false
            updateGenderColors()
        }

        // Встановлюємо початкові кольори
        updateGenderColors()

        // 4. Логіка кнопки "Розрахувати"
        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    // Функція зміни кольору кнопок
    private fun updateGenderColors() {
        val orangePrimary = ContextCompat.getColor(this, R.color.orange_primary)
        val orangeDark = ContextCompat.getColor(this, R.color.orange_dark)

        if (isMaleSelected) {
            // Чоловік активний (темний), Жінка неактивна (світла)
            btnMale.setBackgroundColor(orangeDark)
            btnFemale.setBackgroundColor(orangePrimary)
        } else {
            // Жінка активна (темна), Чоловік неактивний (світлий)
            btnFemale.setBackgroundColor(orangeDark)
            btnMale.setBackgroundColor(orangePrimary)
        }
    }

    // Функція розрахунку
    private fun calculateBMI() {
        val heightStr = etHeight.text.toString()
        val weightStr = etWeight.text.toString()

        // Перевірка на порожні поля
        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show()
            return
        }

        val heightCm = heightStr.toFloat()
        val weight = weightStr.toFloat()

        // Конвертуємо см в метри
        val heightM = heightCm / 100

        // Формула
        val bmi = weight / (heightM * heightM)

        // Визначення результату
        val resultText = when {
            bmi < 18.5 -> "Недостатня вага"
            bmi < 24.9 -> "Нормальна вага"
            bmi < 29.9 -> "Надмірна вага"
            else -> "Ожиріння"
        }

        // Форматування виводу (2 знаки після коми)
        val formattedBMI = String.format("%.2f", bmi)

        tvResult.text = "BMI: $formattedBMI\n$resultText"
    }
}