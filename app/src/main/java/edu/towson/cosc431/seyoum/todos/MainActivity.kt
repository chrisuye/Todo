package edu.towson.cosc431.seyoum.todos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val title = intent.getStringExtra("Title")
        val text = intent.getStringExtra("Text")
        val complete = intent.getBooleanExtra("Complete", false)

        if (title != null && text != null){
            val todo = Todo(title, text, complete)
            println(todo.toString())
        }




        add_btn.setOnClickListener {
            val i = Intent(this, AddTodo ::class.java)

            startActivity(i)
        }
    }
}
