package edu.towson.cosc431.seyoum.todos.interfaces

import edu.towson.cosc431.seyoum.todos.Todo

interface ITodoRepo{
    fun getCount(): Int
    fun replace(idx: Int, todo: Todo)
    fun remove(idx: Int)
    fun getTodo(idx: Int):Todo
    fun addTodo(todo: Todo)
    fun isCompleted(idx: Int)
}