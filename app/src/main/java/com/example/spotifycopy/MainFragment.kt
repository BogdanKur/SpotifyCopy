package com.example.spotifycopy

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spotifycopy.databinding.FragmentMainBinding
import com.yandex.authsdk.YandexAuthSdk

const val REQUEST_LOGIN_SDK = 1337
const val BOT_DEEP_LINK = "https://t.me/music_yandex_bot?start="
const val SOURCE_CODE_LINK = "https://github.com/MarshalX/yandex-music-token"

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val token: MutableState<String> = mutableStateOf("")
    private lateinit var sdk: YandexAuthSdk

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        binding.bottomNav.setupWithNavController(navController)


        return view
    }

    private fun openUriInBrowser(uriString: String, context: Context) {
        val uri = Uri.parse(uriString)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    private fun copyToken(context: Context) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("token", token.value)
        clipboardManager.setPrimaryClip(clip)
    }


}