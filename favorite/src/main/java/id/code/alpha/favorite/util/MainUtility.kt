package id.code.alpha.favorite.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}


fun AppCompatActivity.replaceFragment(
    frameId: Int,
    fragment: Class<out Fragment>,
    argument: Bundle?
) {
    supportFragmentManager.doTransaction {
        replace(frameId, fragment, argument, null)
        addToBackStack(null)
    }
}

fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Class<out Fragment>): Int? {
    supportFragmentManager.doTransaction {
        replace(frameId, fragment, null, null)
        addToBackStack(null)
    }
    return null
}

fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment): Float? {
    supportFragmentManager.doTransaction {
        replace(frameId, fragment, null)
        addToBackStack(null)
    }
    return null
}