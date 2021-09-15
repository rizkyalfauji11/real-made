package id.code.alpha.realmade.main.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.code.alpha.core.utils.viewLifecycleLazy
import id.code.alpha.realmade.R
import id.code.alpha.realmade.databinding.FragmentMovieTypeDialogBinding

class MovieTypeDialogFragment : BottomSheetDialogFragment() {

    private val viewBinding by viewLifecycleLazy {
        view?.let { FragmentMovieTypeDialogBinding.bind(it) }
    }
    private var typeListener: OnTypeSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_type_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkType()
        configureOnClick()
    }

    private fun configureOnClick() {
        viewBinding?.btnMovieType?.setOnClickListener {
            typeListener?.onTypeSelected(getType())
            dismiss()
        }
    }

    private fun getType(): String {
        return when {
            viewBinding?.rbPopularMovie?.isChecked == true -> {
                POPULAR_TYPE
            }
            viewBinding?.rbTopRatedMovie?.isChecked == true -> {
                TOP_RATED_TYPE
            }
            viewBinding?.rbNowPlayingMovie?.isChecked == true -> {
                NOW_PLAYING_TYPE
            }
            viewBinding?.rbUpcomingMovie?.isChecked == true -> {
                UPCOMING_TYPE
            }
            else -> {
                POPULAR_TYPE
            }
        }
    }

    fun setTypeListener(listener: OnTypeSelectedListener) {
        this.typeListener = listener
    }

    private fun checkType() {
        val type = arguments?.getString(SELECTED_TYPE)
        when {
            type.equals(POPULAR_TYPE) -> {
                viewBinding?.rbPopularMovie?.isChecked = true
            }
            type.equals(TOP_RATED_TYPE) -> {
                viewBinding?.rbTopRatedMovie?.isChecked = true
            }
            type.equals(NOW_PLAYING_TYPE) -> {
                viewBinding?.rbNowPlayingMovie?.isChecked = true
            }
            type.equals(UPCOMING_TYPE) -> {
                viewBinding?.rbUpcomingMovie?.isChecked = true
            }
            else -> {
                viewBinding?.rbPopularMovie?.isChecked = true
            }
        }
    }

    interface OnTypeSelectedListener {
        fun onTypeSelected(type: String)
    }

    companion object {
        private const val SELECTED_TYPE = "SELECTED_TYPE"
        const val POPULAR_TYPE = "popular"
        const val TOP_RATED_TYPE = "top_rated"
        const val NOW_PLAYING_TYPE = "now_playing"
        const val UPCOMING_TYPE = "upcoming"

        @JvmStatic
        fun newInstance(selectedType: String? = null) =
            MovieTypeDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(SELECTED_TYPE, selectedType)
                }
            }
    }
}