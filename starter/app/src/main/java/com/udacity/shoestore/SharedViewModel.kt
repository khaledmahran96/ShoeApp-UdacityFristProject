package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel: ViewModel() {

    //     Initialization of encapsulation
    //     Login -- state for the buttons
    private val _loginButtonPressed = MutableLiveData<Boolean>()
    val loginButtonPressed: LiveData<Boolean>
        get() =_loginButtonPressed

    //    Welcome -- state for the button
    private val _nextWelcomeButtonPressed = MutableLiveData<Boolean>()
    val nextWelcomeButtonPressed: LiveData<Boolean>
        get() =  _nextWelcomeButtonPressed

    //   Instruction -- state for the button
    private val _nextInstructionButtonPressed = MutableLiveData<Boolean>()
    val nextInstructionButtonPressed: LiveData<Boolean>
        get() = _nextInstructionButtonPressed

    //  ShoeList -- state for floating button and views
    private val _floatingButtonPressed = MutableLiveData<Boolean>()
    val floatingButtonPressed: LiveData<Boolean>
        get() = _floatingButtonPressed



    //  ShoeDetail -- encapsulation
    private val _cancelButtonPressed = MutableLiveData<Boolean>()
    val cancelButtonPressed: LiveData<Boolean>
        get() = _cancelButtonPressed

    private val _saveButtonPressed = MutableLiveData<Boolean>()
    val saveButtonPressed: LiveData<Boolean>
        get() = _saveButtonPressed


    private val _list = MutableLiveData<MutableList<Shoe>>()
    val list: LiveData<MutableList<Shoe>>
        get() = _list

    //   ShoeDetail
    val shoeName = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()










    init {
        _loginButtonPressed.value = false
        _nextWelcomeButtonPressed.value = false
        _nextInstructionButtonPressed.value = false
        _floatingButtonPressed.value = false
        _cancelButtonPressed.value = false
        _saveButtonPressed.value = false
    }




    //    Required Functions for UI
    //    Login -- fun
    fun onLoginButtonPress (){
        _loginButtonPressed.value = true
    }

    fun onLoginButtonPressed (){
        _loginButtonPressed.value = false
    }

    //   Welcome -- fun
    fun onNextWelcomeButtonPress (){
        _nextWelcomeButtonPressed.value = true
    }

    fun onNextWelcomeButtonPressed (){
        _nextWelcomeButtonPressed.value = false
    }

    //   Instruction -- fun
    fun onNextInstructionButtonPress () {
        _nextInstructionButtonPressed.value = true
    }

    fun onNextInstructionButtonPressed (){
        _nextInstructionButtonPressed.value = false
    }

    //   ShoeList -- floating button fun
    fun onFloatingButtonPress (){
        _floatingButtonPressed.value = true
    }

    fun onFloatingButtonPressed (){
        _floatingButtonPressed.value = false
    }

    //  ShoeDetail -- functions
    fun onCancelButtonPress(){
        _cancelButtonPressed.value = true
    }

    fun onCancelButtonPressed(){
        _cancelButtonPressed.value = false
    }

    fun onSaveButtonPress(){
        _saveButtonPressed.value = true
    }
    fun onSaveButtonPressed(){
        _saveButtonPressed.value = false
    }

    fun isNotEmpty(): Boolean {
        if(shoeName.value?.isNotEmpty() == true &&
            shoeSize.value?.isNotEmpty() == true &&
                shoeCompany.value?.isNotEmpty() == true &&
                shoeDescription.value?.isNotEmpty() == true){
            return true
        }
        return false
    }

    fun sizeToDouble():Double {
        if (shoeSize.value != null && shoeSize.value?.isNotEmpty() == true){
            return shoeSize.value?.toDouble()!!
        }
        return 0.0
    }

    fun makeNewShoe(): Shoe {
        return Shoe(
            shoeName.value ?: "",
            sizeToDouble(),
            shoeCompany.value ?: "",
            shoeDescription.value ?: ""
        )
    }

    fun addNewShoe(shoe: Shoe){
        if (_list.value == null){
            _list.value = mutableListOf(shoe)
        }
        _list.value?.add(shoe)
    }

}