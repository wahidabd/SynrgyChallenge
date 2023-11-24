package com.wahidabd.synrgy.presentation.user

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.wahidabd.common.utils.Constants
import com.wahidabd.domain.auth.AuthUser
import com.wahidabd.synrgy.utils.AuthDataStore
import com.wahidabd.synrgy.utils.BlurUtil
import kotlinx.coroutines.launch


/**
 * Created by wahid on 11/24/2023.
 * Github github.com/wahidabd.
 */


class UserViewModel(
    private val authDataStore: AuthDataStore,
    private val workManager: WorkManager
) : ViewModel() {

    private val _image = MutableLiveData<String?>()
    val image: MutableLiveData<String?> get() = _image

    private val _user = MutableLiveData<AuthUser>()
    val user: MutableLiveData<AuthUser> get() = _user

    fun getUserInformation() {
        viewModelScope.launch {
            authDataStore.getUserInformation().collect {
                _user.value = it
            }
        }
    }

    fun saveImage(uri: Uri?) {
        blurImage(uri)
        viewModelScope.launch {
            authDataStore.setImage(uri.toString())
        }
    }

    fun getImage() {
        viewModelScope.launch {
            authDataStore.getImage().collect {
                _image.value = it
            }
        }
    }


    private fun blurImage(uri: Uri?){
        val inputData = Data.Builder()
            .putString(Constants.INPUT_IMAGE, uri.toString())
            .build()

        val blurWorkRequest = OneTimeWorkRequestBuilder<BlurUtil>()
            .setInputData(inputData)
            .addTag(Constants.OUTPUT_IMAGE)
            .build()

        workManager.enqueue(blurWorkRequest)
    }

    fun getWorkManagerLiveData() =
        workManager.getWorkInfosByTagLiveData(Constants.OUTPUT_IMAGE)
}