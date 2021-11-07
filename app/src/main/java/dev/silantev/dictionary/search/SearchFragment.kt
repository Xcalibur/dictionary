package dev.silantev.dictionary.search

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.silantev.dictionary.R
import dev.silantev.dictionary.data.models.Word
import dev.silantev.dictionary.databinding.FragmentSearchBinding
import dev.silantev.dictionary.presentation.ErrorPresentation

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var errorPresentation: ErrorPresentation
    private val adapter = SearchAdapter(::onSearchItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return FragmentSearchBinding
            .inflate(inflater, container, false)
            .also {
                binding = it
                (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorPresentation = ErrorPresentation(requireContext().applicationContext)
        setupList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
        val item = menu.findItem(R.id.search)
        val view = item?.actionView as SearchView
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                viewModel.search(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupList() {
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter
        viewModel.stateSearch.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchUIState.Error -> {
                    onError(state)
                }

                SearchUIState.Loading -> {
                    onLoading()
                }

                is SearchUIState.Success -> {
                    onSuccess(state)
                }
            }
        }
    }

    private fun onSuccess(state: SearchUIState.Success) {
        binding.list.isVisible = true
        binding.message.isVisible = false
        binding.loading.isVisible = false
        adapter.submitList(state.list)
    }

    private fun onError(state: SearchUIState.Error) {
        binding.loading.isVisible = false
        binding.message.isVisible = true
        binding.list.isVisible = false
        errorPresentation.present(state.exception, binding.message)
    }

    private fun onLoading() {
        binding.loading.isVisible = true
        binding.message.isVisible = false
    }

    private fun hideKeyboard() {
        val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(requireView().windowToken, 0)

    }

    private fun onSearchItemClicked(item: Word) {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_searchFragment_to_wordFragment, Bundle().apply {
                /*
                 В теории этот объект может быть слишком велик чтобы передать его через бандл
                 В таком случае стоит поменять на передачу только ид и получение всего объекта
                 из бд например.
                 На данный момент не достаточно экспертизы для оценки проблем
                 */
                putSerializable("word", item)
            })
    }
}