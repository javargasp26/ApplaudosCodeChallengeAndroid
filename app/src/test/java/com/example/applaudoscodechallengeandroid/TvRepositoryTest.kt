package com.example.applaudoscodechallengeandroid

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.applaudoscodechallengeandroid.data.local.tv.TvDao
import com.example.applaudoscodechallengeandroid.data.local.tv.TvEntity
import com.example.applaudoscodechallengeandroid.data.network.tv_list.TvListApi
import com.example.applaudoscodechallengeandroid.data.repository.TvRepository
import com.example.applaudoscodechallengeandroid.data.repository.TvRepositoryImpl
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import com.example.applaudoscodechallengeandroid.domain.toDomain
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

private val tv1 = TvEntity(1, 1,"House of Dragon", "http://..", "8", "show overview")
private val tv2 = TvEntity(2, 2,"House", "http://..", "7", "show overview")

class TvRepositoryTest {
    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }
    private val tvListApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TvListApi::class.java)

    private val tvRepository = TvRepositoryImpl(tvListApi, MockTvDao())

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `TvShows on the DB are retrieved correctly`() {
        val tvShow = mutableListOf<TvEntity>(tv1, tv2)
        assertEquals(2, tvShow.size)
    }

}

class MockTvDao : TvDao {

    private val tvShows = mutableListOf<TvEntity>(tv1, tv2)


    override suspend fun getAllTvs(): List<TvEntity> = tvShows

    override suspend fun insertAllTv(tvList: List<TvEntity>) {
        tvShows.add(tv1)
    }

    override suspend fun clearTvTable() {
        tvShows.clear()
    }


}

val myDispatcher: Dispatcher = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/tv/popular" -> MockResponse().apply { addResponse("apiresponse.json") }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}