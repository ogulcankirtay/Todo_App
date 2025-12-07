    package com.oglcnkrty.todo_app.di

    import android.content.Context
    import androidx.room.Room
    import com.oglcnkrty.todo_app.data.ToddDatabase
    import com.oglcnkrty.todo_app.utilities.Constants.DATABASE_NAME
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.android.qualifiers.ApplicationContext
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton


    @Module
    @InstallIn(SingletonComponent::class)
    object DataBaseModule {

        @Singleton
        @Provides
        fun provideTodoDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
            context, ToddDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

        @Singleton
        @Provides
        fun provideToDoDao(database: ToddDatabase) = database.todoDao()

    }