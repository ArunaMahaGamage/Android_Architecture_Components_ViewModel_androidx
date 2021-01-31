package com.example.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    public final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public UserViewModel() {
        // trigger user load.
//        doAction();
    }

    void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
        // Do api Call
        User user = new User();
        user.setFirstName("Aruna");
        user.setLastName("Gamage");
        user.setAge(31);
        userLiveData.postValue(user);
    }

    void setUser(User user) {
        userLiveData.postValue(user);
    }
}
