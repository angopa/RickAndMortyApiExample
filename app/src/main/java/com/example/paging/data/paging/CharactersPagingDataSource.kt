package com.example.paging.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.data.service.CharacterApi
import com.example.paging.model.Character

class CharactersPagingDataSource(
    private val service: CharacterApi
    ): PagingSource<Int, Character>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key?: 1
        return try {
            val response = service.getAllCharacters(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPagerNumber: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery= uri.getQueryParameter("page")
                nextPagerNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPagerNumber
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1
}