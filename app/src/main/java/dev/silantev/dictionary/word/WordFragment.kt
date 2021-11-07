package dev.silantev.dictionary.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.silantev.dictionary.data.models.Word
import dev.silantev.dictionary.databinding.FragmentWordBinding

class WordFragment : Fragment() {

    private val viewModel: WordViewModel by viewModels()

    private lateinit var binding: FragmentWordBinding
    private val adapter = MeaningsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentWordBinding
            .inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val controller = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbar, controller)

        init()
        observe()
    }

    private fun init() {
        viewModel.word = requireArguments().getSerializable("word") as Word
    }

    private fun observe() {
        viewModel.meanings.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.text.observe(viewLifecycleOwner) {
            binding.toolbar.title = it
        }
    }

}

