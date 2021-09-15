package id.code.alpha.realmade.main.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.code.alpha.realmade.R

class MovieTypeDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_type_dialog, container, false)
    }

    companion object {
        private const val SELECTED_TYPE = "SELECTED_TYPE"
        @JvmStatic
        fun newInstance(selectedType: String? = null) =
            MovieTypeDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(SELECTED_TYPE, selectedType)
                }
            }
    }
}