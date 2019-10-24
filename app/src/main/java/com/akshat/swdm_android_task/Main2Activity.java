package com.akshat.swdm_android_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText truckNumberEditText, gstinEditText, buissnessNameEditText, nameEditText, stateCodeEditText, addressEditText, panNumberEditText;
    Button saveButton;
    Model model;

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
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                model = bundle.getParcelable("Model1");
                if (model != null) {
                    truckNumberEditText.setText(getIntent().getStringExtra("TruckNumber"));
                    gstinEditText.setText(getIntent().getStringExtra("GSTIN"));
                    buissnessNameEditText.setText(model.getTaxpayerInfo().getTradeNam());
                    nameEditText.setText(model.getTaxpayerInfo().getLgnm());
                    stateCodeEditText.setText(model.getTaxpayerInfo().getPradr().getAddr().getStcd());
                    addressEditText.setText(model.getTaxpayerInfo().getPradr().getAddr().getBno() +
                            model.getTaxpayerInfo().getPradr().getAddr().getCity() +
                            model.getTaxpayerInfo().getPradr().getAddr().getDst() +
                            model.getTaxpayerInfo().getPradr().getAddr().getLoc() +
                            model.getTaxpayerInfo().getPradr().getAddr().getBnm());
                    panNumberEditText.setText(model.getTaxpayerInfo().getGstin());
                }
            }
        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


}
