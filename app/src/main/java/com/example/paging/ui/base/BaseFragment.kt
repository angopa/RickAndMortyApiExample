package com.example.paging.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.paging.ext.observe

abstract class BaseFragment<DB: ViewDataBinding, VM: BaseViewModel> : Fragment() {
    private lateinit var viewModel: VM
    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, vm: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVM(binding, viewModel)
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) (activity as BaseActivity<*, *>).showProgress()
                else (activity as BaseActivity<*, *>).hideProgress()
            }

            observe(errorMessage) { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute
        }
    }
}