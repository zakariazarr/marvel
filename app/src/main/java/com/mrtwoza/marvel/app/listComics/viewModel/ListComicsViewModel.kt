package com.mrtwoza.marvel.app.listComics.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrtwoza.marvel.app.listComics.interactor.ListComicsInteractor
import com.mrtwoza.marvel.app.listComics.interactor.getListComicsRequest
import com.mrtwoza.marvel.app.listComics.model.Comic
import com.mrtwoza.marvel.core.base.fold
import com.mrtwoza.marvel.utils.Config
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListComicsViewModel @Inject constructor(
    private val interactor: ListComicsInteractor,
    private val scope: CoroutineScope
): ViewModel() {

    private val comics = MutableLiveData<List<Comic>>()
    private val errorMsg = MutableLiveData<String>()

    private val MAX_TO_GET = 20
    private var limit = MAX_TO_GET
    private var offset = 0
    private var countItems = 0

    private var HAS_NEW_DATA = true

    init {
        loadComics()
    }

    fun getListComics(): LiveData<List<Comic>> {
        return comics
    }

    fun getErrorMsg(): LiveData<String>{
        return errorMsg
    }

    fun loadComics(){
        var request = getListComicsRequest(
                apiKey = Config.API_KEY_PUBLIC,
                hash = Config.HASH,
                limit = limit,
                offset = offset,
                ts = Config.TS
        )

        scope.launch {
            interactor.execute(request).fold(
                    onSuccess = {
                        countItems += it.data.count
                        HAS_NEW_DATA = countItems<it.data.total
                        comics.value = it.data.results

                        Log.i("LoadData","countItems : $countItems - HAS_NEW_DATA : $HAS_NEW_DATA")
                    },
                    onError = {
                       errorMsg.value = it.message
                    }
            )
        }
    }

    fun loadData(){

         if (!HAS_NEW_DATA) return

        offset += MAX_TO_GET

        Log.i("LoadData 2","offset : $offset - limit : $limit")

        loadComics()
    }
}