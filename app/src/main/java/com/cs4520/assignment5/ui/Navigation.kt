package com.cs4520.assignment5.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cs4520.assignment5.ui.login.LoginForm
import com.cs4520.assignment5.ui.product_list.ProductListScreen
import com.cs4520.assignment5.ui.product_list.ProductListViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route
)
{
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginForm(navController)
        }

        composable(NavigationItem.ProductList.route) {
//            val vm by ProductListViewModel(this)
            ProductListScreen(navController)
        }
    }
}

enum class Screen {
    LOGIN,
    PRODUCTLIST

}

sealed class NavigationItem(val route: String){
    object Login: NavigationItem(Screen.LOGIN.name)
    object ProductList: NavigationItem(Screen.PRODUCTLIST.name)
}