package com.practicework.weatherapp.core.locationprovider

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationProviderClient @Inject constructor(
    @ApplicationContext context: Context
) {

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onComplete: (Location) -> Unit ) {
        val cancellationToken = CancellationTokenSource()

        fusedLocationProviderClient
            .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationToken.token)
            .addOnCompleteListener {
                if (it.result != null) {
                    onComplete(it.result)
                }
            }
    }
}