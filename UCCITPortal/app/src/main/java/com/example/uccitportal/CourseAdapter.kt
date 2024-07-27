package com.example.uccitportal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uccitportal.databinding.ItemCourseBinding

// Creating an adapter class for the recycler view to display course data
class CourseAdapter(
    // The list of courses to display
    private var courses: MutableList<Course>,
    
    // The function that will be called to view course details
    private val courseDetails: (Course) -> Unit
    
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    // Method that is called when the recycler view needs a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        // Inflate and create a new view holder using the binding class
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    // Method that is called to display data at a specific position
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        // Getting the course at the specific position and binding it to the view holder
        val course = courses[position]
        holder.bind(course)
    }

    // Method that returns the total number of items in the dataset
    override fun getItemCount() = courses.size

    // Function to update the list of courses and refresh the display
    fun updateCourses(newCourses: List<Course>) {
        // Clearing the current list 
        courses.clear()
        // Adding the new list of courses
        courses.addAll(newCourses)
        // Telling the recycler view that the dataset has changed
        notifyDataSetChanged()
    }

    // The inner class that represents the view of a single item in the recycler view
    inner class CourseViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        // Binding the coursedetails to the item view
        fun bind(course: Course) {
            // Setting the code, name, credits, prerequisites, and description text views with the course data
            binding.code.text = course.code
            binding.name.text = course.name
//            binding.credits.text = course.credits
//            binding.prerequisites.text = course.prerequisites
//            binding.description.text = course.description

            // Setting the listener for the details button
            binding.detailsButton.setOnClickListener {
                courseDetails(course)
            }

        }
    }
}

