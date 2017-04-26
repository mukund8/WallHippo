package me.chandansharma.wallhippo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.chandansharma.wallhippo.R;
import me.chandansharma.wallhippo.fragment.PictureDetailFragment;

public class WallHippoMainScreen extends AppCompatActivity {

    //get the tag of the class
    public static final String TAG = PictureDetailFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_hippo_main_screen);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PictureDetailFragment())
        .commit();
    }
}
