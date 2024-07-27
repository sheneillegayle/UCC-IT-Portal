package com.example.uccitportal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.example.uccitportal.databinding.ActivityCoursesBinding
import android.content.Intent
import com.google.firebase.FirebaseApp
import android.widget.Toast

// The class for the courses activity of the app
class CoursesActivity : AppCompatActivity() {

    // Variable for the view binding
    private lateinit var binding: ActivityCoursesBinding

    // declaring a variable for the firestore database
    private lateinit var firestoreDB: FirebaseFirestore

    // declaring a variable for the recycler view adapter
    private lateinit var courseAdapter: CourseAdapter

    // The on create method for when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inflating the layout with view binding
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the firestore database instance
        firestoreDB = FirebaseFirestore.getInstance()

        // Initializing the recycler view adapter with an empty list of courses, and a callback action for the course details
        courseAdapter = CourseAdapter(mutableListOf(), this::courseDetails)

        // Setting the layout manager and adapter for the recycler view
        binding.recyclerViewCourses.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCourses.adapter = courseAdapter

        // Setting the listener for when the back button is pressed
        binding.backButton.setOnClickListener {
            // Returning to the main activity
            finish()
        }

        // getting the course data from the firestore database
        fetchCourses()
    }

    // Creating a method to get the coursedata from the database
    private fun fetchCourses() {
        // Accessing the courses collection in the firestore
        firestoreDB.collection("courses")
            // Using a query to get all the documents in the collection
            .get()
            .addOnSuccessListener { result ->
                // Initializing a list to hold the courses
                val courseList = mutableListOf<Course>()
                // Looping through each item retreived from the database
                for (document in result) {
                    // Getting the ID
                    val id = document.id
                    // Getting the code, name, credits, prerequisites, and description
                    val code = document.getString("code") ?: ""
                    val name = document.getString("name") ?: ""
                    val credits = document.getLong("credits")?.toInt() ?: 0
                    val prerequisites = document.getString("prerequisites") ?: ""
                    val description = document.getString("Description") ?: ""

                    // Creating a course object
                    val course = Course(id, code, name, credits, prerequisites, description)
                    // Adding the course to the list
                    courseList.add(course)
                }

                // Updating the recycler view adapter with the courses
                courseAdapter.updateCourses(courseList)
            }
            .addOnFailureListener { e ->
                // Handling failure when getting the data from the firestore
                Toast.makeText(this, "Failed to fetch courses: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    // Creating a method to handle viewing course details
    private fun courseDetails(course: Course) {
        // Creating an intent to go to the activity to view the details
        val intent = Intent(this, CourseDetailsActivity::class.java)
        // Passing over the course ID
        intent.putExtra("courseId", course.id)
        startActivity(intent)
    }
}
