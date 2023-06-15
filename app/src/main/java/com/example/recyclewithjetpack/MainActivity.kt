package com.example.recyclewithjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyclewithjetpack.ui.theme.RecyclewithjetpackTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclewithjetpackTheme {
                Surface(color = Color(0xFF006A4E)) {
                    val stockDataList = dummyStockData()
                    StockList(stockDataList)
                }

            }
        }
    }
}

@Composable
fun EachRow(user: User) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(R.drawable.h),
                contentDescription = "hello",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(CornerSize(15.dp)))
                    .size(150.dp)
            )
            Text(text = user.description, modifier = Modifier.padding(5.dp).size(150.dp))
        }
    }
}
@Preview
@Composable
fun PreviewEachRow() {
    EachRow(user = User("Sample User"))
}

@Preview
@Composable
fun PreviewRecyclerView() {
    val dummyUsers = listOf(
        User("User 1"),
        User("User 2"),
        User("User 3")
    )
    recyclerView(users = dummyUsers)
}

@Composable
fun recyclerView(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            EachRow(user)
        }
    }
}
@Composable
fun StockList(stockDataList: List<StockData>) {
    LazyColumn {
        items(stockDataList) { stockData ->
            StockCard(stockData = stockData)
        }
    }
}
@Composable
fun StockCard(stockData: StockData) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp,
        backgroundColor = colorResource(id = R.color.white),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // First Column
            Column(modifier = Modifier.weight(3f)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    // First Row - Icon and Full Name
                    Image(
                        painter = painterResource(id = stockData.icon),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = stockData.longName,
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Second Row - Short Name, Equity, Market
                    Text(
                        text = stockData.shortName,
                        style = MaterialTheme.typography.body1
                    )
                    Text("|", modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = stockData.equity,
                        style = MaterialTheme.typography.body1
                    )
                    Text("|", modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = stockData.market,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            // Second Column
            Column(modifier = Modifier.weight(2f)) {
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    // First Row - Value
                    Text(
                        text = stockData.value.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                Column(modifier = Modifier.fillMaxHeight().align(Alignment.CenterHorizontally), verticalArrangement = Arrangement.Center) {
                    // Second Row - Closed Price and Change
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = stockData.changeIcon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = stockData.closedPrice.toString(),
                            style = MaterialTheme.typography.body1,
                        )
                        Text("(")

                        Image(
                            painter = painterResource(id = stockData.changeIcon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )

                        Text(
                            text = stockData.change.toString(),
                            style = MaterialTheme.typography.body1,
                        )
                        Text(")")

                    }
                }
            }
        }
    }
}
