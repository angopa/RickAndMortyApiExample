package com.example.paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging.data.paging.CharactersPagingDataSource
import com.example.paging.data.service.CharacterApi
import com.example.paging.model.Character
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImp(
    private val service: CharacterApi
    ): CharacterRepository {

    override suspend fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 10),
        pagingSourceFactory = { CharactersPagingDataSource(service) }
    ).flow

    override suspend fun getSingleCharacter(id: Int) = service.getSingleCharacter(id)

    override suspend fun getMultipleCharacters(ids: List<Int>) = service.getMultipleCharacters(ids)

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) = service.filterCharacters(
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender)
}