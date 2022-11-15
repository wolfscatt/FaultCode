package com.tufar.faultcode.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.service.CombiDatabase
import kotlinx.coroutines.launch

class CombiFaultListViewModel(application: Application) : BaseViewModel(application) {

    val combiLiveData = MutableLiveData<List<Combi>>()

    fun getRoomData(modelName : String?){
        launch {
            val dao = CombiDatabase(getApplication()).combiDao()
            val combiList = dao.getCombiFault(modelName)
            combiLiveData.value = combiList
        }
    }
}