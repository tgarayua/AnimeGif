package com.example.animegif.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.animegif.adapter.AnnieGifAdapter
import com.example.animegif.databinding.ActivityCategoryBinding
import com.example.animegif.viewmodel.GifViewModel


class CategoryActivity : AppCompatActivity() {
    private val viewModel by viewModels<GifViewModel>()
    private val binding by lazy{ ActivityCategoryBinding.inflate(layoutInflater)}
    private val path = arrayListOf("baka", "bite", "blush", "bored", "cry", "cuddle",
        "dance", "facepalm", "feed", "happy", "highfive", "hug", "kiss", "laugh",
        "pat", "poke", "pout", "shrug", "slap", "sleep", "smile", "smug", "stare",
        "think", "thumbsup", "tickle", "wave", "wink")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.spCategory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, path)

        initViews()
        viewModel.gifObj.observe(this){
            (binding.rvGif.adapter as AnnieGifAdapter).updateGifInfoList(it)
        }
    }

    private fun initViews() = with(binding) {
        rvGif.adapter = AnnieGifAdapter()
        btnSubmit.setOnClickListener {
            if (etAmount.text.toString() != ""){
                viewModel.fetchData(spCategory.selectedItem.toString(),etAmount.text.toString().toInt())
            }else{
                viewModel.fetchData(spCategory.selectedItem.toString(),0)
            }
        }
    }
}
