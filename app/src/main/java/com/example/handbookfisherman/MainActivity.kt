package com.example.handbookfisherman

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content_rv.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var adapterMain: FishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener(this)

        var listArray = ArrayList<ListItemInfo>()

        listArray.addAll(
            fillArrays(
                resources.getStringArray(R.array.fish),
                resources.getStringArray(R.array.fish_content),
                getImageId(R.array.fish_image_array)
            )
        )

        rc_view.hasFixedSize()
        rc_view.layoutManager = LinearLayoutManager(this)
        adapterMain = FishAdapter(listArray, this)
        rc_view.adapter = adapterMain
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_fish -> {
                Toast.makeText(this, "Переходим в рыбки", Toast.LENGTH_SHORT).show()
                adapterMain.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.fish),
                        resources.getStringArray(R.array.fish_content),
                        getImageId(R.array.fish_image_array)
                    )
                )
            }
            R.id.id_na -> {
                Toast.makeText(this, "Переходим в наживки", Toast.LENGTH_SHORT).show()
                adapterMain.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.na),
                        resources.getStringArray(R.array.na_content),
                        getImageId(R.array.na_image_array)
                    )
                )
            }
            R.id.id_sna -> {
                Toast.makeText(this, "Переходим в снасти", Toast.LENGTH_SHORT).show()
                adapterMain.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.sna),
                        resources.getStringArray(R.array.sna_content),
                        getImageId(R.array.sna_image_array)
                    )
                )
            }
            R.id.id_history -> {
                Toast.makeText(
                    this, "Переходим в интересные истории", Toast.LENGTH_SHORT
                ).show()
                adapterMain.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.history),
                        resources.getStringArray(R.array.history_content),
                        getImageId(R.array.history_image_array)
                    )
                )
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fillArrays(
        titleArray: Array<String>,
        contentArray: Array<String>,
        imageArray: IntArray
    ): List<ListItemInfo> {
        var listItemArray = ArrayList<ListItemInfo>()
        for (n in 0..titleArray.size - 1) {
            var listItem = ListItemInfo(imageArray[n], titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
    //фун которая будет заполнять массив в категории меню

    private fun getImageId(imageArrayId: Int): IntArray {
        var typeArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = typeArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = typeArray.getResourceId(i, 0)
        }
        typeArray.recycle()
        return ids
    }
    // получение Int значения из массива в value преобразование так сказать из стринга
}