package edu.towson.cosc431.seyoum.todos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_todo.*

class AddTodo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        save_btn.setOnClickListener {
            val title = title_txt.text.toString()
            val text = content_txt.text.toString()
            val complete = iscompleted_check.isChecked

            val intent = Intent (this, MainActivity ::class.java)

            intent.putExtra("Title", title)
            intent.putExtra("Text", text)
            intent.putExtra("Complete", complete)
            startActivity(intent)
        }
    }
}
