package edu.towson.cosc431.seyoum.todos

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoControl
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoRepo
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity() : AppCompatActivity(), ITodoControl {

    override lateinit var todos: ITodoRepo
    override fun launchAdd() {
        val intent = Intent(this, AddTodo::class.java)
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
    }

    override fun launchEdit(idx: Int, todo: Todo) {
        val intent = Intent(this, EditTodo::class.java)
        val todostring = Gson().toJson(todo)
        intent.putExtra("Todo",todostring)
        intent.putExtra("Idx", idx)
        startActivityForResult(intent, EDIT_TODO_REQUEST_CODE)
    }

    override fun getCurrentCount() : Int {
        return todos.getCount()
    }

    override fun complete(idx: Int) {
        todos.isCompleted(idx)
    }

    override fun deleteTodo(idx: Int) {
        todos.remove(idx)
    }

    override fun editTodo(idx: Int, todo: Todo) {
        todos.replace(idx,todo)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = MainAdapter(this)

        todos = TodoRepo()


        add_btn.setOnClickListener {
            launchAdd()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(resultCode){
            Activity.RESULT_OK -> {
                when(requestCode){
                    ADD_TODO_REQUEST_CODE -> {
                        val json = data?.getStringExtra(AddTodo.TODO_EXTRA_KEY)
                        if (json != null){
                            val todo = Gson().fromJson<Todo>(json,Todo::class.java)
                            todos.addTodo(todo)
                            recycleview.adapter?.notifyItemInserted(todos.getCount())
                        }

                    }
                    EDIT_TODO_REQUEST_CODE -> {
                        val json = data?.getStringExtra(EditTodo.TODOE_EXTRA_KEY)
                        val idx = data?.getIntExtra(EditTodo.POSITION_KEY,1)
                        if (json != null && idx != null){
                            val todo = Gson().fromJson<Todo>(json,Todo::class.java)
                            editTodo(idx,todo)
                            recycleview.adapter?.notifyItemChanged(idx)
                        }

                    }
                }

            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this,"No change",Toast.LENGTH_SHORT).show()
            }
        }

    }
    companion object{
        val ADD_TODO_REQUEST_CODE = 1
        val EDIT_TODO_REQUEST_CODE = 2
    }
}

