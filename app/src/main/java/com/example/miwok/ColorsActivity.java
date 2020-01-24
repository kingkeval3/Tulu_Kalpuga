package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;   //For adding a media player

    //Handles Audio Focus when playing a sound file.
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                    || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {


                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.


                // Pause Playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);


            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }

        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.common_layout);

// Create and set up the {@Link Audio Manager} to request Audio Focus
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);


      final ArrayList<word> words = new ArrayList<word>();



        words.add(new word("Red","kemp",R.drawable.color_red,R.raw.red));
        words.add(new word("Black","kapp",R.drawable.color_black,R.raw.black));
        words.add(new word("White","boldu",R.drawable.color_white,R.raw.white));
        words.add(new word("Green","pachhe",R.drawable.color_green,R.raw.green));
        words.add(new word("Yellow","haladhi",R.drawable.color_mustard_yellow,R.raw.yellow));
        words.add(new word("Orange","kesari",R.drawable.color_dusty_yellow,R.raw.orange));



        wordAdapter adapters =  new wordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapters);





        /** For Adding ONCLICK FOR AUDIO TO PLAY **/

        /** Here from word java class position of the clicked list is called and stores in 'w' the using that id
         * the correct audio file is played
         */

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word w = words.get(position);
                releaseMediaPlayer();

                //Request Audio Focus for Playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,

                        //Use Music Stream
                        AudioManager.STREAM_MUSIC,
                        //Request Permanent Focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    //We have AudioFocus Now

//Create and setup the {@Link MediaPlayer } for the audio resource associated with current word


                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, w.getAudioResourceId());
                    mMediaPlayer.start();

                    Toast.makeText(ColorsActivity.this, "Playing Pronunciation of the Color!", Toast.LENGTH_SHORT).show();


                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);


                }
            }

        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        //When the activity is stopped, release the media player resources because
        //we won't be playing more sounds

        releaseMediaPlayer();
    }




    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };




    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;



            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }




}
