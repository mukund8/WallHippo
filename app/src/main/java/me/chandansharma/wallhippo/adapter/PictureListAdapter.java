package me.chandansharma.wallhippo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.chandansharma.wallhippo.R;
import me.chandansharma.wallhippo.model.PictureDetail;

import static android.content.ContentValues.TAG;

/**
 * Created by iamcs on 2017-04-26.
 */

public class PictureListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * Context and reference of the arrayList
     */
    private Context mContext;
    private ArrayList<PictureDetail> mPictureDetailArrayList;

    // Public Constructor
    public PictureListAdapter(Context context, ArrayList<PictureDetail> pictureDetails){
        mContext = context;
        mPictureDetailArrayList = pictureDetails;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PictureListItemHolder(LayoutInflater
                .from(mContext).inflate(R.layout.picture_screen_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PictureListItemHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mPictureDetailArrayList.size();
    }

    private class PictureListItemHolder extends RecyclerView.ViewHolder{

        //get the reference of the all views
        private ImageView mPicture;
        private TextView mPictureAuthorName;

        private PictureListItemHolder(View itemView) {
            super(itemView);
            mPicture = (ImageView) itemView.findViewById(R.id.photo_id);
            mPictureAuthorName = (TextView) itemView.findViewById(R.id.photo_author_name);
        }

        //Bind the view
        private void bindView(int position){

            //load the image in the imageView
            Log.d(TAG,mPictureDetailArrayList.get(position).getPhotoThumbnailUrl());
            Picasso.with(mContext).load(mPictureDetailArrayList.get(position).getPhotoThumbnailUrl())
                    .into(mPicture);

            mPictureAuthorName.setText(mPictureDetailArrayList.get(position).getPhotoAuthorName());
        }
    }
}
