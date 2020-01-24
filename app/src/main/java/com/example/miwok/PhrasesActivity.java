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

public class PhrasesActivity extends AppCompatActivity {


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

        words.add(new word("Then, What's up?","Bokka, Vishesha?",R.raw.sup));
        words.add(new word("Had Lunch","Unas Aanda",R.raw.had_lunch));
        words.add(new word("What the hell","enchina saav ya",R.raw.what_the_hell));
        words.add(new word("Third Class Guy","Mooji kaas da aye",R.raw.third_class));
        words.add(new word("Bastard","Bevarsi",R.raw.bvc));
        words.add(new word("What does your dad do?","Bokka ninna ammer dada malpune?",R.raw.dad_work));
        words.add(new word("Do you have any sense","Nikk chur aandala swaya unda",R.raw.sense));
        words.add(new word("Get Lost","Poya manipande",R.raw.get_lost));
        words.add(new word("Had Breakfast","Tindi Aanda",R.raw.breakfast));
        words.add(new word("Have you lost your mind","Attya, nikk mande sama unda",R.raw.lost_mind));
        words.add(new word("Idiot","Muttala",R.raw.idiot));



        wordAdapter adapters =  new wordAdapter(this, words,R.color.category_phrases);

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


                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, w.getAudioResourceId());
                    mMediaPlayer.start();

                    Toast.makeText(PhrasesActivity.this, "Playing Pronunciation of the Phrase!", Toast.LENGTH_SHORT).show();


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
