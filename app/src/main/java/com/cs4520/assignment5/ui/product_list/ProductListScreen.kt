package com.cs4520.assignment5.ui.product_list

import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cs4520.assignment5.model.Product
import androidx.compose.runtime.collectAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ProductListScreen(
    vm: ProductListViewModel = viewModel(),
    navHostController: NavHostController
) {
    val productList by vm.getProductList().observeAsState(emptyList())
    val isLoaded by vm.getIsLoading().observeAsState(false)
    val errorMsg by vm.getErrorMessage().observeAsState(initial = "")


    LaunchedEffect(Unit){
        vm.fetchProducts()
    }

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!isLoaded) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp),
                    backgroundColor = Color.LightGray,
                    color = Color.Red //progress color
                )
            } else {
                if(errorMsg != "") {
                    Text(errorMsg)
                }
                else{
                    ProductList(products = productList)
                }
            }

        }

    }


}

@Composable
fun ProductList(products: List<Product>) {

    if (products.isEmpty()) {
        Text(
            text="No Products :(",
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize()
        )

    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp)
        )
        {
            items(
                items = products,
                itemContent = {
                    ProductListItem(product = it)
                }
            )
        }

    }

}


private fun testProductList(show: Int): List<Product> {
    return if (show == 1) {
        listOf(
            Product("Apple", "food", "2024-02-23", 4.5),
            Product("Apple", "food", "2024-02-23", 4.5),
            Product("Donut", "equipment", null, 4.59)
        )
    } else {
        listOf()
    }
}

