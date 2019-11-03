package edu.towson.cosc431.seyoum.todos

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoControl
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoRepo
import kotlinx.android.synthetic.main.todolist.view.*

class MainAdapter (val controller:ITodoControl): RecyclerView.Adapter<CustomViewHolder>(){
    override fun getItemCount(): Int {
        println(controller.getCurrentCount())
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
            val todo = controller.todostemp.getTodo(position)
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

            alert.setTitle(controller.todostemp.getTodo(position).title)

            alert.show()

            return@setOnLongClickListener true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val todo = controller.todostemp.getTodo(position)
        println(todo)
        holder.BindTodo(todo)
    }


}

class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun BindTodo(todo:Todo){

        if (todo.complete) {
            itemView.titleview_txt.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.todoview_txt.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            itemView.titleview_txt.paintFlags = 0
            itemView.todoview_txt.paintFlags = 0
        }

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