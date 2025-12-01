package com.oglcnkrty.todo_app.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository
@Inject constructor(
     localDataSource: LocalDataSource
) {

     val localDataSource=localDataSource

}