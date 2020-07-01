package com.anioncode.gamevideodagger.main.filterFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
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
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search,page_number)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<SearchModel>(source, object : androidx.lifecycle.Observer<SearchModel?> {

            override fun onChanged(t: SearchModel?) {
             //   Log.d("TAG", "VideoonChanged: $t")
                gameInfo.setValue(t)
                gameInfo.removeSource(source)
            }
        })
    }
    fun getInfoAboutGame(page: String,search:String,genres:String,page_number:Int) {
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search,genres,page_number)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<SearchModel>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.setValue(t)
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
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatuswithPlatform(page,search,genres,page_number,platform)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<SearchModel>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.setValue(t)
            gameInfo.removeSource(source)
        }
    }
    fun getInfoAboutGamewithPlatform2(
        page: String,
        search:String,
        page_number:Int,
        platform: String?
    ) {
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatuswithPlatform2(page,search,page_number,platform)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<SearchModel>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.value = t
            gameInfo.removeSource(source)
        }
    }
    fun observeSingle(): LiveData<SearchModel?>? {
        return gameInfo
    }

    fun getGenreGame() {
        val source: LiveData<genresModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getGenres()
                .subscribeOn(Schedulers.io())
        )
        gameGenre.addSource<genresModel>(source, object : androidx.lifecycle.Observer<genresModel?> {

            override fun onChanged(t: genresModel?) {
                //   Log.d("TAG", "VideoonChanged: $t")
                gameGenre.setValue(t)
                gameGenre.removeSource(source)
            }
        })
    }


    fun observeGenre(): LiveData<genresModel?>? {
        return gameGenre
    }

    fun getPlatformGame() {
        val source: LiveData<platformModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getPlatform()
                .subscribeOn(Schedulers.io())
        )
        gamePlatform.addSource<platformModel>(source, object : androidx.lifecycle.Observer<platformModel?> {

            override fun onChanged(t: platformModel?) {
                //   Log.d("TAG", "VideoonChanged: $t")
                gamePlatform.setValue(t)
                gamePlatform.removeSource(source)
            }
        })
    }


    fun observePlatform(): LiveData<platformModel?>? {
        return gamePlatform
    }




}