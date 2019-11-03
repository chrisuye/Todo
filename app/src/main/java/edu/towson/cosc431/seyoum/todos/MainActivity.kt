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
import kotlinx.android.synthetic.main.fragment_button.*
import kotlinx.android.synthetic.main.fragment_list.*

class MainActivity() : AppCompatActivity(), ITodoControl {

    override lateinit var todos: ITodoRepo
    override lateinit var todostemp:ITodoRepo
    override fun launchAdd() {
        val intent = Intent(this, AddTodo::class.java)
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
    }

    override fun launchEdit(idx: Int, todo: Todo) {
        val intent = Intent(this, EditTodo::class.java)
        val todostring = Gson().toJson(todo)
        intent.putExtra("Todo",todostring)
        intent.putExtra("Idx", idx)
        intent.putExtra("Id", todostemp.getTodo(idx).id)
        startActivityForResult(intent, EDIT_TODO_REQUEST_CODE)
    }

    override fun getCurrentCount() : Int {
        return todostemp.getCount()
    }

    override fun complete(idx: Int) {

        todostemp.isCompleted(idx)
    }

    override fun deleteTodo(idx: Int) {

        todostemp.remove(idx)
    }

    override fun editTodo(idx: Int, todo: Todo) {

        todostemp.replace(idx,todo)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        all_btn.setBackgroundColor(Color.parseColor("#ff0000"))
        active_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
        completed_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))



        recycle_fram.layoutManager = LinearLayoutManager(this)
        recycle_fram.adapter = MainAdapter(this)

        todostemp = TodoRepo()


        add_todo_btn.setOnClickListener {
            launchAdd()
        }

        all_btn.setOnClickListener {
            all_btn.setBackgroundColor(Color.parseColor("#ff0000"))
            active_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            completed_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            todostemp.findAll()
            recycle_fram.adapter?.notifyDataSetChanged()

        }
        active_btn.setOnClickListener {
            all_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            active_btn.setBackgroundColor(Color.parseColor("#ff0000"))
            completed_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            todostemp.findActive()
            recycle_fram.adapter?.notifyDataSetChanged()
        }
        completed_btn.setOnClickListener {
            all_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            active_btn.setBackgroundColor(Color.parseColor("#d3d3d3"))
            completed_btn.setBackgroundColor(Color.parseColor("#ff0000"))
            todostemp.findCompleted()
            recycle_fram.adapter?.notifyDataSetChanged()
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
                            todostemp.addTodo(todo)
                            recycle_fram.adapter?.notifyItemInserted(todostemp.getCount())
                        }

                    }
                    EDIT_TODO_REQUEST_CODE -> {
                        val json = data?.getStringExtra(EditTodo.TODOE_EXTRA_KEY)
                        val idx = data?.getIntExtra(EditTodo.POSITION_KEY,1)
                        if (json != null && idx != null){
                            val todo = Gson().fromJson<Todo>(json,Todo::class.java)
                            editTodo(idx,todo)
                            recycle_fram.adapter?.notifyItemChanged(idx)
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

