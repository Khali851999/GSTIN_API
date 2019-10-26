package com.akshat.swdm_android_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Main2Activity extends AppCompatActivity {

    EditText truckNumberEditText, gstinEditText, buissnessNameEditText, nameEditText, stateCodeEditText, addressEditText, panNumberEditText;
    Button saveButton;
    TextInputLayout truckNumberLayout, gstinLayout;
    Model model;
    private static final String TAG = "Main2Activity";
    String truckNumber, gstinNumber;
    boolean error1 = false;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        truckNumberEditText = findViewById(R.id.truckNumberEditText);
        gstinEditText = findViewById(R.id.gstinEditText);
        buissnessNameEditText = findViewById(R.id.buissnessNameEditText);
        nameEditText = findViewById(R.id.nameEditText);
        stateCodeEditText = findViewById(R.id.stateCodeEditText);
        addressEditText = findViewById(R.id.addressEditText);
        panNumberEditText = findViewById(R.id.panNumberEditText);
        truckNumberLayout = findViewById(R.id.truckNumberLayout);
        gstinLayout = findViewById(R.id.gstinLayout);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                truckNumberEditText.setText(bundle.getString("TruckNumber"));
                gstinEditText.setText(bundle.getString("GSTIN"));
                buissnessNameEditText.setText(bundle.getString("model.getTaxpayerInfo().getTradeNam()"));
                nameEditText.setText(bundle.getString("model.getTaxpayerInfo().getLgnm()"));
                stateCodeEditText.setText(bundle.getString("model.getTaxpayerInfo().getPradr().getAddr().getStcd()"));
                addressEditText.setText(bundle.getString("add"));
                panNumberEditText.setText(bundle.getString("model.getTaxpayerInfo().getGstin()"));
//                model = bundle.getParcelable("Model");
//                if (model != null) {
//                    buissnessNameEditText.setText(model.getTaxpayerInfo().getTradeNam());
//                    nameEditText.setText(model.getTaxpayerInfo().getLgnm());
//                    stateCodeEditText.setText(model.getTaxpayerInfo().getPradr().getAddr().getStcd());
//                    addressEditText.setText(model.getTaxpayerInfo().getPradr().getAddr().getBno() +
//                            model.getTaxpayerInfo().getPradr().getAddr().getCity() +
//                            model.getTaxpayerInfo().getPradr().getAddr().getDst() +
//                            model.getTaxpayerInfo().getPradr().getAddr().getLoc() +
//                            model.getTaxpayerInfo().getPradr().getAddr().getBnm());
//                    panNumberEditText.setText(model.getTaxpayerInfo().getGstin());
//                }
            }
        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                truckNumber = truckNumberEditText.getText().toString().trim();
                gstinNumber = gstinEditText.getText().toString().trim().toUpperCase();


                if (truckNumber.length() >= 9 && truckNumber.length() <= 11) {
                    for (char c : truckNumber.substring(0, 2).toCharArray()) {
                        if (!Character.isLetter(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);

                            error = true;
                        }
                    }
                    for (char c : truckNumber.substring(2, 4).toCharArray()) {
                        if (!Character.isDigit(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);

                            error = true;
                        }
                    }
                    int seql = truckNumber.length() - 8;
                    for (char c : truckNumber.substring(4, 4 + seql).toCharArray()) {
                        if (!Character.isLetter(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);

                            error = true;
                        }
                    }
                    for (char c : truckNumber.substring(truckNumber.length() - 4, truckNumber.length() - 1).toCharArray()) {
                        if (!Character.isDigit(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);
                            error = true;
                        }
                    }
                } else {
                    error = true;
                    truckNumberLayout.setErrorEnabled(true);
                    truckNumberLayout.setError("Invalid");
                    truckNumberLayout.setBoxStrokeColor(Color.RED);

                }


                if (gstinNumber.length() == 15) {
                    for (char c : gstinNumber.substring(0, 2).toCharArray()) {
                        if (!Character.isDigit(c)) {
                            gstinLayout.setErrorEnabled(true);
                            gstinLayout.setError("Invalid");
                            gstinLayout.setBoxStrokeColor(Color.RED);
                            error1 = true;
                        }
                    }
                    if (!(((Integer.parseInt(gstinNumber.substring(0, 2))) >= 1) && ((Integer.parseInt(gstinNumber.substring(0, 2))) <= 35))) {
                        gstinLayout.setErrorEnabled(true);
                        gstinLayout.setError("Invalid");
                        gstinLayout.setBoxStrokeColor(Color.RED);
                        error1 = true;
                    }
                    Log.e(TAG, "onClick: " + Integer.parseInt(gstinNumber.substring(0, 2)));

                    if (!(Integer.parseInt(gstinNumber.substring(12, 13)) == 1) && !(Integer.parseInt(gstinNumber.substring(12, 13)) == 2)) {
                        gstinLayout.setErrorEnabled(true);
                        gstinLayout.setError("Invalid");
                        gstinLayout.setBoxStrokeColor(Color.RED);
                        error1 = true;
                    }
                    if (!(gstinNumber.substring(13, 14).equals("Z"))) {
                        gstinLayout.setErrorEnabled(true);
                        gstinLayout.setError("Invalid");
                        gstinLayout.setBoxStrokeColor(Color.RED);
                        error1 = true;
                    }
                } else {
                    error1 = true;
                    gstinLayout.setErrorEnabled(true);
                    gstinLayout.setBoxStrokeColor(Color.RED);
                    gstinLayout.setError("Invalid");
                }

                if (!error) {
                    truckNumberLayout.setBoxStrokeColor(Color.GREEN);
                    truckNumberEditText.setEnabled(true);
                    truckNumberLayout.setErrorEnabled(false);
                }
                if (!error1) {
                    gstinLayout.setBoxStrokeColor(Color.GREEN);
                    gstinLayout.setErrorEnabled(false);
                    gstinEditText.setEnabled(true);

                }
                if (!error1 && !error)
                    Toast.makeText(Main2Activity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
