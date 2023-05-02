package com.example.a28practical_work

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a28practical_work.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter: Byte = 0
        set(value) {
            if (value >= 0) //запрещаем счётчик меньше нуля
                field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ClearCounter()

        binding.buttonPlus.setOnClickListener { //слушатель кнопки "+"
            counter++
            Setting()
        }

        binding.buttonMinus.setOnClickListener {//слушатель кнопки "-"
            counter--
            Setting()
        }

        binding.buttonClear.setOnClickListener {//слушатель кнопки "Сброс"
            ClearCounter()
        }
    }

    fun ClearCounter() {
        counter = 0
        binding.buttonClear.visibility = View.INVISIBLE //кнопка "Сброс" изначально невидима
        Setting()
    }

    fun Setting() {
        when (counter.toInt()) {
            0 -> {
                binding.textView1.setTextColor(Color.GREEN)
                binding.buttonMinus.isEnabled = false//кнопка "минус" неактивна
                binding.textView1.text = "Все места свободны"
            }
            in 1..49 -> {
                binding.textView1.setTextColor(Color.parseColor("#19199E"))
                binding.buttonClear.visibility = View.INVISIBLE
                binding.buttonMinus.isEnabled = true //кнопка "минус" активна
                binding.textView1.text = "Осталось мест: ${50-counter}"
            }
            else -> {
                binding.textView1.setTextColor(Color.RED)
                binding.buttonClear.visibility = View.VISIBLE
                binding.buttonMinus.isEnabled = true
                binding.textView1.text = "Пассажиров слишком много!"
            }
        }
        binding.textViewCounter.text = counter.toString() //выводим значение счётчика
    }

}