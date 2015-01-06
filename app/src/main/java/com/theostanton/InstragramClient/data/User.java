package com.theostanton.InstragramClient.data;

import android.util.Log;
import com.theostanton.InstragramClient.Theo;
import com.theostanton.InstragramClient.instagram.InstaJSON;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by theo on 27/12/14.
 */
public class User {

    private static final String TAG = "User";

    public String userName = "username unset";
    public String webSite = null;
    public String profilePicture = null;
    public String fullName = "Private user?";
    public String bio = null;
    public int id = -1;

    private boolean privateUser = false;
    private boolean complete = false;
    public int followedByCount = -1;
    public int mediaCount = -1;
    public int followsCount = -1;

    public User(){}

    public User(JSONObject object){
        if(object==null){
            privateUser = true;
            return;
        }
        try {
            id = object.getInt(InstaJSON.ID);
            userName = object.getString(InstaJSON.USERNAME);
            fullName = object.getString(InstaJSON.FULL_NAME);
            profilePicture = object.getString(InstaJSON.PROFILE_PICTURE);
            if(object.has(InstaJSON.BIO)) bio = object.getString(InstaJSON.BIO);
            if(object.has(InstaJSON.WEBSITE)) webSite = object.getString(InstaJSON.WEBSITE);

            if (object.has(InstaJSON.COUNTS)){
                JSONObject countsObject = object.getJSONObject(InstaJSON.COUNTS);
                mediaCount = countsObject.getInt(InstaJSON.MEDIA);
                followsCount = countsObject.getInt(InstaJSON.FOLLOWS);
                followedByCount = countsObject.getInt(InstaJSON.FOLLOWED_BY);
                complete = true;
            }

        } catch (JSONException e) {
            Log.e(TAG, "object = " + object.toString() );
            e.printStackTrace();
        }
    }

    public boolean isComplete() {
        return complete;
    }

    public String getMediaCount(){
        return Theo.abreviate(mediaCount);
    }

    public String getFollowsCount(){
        return Theo.abreviate(followsCount);
    }

    public String getFollowedByCount(){
        return Theo.abreviate(followedByCount);
    }
}
