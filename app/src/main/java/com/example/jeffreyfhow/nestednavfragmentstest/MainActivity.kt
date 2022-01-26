package com.example.jeffreyfhow.nestednavfragmentstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Why am I overriding on SupportNavigateUp instead of using 'setupWithNavController' on the Toolbar?
     * Reasons discussed here.... https://stackoverflow.com/a/52863037/1229936
     * Pretend that I came to this Activity from another Activity NOT via NavigationComponent
     * and had to use 'navigateUpOrFinish' from the example to go back to the previous activity
     **/
    override fun onSupportNavigateUp(): Boolean {
        return navigateUpV2()
    }

    /**
     * This version causes a crash.
     * Replicate crash with: A -> B (with B1) -> B2 -> Back to B1 -> Back to B -> B -> Crash
     * java.lang.IllegalArgumentException: navigation destination id/action_fragmentA_to_fragmentB
     * is unknown to this NavController
     */
    private fun navigateUpV1(): Boolean {
        return findNavController(R.id.navHost).navigateUp()
    }

    /**
     * This version seems to work. But, is there a more proper way to do it?
     */
    private fun navigateUpV2(): Boolean {
        val hostedFragment = navHost?.childFragmentManager?.primaryNavigationFragment
        return when (hostedFragment) {
            is FragmentB -> {
                if (hostedFragment.isInnerFragmentB2Showing()) {
                    findNavController(R.id.embeddedNavHostFragment).navigateUp()
                } else {
                    findNavController(R.id.navHost).navigateUp()
                }
            }
            is FragmentA -> {
                findNavController(R.id.navHost).navigateUp()
            }
            else -> false
        }
    }
}
