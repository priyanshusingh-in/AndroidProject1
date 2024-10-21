package com.fullstackalchemists.intent

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var isEnglish = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var nameInput = findViewById<EditText>(R.id.name_input)
        var displayNameBtn = findViewById<Button>(R.id.display_name_btn)
        var numberInput = findViewById<EditText>(R.id.number_input)
        var dialNumberBtn = findViewById<Button>(R.id.dial_number_btn)
        var openCameraBtn = findViewById<Button>(R.id.open_camera_btn)
        var googleSearchBtn = findViewById<Button>(R.id.google_search_btn)
        var testSnackBarBtn = findViewById<Button>(R.id.test_snack_bar_btn)
        var welcomeText = findViewById<TextView>(R.id.welcome_text)
        welcomeText.text = "Hello!"
        var testAppLocalizationBtn = findViewById<Button>(R.id.test_app_localization_btn)

        displayNameBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name", nameInput.text.toString())
            startActivity(intent)
        }

        dialNumberBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+numberInput.text.toString())
            startActivity(intent)
        }

        openCameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        googleSearchBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }

        testSnackBarBtn.setOnClickListener {
            val snackbar = Snackbar.make(it, "Test SnackBar", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

        testAppLocalizationBtn.setOnClickListener {

        }
    }

    fun changeLanguage(view: View) {
        if(isEnglish) {
            setLocale("es")
        } else {
            setLocale("en")
        }
        isEnglish = !isEnglish
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        resources.updateConfiguration(config,resources.displayMetrics)
        recreate()
    }

}