package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_first_name, tv_first_name_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_first_name = findViewById(R.id.tv_first_name);
        tv_first_name_new = findViewById(R.id.tv_first_name_new);

        // Old Form
//        final UserViewModel viewModel1 = ViewModelProviders.of(this).get(UserViewModel.class);
        final UserViewModel viewModel2 = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);

        viewModel2.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // update ui.
                tv_first_name.setText(user.getFirstName());
            }
        });

        // New Form
        UserViewModel viewModels = new UserViewModel();

        viewModels.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // update ui.
                tv_first_name_new.setText(user.getFirstName() + " " + user.getLastName());
            }
        });

/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // In Fragment

    // Old Form
    UserModel userModel = ViewModelProviders.of(getActivity()).get(UserModel.class);

    // New Form
    UserViewModel viewModels = new UserViewModel();
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/
    }
}