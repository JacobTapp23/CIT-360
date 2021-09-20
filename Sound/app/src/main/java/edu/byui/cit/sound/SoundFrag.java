package edu.byui.cit.sound;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class SoundFrag extends Fragment {
    MediaPlayer player;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            super.onCreateView(inflater, container, savedInstanceState);
            view = inflater.inflate(R.layout.frag_sound, container, false);

            Button btnPlay = view.findViewById(R.id.btnPlay);
            Button btnPause = view.findViewById(R.id.btnPause);
            btnPlay.setOnClickListener(new PlayHandler());
            btnPause.setOnClickListener(new PauseHandler());

            if (player == null) {
                Activity activity = getActivity();
                player = MediaPlayer.create(activity, R.raw.boogie1);
            }
        } catch (Exception ex) {
            Log.e(MainActivity.TAG, ex.toString(), ex);
        }
        return view;
    }


    private final class PlayHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            player.start();
        }
    }


    private final class PauseHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            player.pause();
        }
    }
}
