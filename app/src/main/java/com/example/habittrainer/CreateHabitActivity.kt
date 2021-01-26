package com.example.habittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.habittrainer.db.HabitDbTable
import kotlinx.android.synthetic.main.activity_create_habit.*
import kotlinx.android.synthetic.main.single_card.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {
    private val TAG = CreateHabitActivity::class.java.simpleName

    private val CHOOSE_IMAGE_REQUEST = 1

    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun chooseImage(view: View) {
        val intent  = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)

        Log.d(TAG, "Intent to choose image sent...")
    }

    private fun EditText.isBlank() = this.text.toString().isBlank()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            Log.d(TAG, "An image was chosen by the user.")

            val bitmap = tryReadBitmap(data.data!!)
            bitmap?.let {
                this.imageBitmap = bitmap
                iv_image.setImageBitmap(bitmap)
                Log.d(TAG, "Read image bimap and updated image view.")
        }
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun storeHabit(view: View) {
        if(et_title.isBlank() || et_description.isBlank()) {
            Log.d(TAG, "No habit stored: title or description missing")
            displayErrorMessage("Your habit needs an engaging title and description")
            return
        } else if (imageBitmap == null) {
            Log.d(TAG, "No habit stored: title or description missing")
            displayErrorMessage("Add a motivating picture to your habit")
            return
        }

        // Store the habit...
//        tv_error.visibility = View.INVISIBLE
        val title = et_title.text.toString()
        val description = et_description.text.toString()
        val habit = Habit(title, description, imageBitmap!!)

        val id = HabitDbTable(this).store(habit)

        if (id == -1L) {
            displayErrorMessage("Habit could not be stored...let's not make this a habit.")
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }



    private fun displayErrorMessage(s: String) {
        tv_error.text = s
        tv_error.visibility = View.VISIBLE

    }


}