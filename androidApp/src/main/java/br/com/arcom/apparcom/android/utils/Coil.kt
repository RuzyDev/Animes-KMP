package br.com.arcom.apparcom.android.utils

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import java.io.File

fun buildImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        // --- Cache em Memória ---
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.25)
                .build()
        }
        // --- Cache em Disco ---
        .diskCache {
            DiskCache.Builder()
                .directory(File(context.cacheDir, "image_cache"))
                .maxSizeBytes(250L * 1024 * 1024) // 250MB
                .build()
        }
        // --- Configurações ---
        .respectCacheHeaders(false)
        .crossfade(200)
        .allowHardware(true)
        .build()
}