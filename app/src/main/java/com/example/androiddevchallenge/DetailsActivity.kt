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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.AndroidDevChallengeTheme

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DetailsContent()
        }
    }
}

@Composable
fun DetailsContent() {
    AndroidDevChallengeTheme {
        Surface(color = MaterialTheme.colors.background) {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Column(modifier = Modifier.fillMaxHeight()) {
                        Column(modifier = Modifier.weight(1f)) {
                            MyAppBar()

                            val bird = Bird(
                                "Robert, from Paris, loves repeating what you say",
                                R.drawable.im2,
                                "Paris, France",
                                1
                            )
                            Image(
                                painter = painterResource(bird.image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop

                            )
                            Column(
                                modifier = Modifier
                                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                            ) {
                                NewsStory(bird)
                                ProfileFab()
                            }
                            Text(
                                "Robert is a colorful parrot able to repeat simple sentences. He loves watching old TV Series and he often repeats famous replicas. Robert is very funny, and he knows how to make you laugh, however he has the bad habit to wake you up at night, by shouting some of his favorite replicas.",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileFab(modifier: Modifier = Modifier) {

    FloatingActionButton(
        onClick = { },
        modifier = modifier
            .padding(16.dp)
            .height(48.dp)
            .width(250.dp),
        content = { Text(text = "Meet Robert in a video call !") },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    )
}
