package io.uiza.apisdk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

import com.uiza.api.UZApi;

import java.util.Locale;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposables;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disposables = new CompositeDisposable();
        txtInfo = findViewById(R.id.txtInfo);
        findViewById(R.id.trackingBtn).setOnClickListener(v -> getLiveViews());
    }

    private void getLiveViews() {
        String entityId = "b938c0a6-e9bc-4b25-9e66-dbf81d755c25";
        UZApi.getLiveViewers(entityId, res -> {
            Timber.e("Client views: %d", res.getViews());
            txtInfo.setText(String.format(Locale.getDefault(), "Views: %d", res.getViews()));
        }, Timber::e, () -> Timber.e("Completed"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposables != null)
            disposables.dispose();
    }
}
