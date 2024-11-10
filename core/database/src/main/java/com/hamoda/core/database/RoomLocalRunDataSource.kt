package com.hamoda.core.database

import android.database.sqlite.SQLiteFullException
import com.hamoda.core.database.dao.RunDao
import com.hamoda.core.database.mapper.toRun
import com.hamoda.core.database.mapper.toRunEntity
import com.hamoda.core.domain.run.LocalRunDataSource
import com.hamoda.core.domain.run.Run
import com.hamoda.core.domain.run.RunId
import com.hamoda.core.domain.util.DataError
import com.hamoda.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalRunDataSource(
    private val runDao: RunDao
) : LocalRunDataSource {
    override fun getRun(): Flow<List<Run>> {
        return runDao.getRuns().map { runEntity ->
            runEntity.map { it.toRun() }
        }
    }

    override suspend fun upsertRun(run: Run): Result<RunId, DataError.Local> {
        return try {
            val entity = run.toRunEntity()
            runDao.upsertRun(entity)
            Result.Success(entity.id)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertRuns(runs: List<Run>): Result<List<RunId>, DataError.Local> {
        return try {
            val entities = runs.map { it.toRunEntity() }
            runDao.upsertRuns(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteRun(id: String) {
        runDao.deleteRun(id = id)
    }

    override suspend fun deleteAllRuns() {
        runDao.deleteAllRun()
    }
}