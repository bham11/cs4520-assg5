package com.cs4520.assignment5.ui.product_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs4520.assignment5.R
import com.cs4520.assignment5.model.Product

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 2.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        backgroundColor = getBackgroundColor(product)
    ){
        Row {
            ProductImage(product)
            Column {
                Text(text = product.name)
                product.expiryDate?.let { Text(text = it) }
                Text(text="$${product.price}")
            }
        }
    }

}

@Composable
private fun ProductImage(product: Product) {
    Image(
        painter = painterResource(id = getProductImageID(product)),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(75.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

private fun getBackgroundColor(product: Product): Color {
    return if(product.type == "Equipment"){
        Color(0xFFE06666)
    }
    else {
        Color(0xFFFFD965)
    }
}

private fun getProductImageID(product: Product): Int{
    return if(product.type == "Equipment"){
        R.drawable.equipment
    }
    else {
        R.drawable.food
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen(){
    val product = Product("Apple", "food", "2024-02-23", 4.5)
    ProductListItem(product)
}