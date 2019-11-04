package edu.towson.cosc431.seyoum.todos


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoButton
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoControl
import edu.towson.cosc431.seyoum.todos.interfaces.ITodoRepo
import kotlinx.android.synthetic.main.fragment_button.*
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Inflate the layout for this fragment
        return view
    }






}
