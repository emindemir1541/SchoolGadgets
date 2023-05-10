package com.emindev.schoolgadgets.main.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.emindev.schoolgadgets.gradecalculator.ui.pages.GradePage
import com.emindev.schoolgadgets.main.common.util.Page
import com.emindev.schoolgadgets.main.ui.animatedComposable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Page.Main.name) {

        composable(route = Page.Main.name){
        MainPage(navController)
        }

        animatedComposable(route = Page.Grade.name){
            GradePage(navController)
        }
    }

}