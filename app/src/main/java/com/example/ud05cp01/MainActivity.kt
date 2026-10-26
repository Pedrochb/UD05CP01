package com.example.ud05cp01

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvHoy = findViewById<TextView>(R.id.tvHoy)
        val tvNaciste = findViewById<TextView>(R.id.tvNaciste)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnFecha = findViewById<Button>(R.id.btnFecha)

        val hoy = LocalDate.now()
        tvHoy.text = "Hoy es ${hoy.dayOfMonth}/${hoy.monthValue}/${hoy.year}"

        btnFecha.setOnClickListener {
            val dialogo = DatePickerDialog(
                this,
                { _, anio, mes, dia ->

                    val nacimiento = LocalDate.of(anio, mes + 1, dia)
                    tvNaciste.text = "Naciste el $dia/${mes + 1}/$anio"

                    val anios = ChronoUnit.YEARS.between(nacimiento, hoy)
                    val ultimoCumple = nacimiento.plusYears(anios)
                    val dias = ChronoUnit.DAYS.between(ultimoCumple, hoy)

                    tvResultado.text = "Tienes $anios años y $dias días"
                },
                hoy.year, hoy.monthValue - 1, hoy.dayOfMonth
            )
            dialogo.datePicker.maxDate = System.currentTimeMillis()
            dialogo.show()
        }
    }
}