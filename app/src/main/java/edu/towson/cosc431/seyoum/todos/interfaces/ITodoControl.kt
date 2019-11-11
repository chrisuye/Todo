package edu.towson.cosc431.seyoum.todos.interfaces

import edu.towson.cosc431.seyoum.todos.Todo

interface ITodoControl{
    fun deleteTodo(todo: Todo)
    fun complete(todo: Todo)
    fun launchAdd()
    fun launchEdit(idx: Int, todo: Todo)
    fun editTodo(todo: Todo)
    fun getCurrentCount() : Int

    val todos: ITodoRepo
    val todostemp: ITodoRepo

}