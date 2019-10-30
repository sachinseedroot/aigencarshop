package com.aigen.carshop.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aigen.carshop.BaseFragment;
import com.aigen.carshop.R;
import com.aigen.carshop.activities.MainActivity;
import com.aigen.carshop.utilities.appsharedprefsettings;

public class loginfragment extends BaseFragment {


    private TextView input_email, input_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getView() != null ? getView() : inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input_email = (TextView) view.findViewById(R.id.input_email);
        input_password = (TextView) view.findViewById(R.id.input_password);

        Button button = (Button) view.findViewById(R.id.loginbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View views = getActivity().getCurrentFocus();
                if (views != null) {
                    InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(views.getWindowToken(), 0);
                }

                if (TextUtils.isEmpty(input_email.getText().toString().trim())
                        && TextUtils.isEmpty(input_password.getText().toString().trim())) {
                    Toast.makeText(mcontext, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!input_email.getText().toString().trim().toLowerCase().equals("test@aigen.tech")) {
                    Toast.makeText(mcontext, "Enter valid email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!input_password.getText().toString().trim().equals("AigenTech")) {
                    Toast.makeText(mcontext, "Enter valid password", Toast.LENGTH_SHORT).show();
                    return;
                }
                appsharedprefsettings.setIsLoggedin(mcontext, true);
                ((MainActivity)mcontext).loadHomeScreen();
            }
        });

    }


}
