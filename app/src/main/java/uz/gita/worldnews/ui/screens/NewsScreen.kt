package uz.gita.worldnews.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import uz.gita.worldnews.R
import uz.gita.worldnews.adapter.ItemClickListener
import uz.gita.worldnews.adapter.NewsAdapter
import uz.gita.worldnews.databinding.NewsScreenBinding
import uz.gita.worldnews.models.NewsData
import uz.gita.worldnews.models.NewsResponse
import uz.gita.worldnews.ui.viewmodel.NewsViewModel
import uz.gita.worldnews.utils.Resource

class NewsScreen : Fragment(), ItemClickListener {
    private var _binding: NewsScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = NewsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = NewsAdapter(this)
        viewModel.getNews(requireContext(), "us")
        viewModel.liveData.observe(viewLifecycleOwner, observer(view))
        binding.rvView.adapter = adapter
        binding.rvView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun observer(view: View): Observer<Resource<NewsResponse>> {
        val observer = Observer<Resource<NewsResponse>> {
            when (it) {
                is Resource.Success -> adapter.differ.submitList(it.data!!.articles)
                is Resource.Error -> Snackbar.make(view, "${it.message}", Snackbar.LENGTH_SHORT)
                    .show()
                else -> Snackbar.make(view, "I don't know", Snackbar.LENGTH_SHORT).show()
            }
        }


        return observer

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClickListener(data: NewsData) {
        val bundle = Bundle().apply {
            putParcelable("key",data)
        }
        findNavController().navigate(R.id.action_newsScreen_to_newsInfoScreen, bundle)
    }


}