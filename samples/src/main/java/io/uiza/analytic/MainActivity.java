package io.uiza.analytic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.uiza.analytic.RxBinder;
import com.uiza.analytic.UZRestClient;
import com.uiza.analytic.models.UZEventType;
import com.uiza.analytic.models.UZTrackingBody;
import com.uiza.analytic.models.UZTrackingData;

import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disposables = new CompositeDisposable();
        findViewById(R.id.trackingBtn).setOnClickListener(v -> {
            tracking();
        });
    }

    private void tracking() {
        UZTrackingData data1 = new UZTrackingData();
        data1.setEventType(UZEventType.WATCHING);
        data1.setEntityId("ena1d7fc-d5f0-421b-a7fa-85db48be8143");
        data1.setEntitySource("live");
        UZTrackingData data2 = new UZTrackingData();
        data2.setEventType(UZEventType.WATCHING);
        data2.setEntityId("ena1d7fc-d5f0-421b-a7fa-85db48be8142");
        data2.setEntitySource("live");
        UZTrackingBody body = UZTrackingBody.create(Arrays.asList(data1, data2));
        Log.e("NAMND", body.toString());
        disposables.add(RxBinder.bind(UZRestClient.getInstance().createAnalyticAPI().pushEvents(body), responseBody -> {
                    Log.e("NAMND", "onNext: " + responseBody.contentLength());
                },
                error -> {
                    Log.e("NAMND", "onError: " + error.getMessage());
                }, () -> {
                    Log.e("NAMND", "completed");
                }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposables != null)
            disposables.dispose();
    }
}
