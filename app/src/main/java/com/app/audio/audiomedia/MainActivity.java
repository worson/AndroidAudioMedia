package com.app.audio.audiomedia;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.lib.audio.player.NativePlayer;
import com.lib.common.androidbase.global.GlobalContext;
import com.lib.common.androidbase.resource.AssetsUtil;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String mWavFilePath;
    private int mLastPlayId=-1;

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalContext.set(getApplication());
        initViews();
    }

    private void initViews() {
        mWavFilePath=GlobalContext.get().getFilesDir().getPath()+"/test.wav";
        if (!new File(mWavFilePath).exists()) {
            AssetsUtil.copyFile(GlobalContext.get(),"test.wav",mWavFilePath);
        }

        tv_content=findViewById(R.id.tv_content);

        findViewById(R.id.bt_media_play).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                NativePlayer.getInstance().cancel(mLastPlayId);
                mLastPlayId=NativePlayer.getInstance().play(new File(mWavFilePath),null);
            }
        });

        findViewById(R.id.bt_media_stop).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                NativePlayer.getInstance().cancel(mLastPlayId);
            }
        });



    }
}
