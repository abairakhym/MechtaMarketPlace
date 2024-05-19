package kz.dream.marketplace.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.dream.marketplace.db.MechtaRoomDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideMechtaRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MechtaRoomDB::class.java, "mechta_room_db").build()

    @Provides
    @Singleton
    fun provideMechtaProductDao(roomDB: MechtaRoomDB) = roomDB.mechtaDao()
}