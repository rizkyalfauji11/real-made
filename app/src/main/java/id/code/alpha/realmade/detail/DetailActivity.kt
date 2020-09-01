package id.code.alpha.realmade.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.realmade.BuildConfig
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val movie = intent?.getParcelableExtra<Movie>(EXTRA_DATA)
        movie?.let { data ->
            var statusFavorite = data.isFavorite
            setFavoriteIcon(statusFavorite)

            Glide.with(viewBinding.root).load(BuildConfig.BASE_IMAGE_URL + data.backdropPath)
                .into(viewBinding.posterMovie)
            Glide.with(viewBinding.root).load(BuildConfig.BASE_IMAGE_URL + data.posterPath)
                .into(viewBinding.imageMovie)

            with(viewBinding) {
                setSupportActionBar(toolbar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                textTitle.text = data.title
                textReleaseDate.text = data.releaseDate
                ratingBar.rating = ((data.voteAverage?.div(2)) ?: 0F).toFloat()
                textDescription.text = data.overview

                iconFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(data, statusFavorite)
                    setFavoriteIcon(statusFavorite)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteIcon(status: Boolean) {
        if (status)
            Glide.with(viewBinding.root).load(R.drawable.ic_favorite).into(viewBinding.iconFavorite)
        else
            Glide.with(viewBinding.root).load(R.drawable.ic_un_favorite)
                .into(viewBinding.iconFavorite)
    }

}