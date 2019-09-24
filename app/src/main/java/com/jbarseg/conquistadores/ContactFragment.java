package com.jbarseg.conquistadores;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ContactFragment extends Fragment {

    private EditText eTextSubject;
    private EditText eTextMessage;

    public ContactFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_contact, container, false);

        eTextSubject = (EditText) RootView.findViewById(R.id.issue_contact);
        eTextMessage = (EditText) RootView.findViewById(R.id.description_contact);
        Button buttonSend = (Button) RootView.findViewById(R.id.send_contact_button);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        return RootView;
    }

    private void sendMail() {
        String email = "conquistadoresvidaplena@gmail.com";
        String[] eDestination = email.split(",");

        String subject = eTextSubject.getText().toString();
        String message = eTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, eDestination);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Cliente de correo electrónico"));
    }
}
