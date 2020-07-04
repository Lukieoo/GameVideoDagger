package com.anioncode.gamevideodagger.main.filterFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.dataState.DataWithStates
import com.anioncode.gamevideodagger.model.genresModel.genresModel
import com.anioncode.gamevideodagger.model.plaformModel.platformModel
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilterViewModel : ViewModel {

    private val authApi: AuthApi

    private val gameInfo: MediatorLiveData<SearchModel> = MediatorLiveData<SearchModel>()
    private val gameGenre: MediatorLiveData<genresModel> = MediatorLiveData<genresModel>()
    private val gamePlatform: MediatorLiveData<platformModel> = MediatorLiveData<platformModel>()

    @Inject constructor( authApis: AuthApi) {

        authApi = authApis
    }

    fun getInfoAboutGame(page: String,search:String,page_number:Int) {
        val source: LiveData<DataWithStates<SearchModel>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search,page_number).map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<DataWithStates<SearchModel>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.value=t.data
            gameInfo.removeSource(source)
        }
    }
    fun getInfoAboutGame(page: String,search:String,genres:String,page_number:Int) {
        val source:  LiveData<DataWithStates<SearchModel>>  =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search,genres,page_number).map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<DataWithStates<SearchModel>>(source
        ) { t ->
            gameInfo.value=t.data
            gameInfo.removeSource(source)
        }
    }
    fun getInfoAboutGamewithPlatform(
        page: String,
        search:String,
        genres: String?,
        page_number:Int,
        platform: String?
    ) {
        val source: LiveData<DataWithStates<SearchModel>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatuswithPlatform(page,search,genres,page_number,platform).map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<DataWithStates<SearchModel>>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.value=t.data
            gameInfo.removeSource(source)
        }
    }
    fun getInfoAboutGamewithPlatform2(
        page: String,
        search:String,
        page_number:Int,
        platform: String?
    ) {
        val source: LiveData<DataWithStates<SearchModel>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatuswithPlatform2(page,search,page_number,platform).map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<DataWithStates<SearchModel>>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.value = t.data
            gameInfo.removeSource(source)
        }
    }
    fun observeSingle(): LiveData<SearchModel?>? {
        return gameInfo
    }

    fun getGenreGame() {
        val source: LiveData<DataWithStates<genresModel>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getGenres().map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gameGenre.addSource<DataWithStates<genresModel>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            gameGenre.value=t.data
            gameGenre.removeSource(source)
        }
    }


    fun observeGenre(): LiveData<genresModel?>? {
        return gameGenre
    }

    fun getPlatformGame() {
        val source: LiveData<DataWithStates<platformModel>> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getPlatform().map { lstUser -> DataWithStates(lstUser) }.onErrorReturn { ex -> DataWithStates(states = ex) }
                .subscribeOn(Schedulers.io())
        )
        gamePlatform.addSource<DataWithStates<platformModel>>(source
        ) { t ->
            //   Log.d("TAG", "VideoonChanged: $t")
            gamePlatform.value=t.data
            gamePlatform.removeSource(source)
        }
    }


    fun observePlatform(): LiveData<platformModel?>? {
        return gamePlatform
    }




}