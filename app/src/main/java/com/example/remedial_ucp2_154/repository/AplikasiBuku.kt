package com.example.remedial_ucp2_154.repository

import android.app.Application

class AplikasiBuku : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerApp(this)
    }
}