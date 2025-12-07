package com.oglcnkrty.todo_app.ui.Edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oglcnkrty.todo_app.databinding.FragmentNewAndEditBinding
import com.oglcnkrty.todo_app.enums.Priority
import com.oglcnkrty.todo_app.model.TodoModel
import com.oglcnkrty.todo_app.ui.Edit.viewmodel.NewAndEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment() {

    private var _binding: FragmentNewAndEditBinding? = null
    private val binding get() = _binding!!

    private var autoCompletePriorityIndex = 0
    private val viewModel: NewAndEditViewModel by viewModels()

    private val args: NewAndEditFragmentArgs by navArgs()

    private val todoId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewAndEditBinding.inflate(inflater, container, false)

        setUpScreen()
        setupAdapter()
        onclickListeners()

        return binding.root
    }

    private fun setUpScreen() {
        if (args.id == -1) {
            setUpCreateMode()

        } else {
            setupEditMode()
            viewModel.getTodoById(args.id)
        }
    }

    private fun setUpCreateMode() {
        setToolbarTitle("New Todo")

        binding.btnSave.setText("Save")
        binding.ivDelete.visibility = View.GONE
    }

    private fun setupEditMode() {
        setToolbarTitle("Edit Todo")
        binding.btnSave.setText("update")
        viewModel.getTodoById(args.id)
        observeTodo()

    }

    private fun observeTodo() {
        viewModel.todo.observe(viewLifecycleOwner) { item ->
            binding.tiTitle.setText(item?.title)
            binding.tiDescription.setText(item?.description)
            binding.acPriority.setText(item?.priority?.name, false)
            binding.cbDone.isChecked = item?.isDone ?: false


            autoCompletePriorityIndex = when (item?.priority) {
                Priority.HIGH -> 0
                Priority.MEDIUM -> 1
                else -> 2
            }
        }
    }

    fun onclickListeners() {
        binding.btnSave.setOnClickListener {
            handleSaveButton()
        }

        binding.acPriority.setOnItemClickListener { _, _, index, _ ->
            autoCompletePriorityIndex = index
        }

        binding.ivDelete.setOnClickListener {
            viewModel.deleteTodo()

            Toast.makeText(requireContext(), "Todo removed successfully", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun setToolbarTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    private fun handleSaveButton() {

        val title = binding.tiTitle.text.toString()
        val description = binding.tiDescription.text.toString()
        val isDone = binding.cbDone.isChecked

        val priority = when (autoCompletePriorityIndex) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUM
            else -> Priority.LOW
        }

        val todoModel = TodoModel(
            title = title, description = description, priority = priority, isDone = isDone
        )

        if (args.id == -1) {
            viewModel.insertTodo(todoModel)
        } else {
            viewModel.updateTodo(todoModel)
        }

        Toast.makeText(requireContext(), "Todo added successfully", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun setupAdapter() {
        binding.acPriority.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                arrayOf(Priority.HIGH.name, Priority.MEDIUM.name, Priority.LOW.name)
            )
        )


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}