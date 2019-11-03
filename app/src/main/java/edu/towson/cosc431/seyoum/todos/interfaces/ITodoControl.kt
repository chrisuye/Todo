package edu.towson.cosc431.seyoum.todos.interfaces

import edu.towson.cosc431.seyoum.todos.Todo

interface ITodoControl{
    fun deleteTodo(idx: Int)
    fun complete(idx: Int)
    fun launchAdd()
    fun launchEdit(idx: Int, todo: Todo)
    fun editTodo(idx: Int, todo: Todo)
    fun getCurrentCount() : Int

    val todos: ITodoRepo
    val todostemp: ITodoRepo

}