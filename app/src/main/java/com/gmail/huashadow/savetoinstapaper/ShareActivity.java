package com.gmail.huashadow.savetoinstapaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ShareActivity extends Activity {

    public static final String TAG = ShareActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        saveToInstapaper();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
        super.onNewIntent(intent);
        saveToInstapaper();
    }

    private void saveToInstapaper() {
        Log.d(TAG, "saveToInstapaper");
        Intent intent = getIntent();
        if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            Log.d(TAG, intent.getData().toString());
            Intent saveToInstapaperIntent = new Intent(Intent.ACTION_SEND);
            saveToInstapaperIntent.setPackage("com.instapaper.android");
            saveToInstapaperIntent.setType("text/plain");
            saveToInstapaperIntent.putExtra(Intent.EXTRA_TEXT, intent.getData().toString());
            if (saveToInstapaperIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(saveToInstapaperIntent);
            } else {
                Toast.makeText(this, R.string.no_instapaper_toast, Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
