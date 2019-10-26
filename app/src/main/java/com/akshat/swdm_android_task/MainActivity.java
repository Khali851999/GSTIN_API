package com.akshat.swdm_android_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.akshat.swdm_android_task.MovieDBApi.API_KEY;

public class MainActivity extends AppCompatActivity {

    EditText truckNumberEditText, gstinEditText;
    Button next;
    String truckNumber, gstin;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        truckNumberEditText = findViewById(R.id.truckNumberEditText);
        gstinEditText = findViewById(R.id.gstinEditText);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                truckNumber = truckNumberEditText.getText().toString().trim();
                gstin = gstinEditText.getText().toString().trim();
                getData();
            }
        });

    }

    public void getData() {
        Call<Model> modelCall = MovieDBApi.getGstService().getModel(gstin, API_KEY);
        modelCall.enqueue(new Callback<Model>() {
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
                    intent.putExtra("GSTIN", gstin);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GSTIN Invalid", Toast.LENGTH_SHORT).show();
                gstinEditText.setError("Invalid GSTIN");
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
