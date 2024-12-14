package com.jabillo.csc422midterm

import android.app.Application
import com.jabillo.csc422midterm.data.users.model.LoggedInUserHolder

class KtorApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        LoggedInUserHolder.init(this)
        container = AppDataContainer(this)
    }
}