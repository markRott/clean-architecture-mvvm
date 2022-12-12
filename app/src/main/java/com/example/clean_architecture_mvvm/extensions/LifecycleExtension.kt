package com.example.clean_architecture_mvvm.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.observe(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend (CoroutineScope) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            block.invoke(this)
        }
    }
}
