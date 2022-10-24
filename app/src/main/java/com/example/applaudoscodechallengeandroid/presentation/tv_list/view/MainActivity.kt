package com.example.applaudoscodechallengeandroid.presentation.tv_list.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import com.example.applaudoscodechallengeandroid.presentation.tv_detail.DetailActivity
import com.example.applaudoscodechallengeandroid.presentation.tv_list.viewmodel.TvListViewModel
import com.example.applaudoscodechallengeandroid.ui.theme.ApplaudosCodeChallengeAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplaudosCodeChallengeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SetView{
                        startActivity(DetailActivity.newIntent(this, it))
                    }

                }
            }
        }
    }

}

@Composable
fun SetView(tvListViewModel: TvListViewModel = hiltViewModel(), navigateToDetail: (TvInfo) -> Unit) {

    TvList(tvList = tvListViewModel.tvListResponse, navigateToDetail)
    tvListViewModel.getTvList()

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TvList(tvList: List<TvInfo>, navigateToDetail: (TvInfo) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tv Shows")
                },
                actions = {
                    IconButton(
                        onClick = { }) {
                        Icon(Icons.Filled.Search, "search")
                    }
                }
            )
        },
        content = {
            var selectedIndex by remember { mutableStateOf(-1) }

            LazyColumn {

                itemsIndexed(items = tvList) { index, item ->
                    TvItem(tvShow = item, index, selectedIndex, navigateToDetail  ) { i ->
                        selectedIndex = i
                    }
                }
            }
        }
    )

}

@Composable
fun TvItem(
    tvShow: TvInfo,
    index: Int,
    selectedIndex: Int,
    navigateToDetail: (TvInfo) -> Unit,
    onClick: (Int) -> Unit
) {

    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .clickable{ navigateToDetail(tvShow) }
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = tvShow.image,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.ic_baseline_cloud_download_24)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = tvShow.image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = tvShow.title!!,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
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
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplaudosCodeChallengeAndroidTheme {
        //TvList(emptyList(), null)
    }
}