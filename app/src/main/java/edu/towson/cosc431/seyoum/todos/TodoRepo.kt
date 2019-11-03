package edu.towson.cosc431.seyoum.todos

import edu.towson.cosc431.seyoum.todos.interfaces.ITodoRepo
import java.text.SimpleDateFormat
import java.util.*

class TodoRepo : ITodoRepo{

    private var todos: MutableList<Todo> = mutableListOf()
    private var todostemp: MutableList<Todo> = mutableListOf()
    var id = 10

    init {
        val today = Calendar.getInstance()
        val time = SimpleDateFormat("EEE MMMM d H:m:s Y").format(today.time)
        val seed = (1..10).map { idx -> Todo(idx,"TodoTitle$idx", "TodoContent$idx", false, time) }
        todos.addAll(seed)
        todostemp.addAll(todos)
    }
    override fun getCount(): Int {
        return todostemp.size
    }

    override fun replace(idx: Int, todo: Todo) {
        var position = 0

        val idd = todo.id

        //todos.set(idx, todo)
        todostemp.set(idx, todo)

        while (position < todos.size){
            if (todos.get(position).id == idd){
                todos.set(position, todo)
                break
            }
            position++
        }

    }

    override fun remove(idx: Int) {
        var position = 0

        val idd = todostemp.get(idx).id
        todostemp.removeAt(idx)

        while (position < todos.size){
            if (todos.get(position).id == idd){
                todos.removeAt(position)
                break
            }
            position++
        }
    }

    override fun getTodo(idx: Int): Todo {
        return todostemp.get(idx)
    }

    override fun addTodo(todo: Todo) {
        id++
        todo.id = id
        //todos.add(todo)
        todostemp.add(todo)
        todos.add(todo)
    }

    override fun isCompleted(idx: Int) {
        var position = 0
        val check = !todostemp.get(idx).complete
        todostemp.get(idx).complete = check
        val idd = todostemp.get(idx).id

        while (position < todos.size){
            if (todos.get(position).id == idd){
                todos.get(position).complete = check
                break
            }
            position++
        }
    }

    override fun findCompleted() {
        var position = 0
        todostemp.clear()
        while (position < todos.size){
            if (todos.get(position).complete){
                todostemp.add(todos.get(position))
            }
            position++
        }
    }

    override fun findActive() {
        var position = 0
        todostemp.clear()
        while (position < todos.size){
            if (!todos.get(position).complete){
                todostemp.add(todos.get(position))
            }
            position++
        }
    }

    override fun findAll() {
        todostemp.clear()
        todostemp.addAll(todos)
    }

}