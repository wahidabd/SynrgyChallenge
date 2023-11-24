package com.wahidabd.synrgy.presentation.user

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.work.WorkInfo
import com.wahidabd.common.R
import com.wahidabd.common.utils.Constants
import com.wahidabd.common.utils.onBackPress
import com.wahidabd.common.utils.requestPermission
import com.wahidabd.domain.auth.AuthUser
import com.wahidabd.synrgy.databinding.ActivityUserBinding
import com.wahidabd.synrgy.presentation.auth.AuthViewModel
import com.wahidabd.synrgy.presentation.auth.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UserActivity : AppCompatActivity() {


    companion object {
        private const val REQUEST_CODE_PERMISSION = 112
        private const val PHOTO_FILE_PREFIX = "IMG_"
        private const val PHOTO_FILE_SUFFIX = ".png"

        fun start(context: Context) {
            context.startActivity(Intent(context, UserActivity::class.java))
        }
    }

    // permissions
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val permissionsAndroid13 = arrayOf(
        Manifest.permission.CAMERA,
    )

    private val launchCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture(),
        ::handleCameraResult
    )

    private var imageUri: Uri? = null
    private val authViewModel: AuthViewModel by inject()
    private val viewModel: UserViewModel by viewModel()
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserInformation()
        viewModel.getImage()

        initListener()
        initObservers()
    }

    private fun initListener() = with(binding) {
        ivBack.setOnClickListener { onBackPress() }
        ivProfile.setOnClickListener { checkPermissions() }
        ivLogout.setOnClickListener {
            authViewModel.setLogin(false)
            LoginActivity.start(this@UserActivity)
            finish()
        }
    }

    private fun initObservers() {
        viewModel.image.observe(this, ::showImageProfile)
        viewModel.user.observe(this, ::showUserInformation)
        viewModel.getWorkManagerLiveData().observe(this, ::handleWorker)
    }

    private fun handleWorker(workers: List<WorkInfo>) {
        if (workers.isEmpty()) return

        val info = workers.last()
        if (info.state.isFinished) {
            val outputImage = info.outputData.getString(Constants.OUTPUT_IMAGE)
            if (!outputImage.isNullOrEmpty()) {
                viewModel.saveImage(Uri.parse(outputImage))
                viewModel.getImage()
            }
        }
    }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            PHOTO_FILE_PREFIX,
            PHOTO_FILE_SUFFIX,
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        )
        imageUri = FileProvider.getUriForFile(
            this,
            "${this.packageName}.provider",
            photoFile
        )

        launchCamera.launch(imageUri)
    }

    private fun handleCameraResult(result: Boolean) {
        if (!result) return;
        viewModel.saveImage(imageUri)
        viewModel.getImage()
    }

    private fun showImageProfile(image: String?) = with(binding) {
        if (image.isNullOrEmpty()) ivProfile.setImageResource(R.drawable.img_logo)
        ivProfile.setImageURI(Uri.parse(image))
    }

    private fun showUserInformation(user: AuthUser) {
        binding.tvName.text = user.name
    }

    private fun checkPermissions() {
        requestPermission(
            permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionsAndroid13
            } else {
                permissions
            },
            requestCode = REQUEST_CODE_PERMISSION,
            doIfGranted = ::openCamera,
        )
    }
}