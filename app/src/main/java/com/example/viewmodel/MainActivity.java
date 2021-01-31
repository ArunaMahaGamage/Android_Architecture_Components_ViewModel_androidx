package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_first_name, tv_first_name_new;
    Button btn_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_first_name = findViewById(R.id.tv_first_name);
        tv_first_name_new = findViewById(R.id.tv_first_name_new);
        btn_data = findViewById(R.id.btn_data);

        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // update ui.
                tv_first_name.setText(user.getFirstName() + " " + user.getLastName());
            }
        });

        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.doAction();
                tv_first_name_new.setText("Portrait and landscape layout change");
            }
        });

        Log.e("MainActivity ", "hashCode_view_model:" + viewModel.hashCode());

/*
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // In Fragment

        UserViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
*/
    }
}