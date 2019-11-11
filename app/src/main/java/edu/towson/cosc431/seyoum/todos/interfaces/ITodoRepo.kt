package edu.towson.cosc431.seyoum.todos.interfaces

import edu.towson.cosc431.seyoum.todos.Todo

interface ITodoRepo{
    fun getCount(): Int
    fun replace(todo: Todo)
    fun remove(todo: Todo)
    fun getTodo(idx: Int):Todo
    fun findAll()
    fun addTodo(todo: Todo)
    fun isCompleted(todo: Todo)
    fun findCompleted()
    fun findActive()
}