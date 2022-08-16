package uz.gita.worldnews.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import uz.gita.worldnews.databinding.NewsInfoScreenBinding
import uz.gita.worldnews.models.NewsData

class NewsInfoScreen : Fragment() {
    private var _binding: NewsInfoScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = NewsInfoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data :NewsData? = requireArguments().getParcelable("key")
        binding.webView.loadUrl(data!!.url)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}