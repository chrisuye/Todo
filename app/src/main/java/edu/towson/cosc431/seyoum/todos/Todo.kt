package edu.towson.cosc431.seyoum.todos

data class Todo(
    var id:Int,
    var title:String,
    var content:String,
    var complete:Boolean,
    //var image:Byte,
    var date:String
)