package id.code.alpha.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.Glide
import id.code.alpha.core.BuildConfig
import id.code.alpha.core.databinding.ItemMovieBinding
import id.code.alpha.core.domain.model.Movie

class MovieListAdapter(private val movies: List<Movie>, private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    class ViewHolder(private val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Movie, listener: (Movie) -> Unit) {
            view.titleMovie.text = item.title
            view.textReleaseDate.text = item.releaseDate
            Glide.with(view.root.context).load(BuildConfig.BASE_IMAGE_URL + item.posterPath)
                .into(view.imageMovie)

            if (item.voteAverage != null) {
                view.ratingBar.rating = (item.voteAverage / 2).toFloat()
            }

            itemView.setOnClickListener {
                if (adapterPosition != NO_POSITION)
                    listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int = movies.size
}