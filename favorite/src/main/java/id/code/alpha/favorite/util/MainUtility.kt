package id.code.alpha.favorite.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment): Float? {
    supportFragmentManager.doTransaction {
        replace(frameId, fragment, null)
        addToBackStack(null)
    }
    return null
}