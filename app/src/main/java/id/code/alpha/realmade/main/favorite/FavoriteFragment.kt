package id.code.alpha.realmade.main.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.code.alpha.core.ui.MovieListAdapter
import id.code.alpha.core.utils.viewLifecycleLazy
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.FragmentFavoriteBinding
import id.code.alpha.realmade.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private val viewBinding by viewLifecycleLazy {
        view?.let { FragmentFavoriteBinding.bind(it) }
    }
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListAdapter = MovieListAdapter {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, {
            movieListAdapter.submitList(it)
        })

        with(viewBinding?.rvHome) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = movieListAdapter
        }
    }
}