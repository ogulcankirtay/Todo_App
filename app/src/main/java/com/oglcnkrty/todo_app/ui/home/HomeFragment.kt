package com.oglcnkrty.todo_app.ui.home

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.application
import com.oglcnkrty.todo_app.R
import com.oglcnkrty.todo_app.databinding.FragmentHomeBinding
import com.oglcnkrty.todo_app.enums.Priority
import com.oglcnkrty.todo_app.model.TodoModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Console
import java.util.Date
@AndroidEntryPoint
class HomeFragment : Fragment() {

private  var _binding : FragmentHomeBinding? = null
    private val binding get()=_binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)


        viewModel.todoList.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", it.toString())

        }

        binding.fbAdd.setOnClickListener {
            viewModel.insertTodo( TodoModel(
                title = "title",
                description = "description",
                priority = Priority.HIGH,
                date = "date",
                isDone = true
            ))
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}