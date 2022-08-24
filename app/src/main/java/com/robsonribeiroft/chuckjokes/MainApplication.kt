package com.robsonribeiroft.chuckjokes

import android.app.Application
import com.robsonribeiroft.chuckjokes.data.di.dataModule
import com.robsonribeiroft.chuckjokes.domain.di.domainModule
import com.robsonribeiroft.chuckjokes.presentation.di.presentationModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalSerializationApi
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    presentationModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}