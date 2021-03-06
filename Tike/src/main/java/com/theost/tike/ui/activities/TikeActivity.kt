package com.theost.tike.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.tike.R
import com.theost.tike.databinding.ActivityTikeBinding

class TikeActivity : FragmentActivity(R.layout.activity_tike) {

    private val binding: ActivityTikeBinding by viewBinding()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavController()
        setupSmoothBottomMenu()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        navController = (navHostFragment as NavHostFragment).navController
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, binding.root).apply { inflate(R.menu.menu_bottom) }
        binding.bottomNavigation.setupWithNavController(popupMenu.menu, navController)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, TikeActivity::class.java)
        }

        fun newTaskIntent(context: Context): Intent {
            return newIntent(context).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
    }
}