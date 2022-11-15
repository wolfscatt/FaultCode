package com.tufar.faultcode.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.service.CombiDatabase
import kotlinx.coroutines.launch

class CombiModelListViewModel (application: Application) : BaseViewModel(application){

    val combiLiveData = MutableLiveData<List<Combi>>()

    fun getRoomData(brandName : String?){
        launch {
            val dao = CombiDatabase(getApplication()).combiDao()
            val combiList = dao.getCombiModel(brandName)
            combiLiveData.value = combiList
        }
    }
}