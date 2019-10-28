package edu.towson.cosc431.seyoum.todos

import edu.towson.cosc431.seyoum.todos.interfaces.ITodoRepo
import java.text.SimpleDateFormat
import java.util.*

class TodoRepo : ITodoRepo{

    private var todos: MutableList<Todo> = mutableListOf()

    init {
        val today = Calendar.getInstance()
        val time = SimpleDateFormat("EEE MMMM d H:m:s Y").format(today.time)
        val seed = (1..10).map { idx -> Todo("TodoTitle$idx", "TodoContent$idx", false, time) }
        todos.addAll(seed)
    }
    override fun getCount(): Int {
        return todos.size
    }

    override fun replace(idx: Int, todo: Todo) {
        todos.set(idx, todo)

    }

    override fun remove(idx: Int) {
        todos.removeAt(idx)
    }

    override fun getTodo(idx: Int): Todo {
        return todos.get(idx)
    }

    override fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    override fun isCompleted(idx: Int) {
        val check = !todos.get(idx).complete
        todos.get(idx).complete = check
    }

}