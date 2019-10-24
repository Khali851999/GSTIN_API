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

    EditText truckNumberEditText,gstinEditText;
    Button next;
    String truckNumber,gstin;
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
    public void getData(){
        Call<Model> modelCall = MovieDBApi.getGstService().getModel(gstin,API_KEY);
        modelCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model = response.body();
                Log.e(TAG, "onResponse: "+model.getTaxpayerInfo().getLgnm() );
                if(model==null){
                    Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("Model1",model);
                    intent.putExtra("TruckNumber",truckNumber);
                    intent.putExtra("GSTIN",gstin);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GSTIN Invalid", Toast.LENGTH_SHORT).show();
                gstinEditText.setError("Invalid GSTIN");
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
