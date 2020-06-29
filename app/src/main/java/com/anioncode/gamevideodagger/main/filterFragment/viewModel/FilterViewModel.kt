package com.anioncode.gamevideodagger.main.filterFragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.anioncode.gamevideodagger.model.detailModel.InfoGame
import com.anioncode.gamevideodagger.model.genresModel.genresModel
import com.anioncode.gamevideodagger.model.searchModel.SearchModel
import com.anioncode.gamevideodagger.network.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilterViewModel : ViewModel {

    private val authApi: AuthApi

    private val gameInfo: MediatorLiveData<SearchModel> = MediatorLiveData<SearchModel>()
    private val gameGenre: MediatorLiveData<genresModel> = MediatorLiveData<genresModel>()

    @Inject constructor( authApis: AuthApi) {

        authApi = authApis
    }

    fun getInfoAboutGame(page: String,search:String) {
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search)
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
    fun getInfoAboutGame(page: String,search:String,genres:String) {
        val source: LiveData<SearchModel> =  LiveDataReactiveStreams.fromPublisher(
            authApi.getSearchStatus(page,search,genres)
                .subscribeOn(Schedulers.io())
        )
        gameInfo.addSource<SearchModel>(source
        ) { t -> //   Log.d("TAG", "VideoonChanged: $t")
            gameInfo.setValue(t)
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





}