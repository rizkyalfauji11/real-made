package id.code.alpha.realmade.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.code.alpha.core.data.Resource
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.ui.MovieListAdapter
import id.code.alpha.core.utils.viewLifecycleLazy
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.FragmentHomeBinding
import id.code.alpha.realmade.detail.DetailActivity
import id.code.alpha.realmade.main.dialog.MovieTypeDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var page = 1
    private val viewBinding by viewLifecycleLazy {
        view?.let { FragmentHomeBinding.bind(it) }
    }

    private val homeViewModel: HomeViewModel by viewModel()
    private var movieListAdapter: MovieListAdapter? = null
    private var selectedType = MovieTypeDialogFragment.UPCOMING_TYPE
    private val movieList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureOnClick()
        configurePagination()
        movieListAdapter = MovieListAdapter(movieList){
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        setType(selectedType, page)
        with(viewBinding?.rvHome) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = movieListAdapter
        }
    }

    private fun configurePagination() {
        viewBinding?.rvHome?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition

                if (isLastPosition && viewBinding?.progressBar?.visibility == GONE) {
                    page++
                    setType(selectedType, page)
                }
            }
        })
    }

    private fun setType(type: String, page: Int) {
        homeViewModel.getMovie(type, page).observe(viewLifecycleOwner, moviesObserver)
    }

    private val moviesObserver = Observer<Resource<List<Movie>>> {
        if (it != null)
            when (it) {
                is Resource.Loading<*> -> viewBinding?.progressBar?.visibility = VISIBLE
                is Resource.Success<*> -> {
                    viewBinding?.progressBar?.visibility = GONE
                    Log.e("WOW", "before")
                    if (page == 1){
                        Log.e("WOW", "after")
                        movieList.clear()
                    }
                    it.data?.let { it1 -> movieList.addAll(it1) }
                    movieListAdapter?.notifyDataSetChanged()
                }
                is Resource.Error<*> -> {
                    viewBinding?.progressBar?.visibility = GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun configureOnClick() {
        viewBinding?.fabType?.setOnClickListener {
            val dialog = MovieTypeDialogFragment.newInstance(selectedType)
            dialog.setTypeListener(typeListener)
            dialog.show(childFragmentManager, MovieTypeDialogFragment::class.java.simpleName)
        }
    }

    private val typeListener = object : MovieTypeDialogFragment.OnTypeSelectedListener{
        override fun onTypeSelected(type: String) {
            selectedType = type
            page = 1
            setType(selectedType, page)
        }
    }
}