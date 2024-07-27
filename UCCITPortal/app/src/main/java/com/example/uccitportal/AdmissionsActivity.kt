package com.example.uccitportal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.uccitportal.databinding.ActivityAdmissionsBinding

// The class for the admissions activity
class AdmissionsActivity : AppCompatActivity() {

    // Variable for the binding
    private lateinit var binding: ActivityAdmissionsBinding

    // The function that is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setting up the view binding
        binding = ActivityAdmissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting the Apply Now link to open in a browser
        binding.applyLink.setOnClickListener {
            // Creating an intent to open the link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ucc.edu.jm/apply/"))
            startActivity(intent)
        }

        // Setting a listener for when the back button is pressed
        binding.backButton.setOnClickListener {
            // Closing this activity and returning to the previous one
            finish()
        }
    }
}

