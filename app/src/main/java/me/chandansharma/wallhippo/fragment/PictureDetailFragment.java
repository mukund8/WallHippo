package me.chandansharma.wallhippo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.chandansharma.wallhippo.R;
import me.chandansharma.wallhippo.adapter.PictureListAdapter;
import me.chandansharma.wallhippo.model.PictureDetail;
import me.chandansharma.wallhippo.utils.ApiUrls;
import me.chandansharma.wallhippo.utils.AppController;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureDetailFragment extends Fragment {

    //get the tag of the class
    public static final String TAG = PictureDetailFragment.class.getSimpleName();

    //ArrayList of the all picture details
    ArrayList<PictureDetail> mPictureDetailsList = new ArrayList<>();

    //Picture Url
    private String mPictureUrl;
    private PictureListAdapter mPictureListAdapter;
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mRecyclerView;

    public PictureDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_picture_detail, container, false);

        mPictureUrl = ApiUrls.PICTURE_BASE_URL + ApiUrls.PHOTO_TAG + "?" + ApiUrls.API_KEY_TAG
                + "=" + ApiUrls.API_KEY;
        Log.d(TAG, mPictureUrl);
        getPictureListData(mPictureUrl);
        mPictureListAdapter = new PictureListAdapter(getActivity(), mPictureDetailsList);
        mGridLayoutManager = new GridLayoutManager(getActivity() , 2);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mPictureListAdapter);
        return mRecyclerView;
    }

    private void getPictureListData(String pictureUrl){
        //Tag to cancel the request
        String jsonArrayTag = "jsonArrayTag";

        JsonArrayRequest pictureJsonArray = new JsonArrayRequest(pictureUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d(TAG, response.toString());

                            for(int i = 0 ; i < response.length() ; i++){
                                JSONObject singlePictureDetailObject = (JSONObject) response.get(i);

                                PictureDetail singlePictureDetail = new PictureDetail(
                                        singlePictureDetailObject.getString("id"),
                                        singlePictureDetailObject.getInt("likes"),
                                        singlePictureDetailObject.getJSONObject("links").getString("download"),
                                        singlePictureDetailObject.getJSONObject("urls").getString("small"),
                                        singlePictureDetailObject.getJSONObject("user").getString("name"));
                                mPictureDetailsList.add(singlePictureDetail);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, error.getMessage());
                    }
                });
        //Adding request to the queue
        AppController.getInstance().addToRequestQueue(pictureJsonArray, jsonArrayTag);
    }
}
