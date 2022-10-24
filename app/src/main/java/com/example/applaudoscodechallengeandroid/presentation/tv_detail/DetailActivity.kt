package com.example.applaudoscodechallengeandroid.presentation.tv_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import com.example.applaudoscodechallengeandroid.ui.theme.ApplaudosCodeChallengeAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    private val tvShow: TvInfo by lazy {
        intent?.getSerializableExtra(TV_SHOW) as TvInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplaudosCodeChallengeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TvShowDetail(tvShow = tvShow)
                }
            }
        }
    }

    companion object {
        private const val TV_SHOW = "tv_show"
        fun newIntent(context: Context, tvShow: TvInfo) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(TV_SHOW, tvShow)
            }
    }
}

@Composable
fun TvShowDetail(tvShow: TvInfo){
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    Poster(
                        tvShow,
                        this@BoxWithConstraints.maxHeight
                    )
                    TitleAndRating(tvShow)
                    Overview(label = "Overview", value = tvShow.overview!!)
                }
            }
        }
    }
}

@Composable
private fun Poster(
    tvShow: TvInfo,
    containerHeight: Dp
) {
    Image(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        painter = rememberImagePainter(
            data = tvShow.image,
            builder = {
                scale(Scale.FILL)
                placeholder(R.drawable.ic_baseline_cloud_download_24)
            }
        ),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun TitleAndRating(
    tvShow: TvInfo
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
        Text(
            text = tvShow.title!!,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        Row() {
            Text(
                text = "Rating: ",
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = tvShow.rating!!,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(
                        Color.LightGray
                    )
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun Overview(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption,
        )
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplaudosCodeChallengeAndroidTheme {

    }
}