package id.code.alpha.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.Glide
import id.code.alpha.core.BuildConfig
import id.code.alpha.core.R
import id.code.alpha.core.domain.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(MyStaticDiffCallback<Movie>()) {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Movie) {
            view.titleMovie.text = item.title
            view.textReleaseDate.text = item.releaseDate
            if (item.voteAverage != null){
                view.ratingBar.rating = (item.voteAverage/2).toFloat()
            }
            Glide.with(view).load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                .into(view.imageMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        ).apply {
            itemView.setOnClickListener {
                if (adapterPosition != NO_POSITION)
                    listener(getItem(adapterPosition))
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}