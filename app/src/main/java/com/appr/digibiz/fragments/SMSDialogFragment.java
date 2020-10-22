package com.appr.digibiz.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appr.digibiz.R;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SMSDialogFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.sendMessageButton)
    Button mSendMessage;
    @BindView(R.id.reminder_message)
    TextInputLayout mMessage;
    @BindView(R.id.reminder_phone)
    TextInputLayout mPhone;

    public SMSDialogFragment() {
        // Required empty public constructor
    }

    public static SMSDialogFragment newInstance(String param1, String param2) {
        SMSDialogFragment fragment = new SMSDialogFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        if(view == mSendMessage) {
            sendSMSMessage();
        }
    }

    private void sendSMSMessage() {
        String message = mMessage.getEditText().getText().toString().trim();
        String number = mPhone.getEditText().getText().toString().trim();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}