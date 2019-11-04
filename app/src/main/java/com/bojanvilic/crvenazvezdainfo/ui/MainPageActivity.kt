package com.bojanvilic.crvenazvezdainfo.ui

import android.os.Bundle
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
import androidx.fragment.app.FragmentManager
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.di.Injector
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomePageFragment
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPageActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var disposable: Disposable? = null
    private var disposable2: Disposable? = null

    private val apiService by lazy {
        IApiService.create()
    }

    companion object {
        const val VIEW: String = "VIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val manager: FragmentManager = this.supportFragmentManager

        val view = manager.findFragmentByTag(VIEW) as HomePageFragment??: HomePageFragment.newInstance(
            Injector(this)
        )


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

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


    private fun readPosts() {

        disposable = apiService.getArticlesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    var i = 0
                    while (i < result.size) {
                        val decoded = HtmlCompat.fromHtml(result[0].content.article_text, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        Log.d("IMAGE", decoded.toString())
                        i++
                    }
                },
                { error -> Log.e("ERROR", error.message.toString()) }
            )
    }

    private fun getImage(id: Int) {
        disposable2 = apiService.getImage(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("IMAGE", result.guid.imageUrl)
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
