package com.example.paging.data.repository

import androidx.paging.PagingData
import com.example.paging.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacters(): Flow<PagingData<Character>>
    suspend fun getSingleCharacter(id: Int)
    suspend fun getMultipleCharacters(ids: List<Int>)
    suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    )
}