package com.bojanvilic.crvenazvezdainfo.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.core.text.HtmlCompat
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home_page.*
import java.net.URLEncoder
import java.util.*

class MainPageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var disposable: Disposable? = null

    private val apiService by lazy {
        IApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        getImage()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_football, R.id.nav_basketball,
                R.id.nav_other, R.id.nav_serbia, R.id.nav_contact, R.id.nav_shop
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

//    private fun readPosts() {
//        disposable = apiService.getArticlesList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    var i = 0
//                    while (i < result.size){
//                        val decoded = HtmlCompat.fromHtml(result[0].content.article_text, HtmlCompat.FROM_HTML_MODE_LEGACY)
//                        text_home.append(decoded)
//                        i++
//                } },
//                { error -> Log.e("ERROR", error.message.toString()) }
//            )
//    }

    private fun getImage() {
        disposable = apiService.getImage(11896)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("SLIKA", result.guid.imageUrl)
                },
                { error -> Log.e("ERROR", error.message.toString()) }
            )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_page, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
