package id.code.alpha.realmade.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.code.alpha.core.data.Resource
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.ui.MovieListAdapter
import id.code.alpha.core.utils.viewLifecycleLazy
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.FragmentHomeBinding
import id.code.alpha.realmade.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
 class HomeFragment : Fragment() {

    private val viewBinding by viewLifecycleLazy {
        view?.let { FragmentHomeBinding.bind(it) }
    }

     val homeViewModel: HomeViewModel by viewModel()
    private var movieListAdapter: MovieListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListAdapter = MovieListAdapter {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        setType(resources.getString(R.string.type_popular))

        Handler(Looper.getMainLooper()).postDelayed(
            {
                setType(resources.getString(R.string.type_top_rated))
            },
            3000
        )

        with(viewBinding?.rvHome) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = movieListAdapter
        }
    }

     private fun setType(type: String){
         homeViewModel.getMovie(type).observe(viewLifecycleOwner, moviesObserver)
     }

    private val moviesObserver = Observer<Resource<List<Movie>>>{
        if (it != null)
            when (it) {
                is Resource.Loading<*> -> viewBinding?.progressBar?.visibility = VISIBLE
                is Resource.Success<*> -> {
                    viewBinding?.progressBar?.visibility = GONE
                    movieListAdapter?.submitList(it.data)
                }
                is Resource.Error<*> -> {
                    viewBinding?.progressBar?.visibility = GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}