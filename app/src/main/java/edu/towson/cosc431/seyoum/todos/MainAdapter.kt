package edu.towson.cosc431.seyoum.todos

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoControl
import kotlinx.android.synthetic.main.todolist.view.*
import kotlin.collections.ArrayList

class MainAdapter (val controller:ITodoControl): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return controller.getCurrentCount()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context).inflate(R.layout.todolist, parent, false)
        val viewHolder = CustomViewHolder(layoutInflater)

        layoutInflater.completeview.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.complete(position)
            this.notifyItemChanged(position)

        }

        layoutInflater.setOnClickListener {
            val position = viewHolder.adapterPosition
            println("adapt "+position)
            val todo = controller.todos.getTodo(position)
            controller.launchEdit(position, todo)

        }

        layoutInflater.setOnLongClickListener {
            val position = viewHolder.adapterPosition
            val dialogBuilder = AlertDialog.Builder(layoutInflater.context)


            dialogBuilder.setMessage("Do you want to delete?")

                .setCancelable(false)

                .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                        dialog, id -> controller.deleteTodo(position)
                    this.notifyItemRemoved(position)
                })

                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })


            val alert = dialogBuilder.create()

            alert.setTitle(controller.todos.getTodo(position).title)

            alert.show()

            return@setOnLongClickListener true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val todo = controller.todos.getTodo(position)
        holder.BindTodo(todo)
    }


}

class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun BindTodo(todo:Todo){

        if (todo.content.length > 12){
            itemView.todoview_txt.text = todo.content.substring(0,11) + "..."
        }
        else{
            itemView.todoview_txt.text = todo.content
        }

        if (todo.title.length > 10){
            itemView.titleview_txt.text = todo.title.substring(0, 9) + "..."
        }
        else{
            itemView.titleview_txt.text = todo.title
        }


        itemView.dateview_txt.text = todo.date
        itemView.completeview.isChecked = todo.complete
    }

}