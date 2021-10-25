package com.example.paging.ui.characters

import com.example.paging.databinding.FragmentCharactersBinding
import com.example.paging.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment: BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    private val charactersViewModel
    @Inject
    lateinit var characterAdapter: CharacterAdapter
}