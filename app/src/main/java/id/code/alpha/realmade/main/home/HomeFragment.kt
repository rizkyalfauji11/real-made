package id.code.alpha.realmade.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.code.alpha.core.data.Resource
import id.code.alpha.core.ui.MovieListAdapter
import id.code.alpha.core.utils.viewLifecycleLazy
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.FragmentHomeBinding
import id.code.alpha.realmade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewBinding by viewLifecycleLazy {
        view?.let { FragmentHomeBinding.bind(it) }
    }
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListAdapter = MovieListAdapter {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        homeViewModel.movies.observe(viewLifecycleOwner, {
            if (it != null)
                when (it) {
                    is Resource.Loading -> viewBinding?.progressBar?.visibility = VISIBLE
                    is Resource.Success -> {
                        viewBinding?.progressBar?.visibility = GONE
                        movieListAdapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        viewBinding?.progressBar?.visibility = GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
        })

        with(viewBinding?.rvHome) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = movieListAdapter
        }
    }
}