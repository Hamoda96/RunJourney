package com.hamoda.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamoda.core.database.dao.RunDao
import com.hamoda.core.database.dao.RunPendingSyncDao
import com.hamoda.core.database.entity.DeletedRunSyncEntity
import com.hamoda.core.database.entity.RunEntity
import com.hamoda.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {
    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
}