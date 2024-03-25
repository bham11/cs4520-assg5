package com.cs4520.assignment5.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.commit
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import com.cs4520.assignment5.R
import com.cs4520.assignment5.ui.login.LoginForm

//class MainActivity : AppCompatActivity() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity_layout)
//        if (savedInstanceState == null) {
//            val appHost = NavHostFragment.create(R.navigation.nav_graph)
//
//            supportFragmentManager.commit {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView, appHost)
//                    .setPrimaryNavigationFragment(appHost)
//                    .commit()
//            }}}
//}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    AppNavHost(navController = rememberNavController())
                }

            }


        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LoginForm()
//}