package com.dji.sdk.sample.demo.test1;

import static com.dji.sdk.sample.internal.utils.ToastUtils.showToast;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dji.sdk.sample.R;
import com.dji.sdk.sample.internal.controller.DJISampleApplication;
import com.dji.sdk.sample.internal.utils.DialogUtils;
import com.dji.sdk.sample.internal.utils.ToastUtils;
import com.dji.sdk.sample.internal.view.PresentableView;

import dji.common.error.DJIError;
import dji.common.util.CommonCallbacks;
import dji.internal.version.DJIVersionBaseComponent;
import dji.sdk.flightcontroller.FlightController;
import dji.sdk.sdkmanager.DJISDKManager;

public class testing extends LinearLayout implements PresentableView {

    Button bt_turnon,bt_turnoff,btn_takeoff,btn_land;


    public testing(Context context) {
        super(context);

        setOrientation(LinearLayout.HORIZONTAL);
        setClickable(true);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.aa, this,true);

        FlightController flightController = DJISampleApplication.getAircraftInstance().getFlightController();

        if(flightController == null) return;

        bt_turnon = findViewById(R.id.btn1);
        bt_turnoff = findViewById(R.id.btn2);
        btn_takeoff = findViewById(R.id.btn_takeoff);
        btn_land = findViewById(R.id.btn_land);

        bt_turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                flightController.turnOnMotors(new CommonCallbacks.CompletionCallback() {
                    @Override
                    public void onResult(DJIError djiError) {
                        //ToastUtils.showToast(djiError.getDescription()); NAO FUNFA
                        /*DialogUtils.showDialog(getContext(), djiError.getDescription());*/
                        DJISDKManager.getInstance().getProduct().setDiagnosticsInformationCallback(diagnosticsList -> {
                            System.out.println(diagnosticsList);
                        });
                    }
                });
            }
        });

        bt_turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                flightController.turnOffMotors(new CommonCallbacks.CompletionCallback() {
                    @Override
                    public void onResult(DJIError djiError) {

                        /*DialogUtils.showDialog(getContext(), djiError.getDescription());*/
                        DJISDKManager.getInstance().getProduct().setDiagnosticsInformationCallback(diagnosticsList -> {
                            System.out.println(diagnosticsList);
                        });
                    }
                });
            }
        });

        btn_takeoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flightController.startTakeoff(new CommonCallbacks.CompletionCallback() {
                    @Override
                    public void onResult(DJIError djiError) {
                        DJISDKManager.getInstance().getProduct().setDiagnosticsInformationCallback(diagnosticsList -> {
                            System.out.println(diagnosticsList);
                        });
                    }
                });
            }
        });

        btn_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flightController.startLanding(new CommonCallbacks.CompletionCallback() {
                    @Override
                    public void onResult(DJIError djiError) {
                        DJISDKManager.getInstance().getProduct().setDiagnosticsInformationCallback(diagnosticsList -> {
                            System.out.println(diagnosticsList);
                        });
                    }
                });
            }
        });
    }




    @Override
    public int getDescription() {
        return R.string.look_at_mission;
    }

    @NonNull
    @Override
    public String getHint() {
        return this.getClass().getSimpleName() + ".java";
    }


}