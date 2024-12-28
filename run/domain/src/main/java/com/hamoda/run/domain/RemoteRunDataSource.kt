package com.hamoda.run.domain

import com.hamoda.core.domain.run.Run
import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.EmptyDataResult
import com.hamoda.core.domain.util.Result

interface RemoteRunDataSource {
    suspend fun getRuns(): Result<List<Run>, DataError.Network>

    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>

    suspend fun deleteRun(id: String): EmptyDataResult<DataError.Network>
}