/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.AndroidDevChallengeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView { startActivity(Intent(this, DetailsActivity::class.java)) }
        }
    }
}

data class Bird(val name: String, val image: Int, val location: String, val age: Int)

@Composable
fun NewsStory(bird: Bird) {
    Text(
        bird.name,
        style = typography.h6,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        Text(bird.location, style = typography.body1, modifier = Modifier.weight(1f))
        Text("Age : ${bird.age}", style = typography.body1)
    }
}

@Composable
fun MyAppBar() {
    AppBar(
        modifier = Modifier
            .fillMaxWidth(),
        onNavIconPressed = { },
        title = { Text("Meet Birds !") },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                // More icon
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    modifier = Modifier
                        .clickable(onClick = {})
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                        .height(24.dp),
                    contentDescription = "AppBar Icon"
                )
            }
        }
    )
}

@Composable
fun MainView(onClick: () -> Unit) {
    AndroidDevChallengeTheme {
        Surface(color = MaterialTheme.colors.background) {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    MyScreenContent(onClick = onClick)
                }
            }
        }
    }
}

@Composable
fun MyScreenContent(onClick: () -> Unit, indexes: List<Int> = List(8) { it }) {

    val birds: ArrayList<Bird> = arrayListOf()
    birds.add(
        Bird(
            "Robert, from Paris, loves repeating what you say",
            R.drawable.im2,
            "Paris, France",
            3
        )
    )
    birds.add(
        Bird(
            "Joe and Joey, the twins, yellow and blue Aras, from Ireland",
            R.drawable.im7,
            "Dublin, Ireland",
            2
        )
    )
    birds.add(Bird("Ariel, Angela and Amelia", R.drawable.im8, "Koln, Germany", 5))
    birds.add(
        Bird(
            "Sarah, a beautiful green parrot, from Norway",
            R.drawable.im3,
            "Oslo, Norway",
            2
        )
    )
    birds.add(
        Bird(
            "Gina, a shy canary from Washington D.C.",
            R.drawable.im4,
            "Washington, D.C.",
            4
        )
    )
    birds.add(
        Bird(
            "David, Carlito, Emmanuel, Gerald and Philip, from Philadelphia",
            R.drawable.im5,
            "Philadelphia, Pennsylvania",
            1
        )
    )
    birds.add(Bird("Jean, from Australia", R.drawable.im6, "Sydney, Australia", 2))

    birds.add(Bird("Sam", R.drawable.im10, "Roma, Italia", 1))

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {

            MyAppBar()
            ArticleList(indexes = indexes, birds = birds, onClick = onClick)
        }
    }
}

@Composable
fun ArticleList(
    onClick: () -> Unit,
    indexes: List<Int>,
    birds: ArrayList<Bird>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = indexes) { index ->
            ArticleMiniature(bird = birds[index], onClick = onClick)
            Divider(color = Color.LightGray, modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp))
        }
    }
}

@Composable
fun ArticleMiniature(onClick: () -> Unit, bird: Bird) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)

    ) {
        Image(
            painter = painterResource(bird.image),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop

        )
        Spacer(Modifier.height(12.dp))

        NewsStory(bird)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MainView { }
}
