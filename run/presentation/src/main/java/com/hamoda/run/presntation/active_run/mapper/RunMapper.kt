package com.hamoda.run.presntation.active_run.mapper

import com.hamoda.core.domain.run.Run
import com.hamoda.core.domain.util.formatted
import com.hamoda.core.domain.util.toFormattedKm
import com.hamoda.core.domain.util.toFormattedKmh
import com.hamoda.core.domain.util.toFormattedMeters
import com.hamoda.core.domain.util.toFormattedPace
import com.hamoda.run.presntation.active_run.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())

    val formattedDateTime = DateTimeFormatter
        .ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)
    val distanceKm = distanceMeters / 1000.0

    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formattedDateTime,
        distance = distanceKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl
    )
}