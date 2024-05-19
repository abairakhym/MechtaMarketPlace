package kz.dream.marketplace.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.dream.marketplace.db.model.ItemLocalDataSource

@Database(entities = [ItemLocalDataSource::class], version = 1)
abstract class MechtaRoomDB : RoomDatabase() {
    abstract fun mechtaDao(): MechtaDao
}