package org.downloader.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.downloader.funactionalities.ImageAdapter;
import org.downloader.funactionalities.ImageDownloader;

public class MainActivity extends ListActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioButton randomButton, corectionRadioButton, noTaskButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        setListAdapter(new ImageAdapter());

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        ImageDownloader.Mode mode = ImageDownloader.Mode.NO_ASYNC_TASK;



        switch (checkedId) {

            case R.id.noTaskButton:
                System.out.println("No Task");
                break;

            case R.id.correctionButton:
                mode = ImageDownloader.Mode.CORRECT;
                System.out.println("Correction");
                break;
            case R.id.randomButton :
                mode = ImageDownloader.Mode.NO_DOWNLOADED_DRAWABLE;
                System.out.println("Random");
                break;
            default:
                return;
        }

        ( (ImageAdapter) getListAdapter()).getImageDownloader().setMode(mode);

    }
}
