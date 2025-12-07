package com.oglcnkrty.todo_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oglcnkrty.todo_app.base.listener.TodoItemClickListener
import com.oglcnkrty.todo_app.databinding.FragmentHomeBinding
import com.oglcnkrty.todo_app.model.TodoModel
import com.oglcnkrty.todo_app.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), TodoItemClickListener {

private  var _binding : FragmentHomeBinding? = null
    private val binding get()=_binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)

        //liveData xml update için gerekli
        binding.lifecycleOwner=viewLifecycleOwner


        //databinding için gerekli
        binding.viewModel=viewModel
        binding.clickListener=this



        viewModel.todoList.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", it.toString())

        }

        binding.fbAdd.setOnClickListener {
          findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewAndEditFragment())
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onTodoItemClickListener(id: Int) {
       val action = HomeFragmentDirections.actionHomeFragmentToNewAndEditFragment(id)
        findNavController().navigate(action)
    }

    override fun onTodoChecked(todoModel: TodoModel) {
        viewModel.updateTodo(todoModel)
    }

}