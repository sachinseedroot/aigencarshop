package com.aigen.carshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.aigen.carshop.R;

public class contacdailogfragment extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getView() != null ? getView() : inflater.inflate(R.layout.dailogfragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText input_name = (EditText) view.findViewById(R.id.input_name);
        final EditText input_msg = (EditText) view.findViewById(R.id.input_msg);

        Button button = (Button) view.findViewById(R.id.send_query_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"developer@aigen.tech"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Query by: "+input_name.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT   , input_msg.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send query..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button closebtn = (Button) view.findViewById(R.id.closebtn);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getDialog().dismiss();
            }
        });

    }
}
