package com.example.pertemuan10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.example.pertemuan10.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nama = intent.getStringExtra("nama")
        val karakter = intent.getStringExtra("karakter")
        val deskripsi = intent.getStringExtra("deskripsi")
        val foto = intent.getStringExtra("foto")

        binding.tvDetailNama.text = nama
        binding.chipDetailKarakter.text = karakter
        binding.tvDetailDeskrip.text = deskripsi

        val circularProgressDrawable = CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        binding.ivDetailGambar.load(foto) {
            crossfade(true)
            placeholder(circularProgressDrawable)
            error(R.drawable.ic_launcher_foreground)
        }
    }
}