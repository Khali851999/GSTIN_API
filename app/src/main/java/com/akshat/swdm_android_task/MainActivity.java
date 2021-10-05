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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.akshat.swdm_android_task.MovieDBApi.API_KEY;

public class MainActivity extends AppCompatActivity {

    EditText truckNumberEditText, gstinEditText;
    Button next,btm,btm22;
    String truckNumber, gstinNumber;
    private static final String TAG = "MainActivity";
    boolean error1 = false;
    TextInputLayout truckNumberLayout, gstinLayout;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        truckNumberEditText = findViewById(R.id.truckNumberEditText);
        gstinEditText = findViewById(R.id.gstinEditText);
        truckNumberLayout = findViewById(R.id.truckNumberLayout);
        gstinLayout = findViewById(R.id.gstinLayout);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                truckNumber = truckNumberEditText.getText().toString().trim();
                gstinNumber = gstinEditText.getText().toString().trim().toUpperCase();


                if (truckNumber.length() >= 9 && truckNumber.length() <= 11) {
                    
                    for (char c : truckNumber.substring(2, 4).toCharArray()) {
                        if (!Character.isDigit(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);

                            error = true;
                        }
                    }
                    for (char c : truckNumber.substring(0, 2).toCharArray()) {
                        if (!Character.isLetter(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);

                            error = true;
                        }
                    }
                    int seql = truckNumber.length() - 8;
                    for (char c : truckNumber.substring(truckNumber.length() - 4, truckNumber.length() - 1).toCharArray()) {
                        if (!Character.isDigit(c)) {
                            truckNumberLayout.setErrorEnabled(true);
                            truckNumberLayout.setError("Invalid");
                            truckNumberLayout.setBoxStrokeColor(Color.RED);
                            error = true;
                        }
                    }
                    for (char c : truckNumber.substring(4, 4 + seql).toCharArray()) {
                        if (!Character.isLetter(c)) {
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
                if (!error1 && !error) {
                    getData();
                }

            }
        });

    }

    public void getData() {
        Call<Model> modelCall = MovieDBApi.getGstService().getModel(gstinNumber, API_KEY);
        modelCall.enqueue(new Callback<Model>() {
            
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GSTIN Invalid", Toast.LENGTH_SHORT).show();
                gstinEditText.setError("Invalid GSTIN");
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
            
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model = response.body();
                if (model == null || model.getTaxpayerInfo() == null || model.getCompliance() == null) {
                    Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onResponse: " + response.message() + "\n" + response.isSuccessful());
                }
                if (model != null && model.getTaxpayerInfo() != null && model.getCompliance() != null) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("model.getTaxpayerInfo().getTradeNam()", model.getTaxpayerInfo().getTradeNam());
                    intent.putExtra("model.getTaxpayerInfo().getLgnm()", model.getTaxpayerInfo().getLgnm());
                    intent.putExtra("model.getTaxpayerInfo().getPradr().getAddr().getStcd()", model.getTaxpayerInfo().getPradr().getAddr().getStcd());
                    intent.putExtra("model.getTaxpayerInfo().getGstin()", model.getTaxpayerInfo().getGstin());
                    intent.putExtra("add",
                            model.getTaxpayerInfo().getPradr().getAddr().getBno() + ", " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getBnm() + " " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getSt() + ", " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getLt() + " " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getDst() + " " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getCity() + ",\n" +
                                    model.getTaxpayerInfo().getPradr().getAddr().getLoc() + " " +
                                    model.getTaxpayerInfo().getPradr().getAddr().getPncd());
                    intent.putExtra("TruckNumber", truckNumber);
                    intent.putExtra("GSTIN", gstinNumber);
                    startActivity(intent);
                }
            }

     
        });
    }
}
