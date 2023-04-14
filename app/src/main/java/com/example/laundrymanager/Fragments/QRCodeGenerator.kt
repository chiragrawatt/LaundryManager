package com.example.laundrymanager.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.laundrymanager.R
import com.example.laundrymanager.databinding.FragmentQRCodeGeneratorBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


class QRCodeGenerator : Fragment() {

    private lateinit var binding: FragmentQRCodeGeneratorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQRCodeGeneratorBinding.inflate(layoutInflater)

        binding.buttonGenerateQr.setOnClickListener {
            val text = binding.editText.text.toString()
            try {
                if (text.isNotEmpty()) {
                    val qrCode = generateQrCode(text)
                    binding.imageViewQrCode.setImageBitmap(qrCode)
                    binding.imageViewQrCode.visibility = View.VISIBLE
                } else {
                    Toast.makeText(context, "Enter some text", Toast.LENGTH_SHORT).show()
                }
            }catch (ex: Exception) {
                Log.d("testingQRGenerator", ex.toString())
            }
        }

        return binding.root
    }

    private fun generateQrCode(text: String): Bitmap? {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            return barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: WriterException) {
            Log.d("testingQRGenerator", e.toString())
            e.printStackTrace()
        }
        return null
    }
}