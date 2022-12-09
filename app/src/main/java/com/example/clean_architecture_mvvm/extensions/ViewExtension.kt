package com.example.clean_architecture_mvvm.extensions

import android.text.Editable
import android.widget.EditText

fun EditText.data(): String = text.toString().trim()

fun Editable?.data(): String = this.toString().trim()