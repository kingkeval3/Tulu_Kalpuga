package com.example.miwok;

public class word {





    /** Default translation for the word */
    private String mDefaultTranslation;




    /** Miwok translation for the word */
    private String mMiwokTranslation;



    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /** For music array **/

    private int mAudioResourceId;




    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId=audioResourceId;
    }

    public word(String defaultTranslation, String miwokTranslation,int imgResId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId=imgResId;
        mAudioResourceId=audioResourceId;
    }




    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }




    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }


    public int getImageResourceId()
    {
        return mImageResourceId;
    }


    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }


    /**
     * Returns the string representation of the {@link Word} object.
     */

/**
    @Override
    public String toString() {
        return "word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }

    **/


}
