@file:OptIn(ExperimentalFoundationApi::class)

package ru.noxis.flipboardanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.noxis.flipboardanimation.data.headlines
import ru.noxis.flipboardanimation.flip.FlipPager
import ru.noxis.flipboardanimation.flip.FlipPagerOrientation
import ru.noxis.flipboardanimation.ui.theme.FlipBoardAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

@Composable
private fun App() {
    val isSystemDark = isSystemInDarkTheme()
    var darkMode by remember {
        mutableStateOf(isSystemDark)
    }
    FlipBoardAnimationTheme(darkTheme = darkMode) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .navigationBarsPadding()
            ) {
                var orientation: FlipPagerOrientation by remember {
                    mutableStateOf(FlipPagerOrientation.Vertical)
                }
                val state = rememberPagerState { headlines.size }

                FlipPager(
                    state = state,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    orientation = orientation,
                ) { page ->
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    ) {
                        HeadlineArticle(
                            modifier = Modifier.align(Alignment.Center),
                            headline = headlines[page],
                        )
                    }
                }
            }

        }
    }
}

