package com.tufar.faultcode.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.service.CombiAPIService
import com.tufar.faultcode.service.CombiDatabase
import com.tufar.faultcode.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CombiListViewModel(application: Application) : BaseViewModel(application) {

    val combies = MutableLiveData<List<Combi>>()
    val combiFaultMessage = MutableLiveData<Boolean>()
    val combiLoading = MutableLiveData<Boolean>()

    private var updateTime = 3000*60*1000*1000*1000L
    private val combiApiService = CombiAPIService()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())


    fun refreshData (){
        val saveTime = ozelSharedPreferences.getTime()
        if (saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime){
            getDataFromSqlite()
        }
        else{
            getDataFromWeb()
        }
    }

    fun refreshFromWeb(){
        getDataFromWeb()
    }


    private fun getDataFromSqlite(){

        combiLoading.value = true
        launch {
            val combiList = CombiDatabase(getApplication()).combiDao().getAllCombi()
            showCombies(combiList)
            Toast.makeText(getApplication(),"Verileri Sqlitedan aldık.",Toast.LENGTH_LONG).show()

        }
    }

    private fun getDataFromWeb(){

        combiLoading.value = true

        disposable.add(
            combiApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Combi>>(){
                    override fun onSuccess(t: List<Combi>) {
                        hideSqlite(t)
                        Toast.makeText(getApplication(),"Verileri Webden aldık.",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        combiLoading.value = false
                        combiFaultMessage.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showCombies(combiList : List<Combi>){
        combiLoading.value = false
        combiFaultMessage.value = false
        combies.value = combiList

    }

    private fun hideSqlite(combiList : List<Combi>){

        launch {
            val dao = CombiDatabase(getApplication()).combiDao()
            dao.deleteAllCombi()
            val uuidList = dao.insertAll(*combiList.toTypedArray())   // Liste içerisindeki elemanları tek tek insert ediyor
            val brandList = dao.insertAll(*combiList.toTypedArray())

            var i = 0
            while (i<combiList.size){
                combiList[i].uuid = uuidList[i].toInt()
                i += 1
            }
            showCombies(combiList)
        }
        ozelSharedPreferences.saveTime(System.nanoTime())
    }

    private fun listOfSet(combiList : List<Combi>) : List<Combi>{
        var newList = mutableListOf<Combi>()
        for(i in combiList){
            if (newList.contains(i)){
                newList.add(i)
            }
        }
        return newList
    }
}