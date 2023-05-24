package com.goms.presentation.view.qr_scan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.BuildConfig
import com.example.presentation.databinding.FragmentQrScanBinding
import com.goms.presentation.utils.apiErrorHandling
import com.goms.presentation.utils.checkUserIsAdmin
import com.goms.presentation.view.qr_scan.capture.QrCodeActivity
import com.goms.presentation.viewmodel.CouncilViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.UUID

@AndroidEntryPoint
class QrScanFragment : Fragment() {
    private val councilViewModel by viewModels<CouncilViewModel>()
    private lateinit var binding: FragmentQrScanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View {
        binding = FragmentQrScanBinding.inflate(layoutInflater)

        if (!checkUserIsAdmin(requireContext()))
            binding.qrScanAdminView.visibility = View.GONE

        setLoading()
        lifecycleScope.launch {
            makeQr()
        }

        return binding.root
    }

    private fun createQrCode(uuid: UUID?) {
        val barCodeEncoder = BarcodeEncoder()
        val bitmap = barCodeEncoder.encodeBitmap(BuildConfig.BASE_URL+uuid, BarcodeFormat.QR_CODE, 300, 300)
        binding.outingQrCodeImage.setImageBitmap(bitmap)
    }

    private suspend fun startTimer() {
        councilViewModel.setTimer(300)
        councilViewModel.scanTime.collect { time ->
            if (time != null)  {
                val min = time / 60
                val sec = (time % 60).let { if (it < 10) "0$it" else "$it" }
                binding.qrScanTimeLeftText.text = "${min}분 ${sec}초"
            } else makeQr()
        }
    }

    private suspend fun makeQr() {
        lifecycleScope.launch {
            apiErrorHandling(
                context = context,
                logic = { qrLogic() }
            )
        }
    }

    private suspend fun qrLogic() {
        if (checkUserIsAdmin(requireContext())) {
            councilViewModel.makeQrCode()
            councilViewModel.makeQr.collect { uuid ->
                if (uuid != null) {
                    createQrCode(uuid.outingUUID)
                    startTimer()
                }
            }
        } else context?.startActivity(Intent(context, QrCodeActivity::class.java))
    }

    private fun setLoading() {
        lifecycleScope.launch {
            councilViewModel.isLoading.collect { loading ->
                if (loading) {
                    binding.qrScanLoadingIndicator.root.visibility = View.VISIBLE
                    binding.qrScanMainView.visibility = View.GONE
                } else {
                    binding.qrScanLoadingIndicator.root.visibility = View.GONE
                    binding.qrScanMainView.visibility = View.VISIBLE
                }
            }
        }
    }
}
