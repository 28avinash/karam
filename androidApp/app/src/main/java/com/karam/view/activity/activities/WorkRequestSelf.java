package com.karam.view.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.karam.rest.RestClient;
import com.karam.rest.RestClientInterface;
import com.karam.rest.rest_messages.requests.LaborerActiveStatus;
import com.karam.rest.rest_messages.responses.Error;
import com.karam.view.activity.BaseActivity;
import com.karam.view.activity.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for a user to register themselves as a laborer for a job request
 */
public class WorkRequestSelf extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.work_request_self;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        assignListenerToViews();

    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = findViewById(R.id.register);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_rest_request();
                startActivity(new Intent(WorkRequestSelf.this, LaborerStatusPage.class));
            }
        });
    }

    private void send_rest_request() {
        RestClient retro = new RestClient(getApplicationContext());
        RestClientInterface service = retro.getService();

        LaborerActiveStatus status = new LaborerActiveStatus("Active");
        Call<Error> callSync = service.modifyLaborerInfo(userData.get_user_id(), status);
        callSync.enqueue(new Callback<Error>() {
            @Override
            public void onResponse(Call<Error> call, Response<Error> response) {
                Error apiResponse = response.body();
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Error> call, Throwable t) {

            }
        });
    }
}