package com.tufar.faultcode.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tufar.faultcode.model.Combi
import com.tufar.faultcode.service.CombiDatabase
import kotlinx.coroutines.launch

class CombiDescViewModel (application: Application) : BaseViewModel(application) {

    val combiLiveData = MutableLiveData<Combi>()

    fun getRoomData(faultCode : String?){
        launch {
            val dao = CombiDatabase(getApplication()).combiDao()
            val combi = dao.getCombiDesc(faultCode)
            combiLiveData.value = combi
        }
    }
}