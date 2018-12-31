package me.fonix232.common.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseViewModel: ViewModel(), KoinComponent {
    protected val context: Context by inject()
}