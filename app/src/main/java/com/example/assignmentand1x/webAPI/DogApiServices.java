package com.example.assignmentand1x.webAPI;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.assignmentand1x.R;

import org.json.JSONException;
import org.json.JSONObject;


public class DogApiServices {
    private static final String TAG = "ApiServices";
    private Context context;

    public DogApiServices(Context context) {
        this.context = context;
    }

    public void getRandomImage(final RandomResultCallBack resultCallBack) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://dog.ceo/api/breeds/image/random", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if ("success".equals(response.getString("status"))) {
                        Log.e(TAG, "getRandomImage onResponse: " + response.getString("message"), null);
                        resultCallBack.onRandomImageReceived(response.getString("message"));
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "getRandomImage onResponse: Error in fetch result!!", null);
                }
            }
        }, error -> {
            Log.e(TAG, "getRandomImage onErrorResponse: " + error.toString(), null);
            resultCallBack.onRandomImageError(Resources.getSystem().getString(R.string.dog_api_services_error_listener));
        });

        request.setRetryPolicy(new DefaultRetryPolicy(3000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueContainer.getInstance(context).add(request);
    }

    public interface RandomResultCallBack {
        void onRandomImageReceived(String message);
        void onRandomImageError(String error);
    }
}
