package com.example.tanseer.deliciousfoodies.mainview;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanseer.deliciousfoodies.R;

import java.util.ArrayList;

/**
 * Created by Supreme on 8/18/2017.
 */

public class CuisineAdapterold extends RecyclerView.Adapter<CuisineAdapterold.PrimaryViewHolder> {


    private Context mContext;
    //private SecondaryAdapter adapter;
    private RecyclerView mSecondaryRecyclerView;
    private Cursor mCursor;
    private View mEmptyView;
    private SecondaryAdapter mSecondaryAdapter;
    private ArrayList<Cuisine> mCuisine;

//    public CuisineAdapterold(@NonNull Context context, View emptyView) {
//        mContext = context;
//        mEmptyView = emptyView;
//
//    }

    public CuisineAdapterold(ArrayList<Cuisine> cuisine) {
        mCuisine = cuisine;

    }

//    public CuisineAdapterold(@NonNull Context context) {
//        mContext = context;
//        //mEmptyView = emptyView;
//
//    }

    public class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mCuisineName, mMore;


        private PrimaryViewHolder(View itemView) {
            super(itemView);
            mCuisineName = (TextView) itemView.findViewById(R.id.cuisine_name);
            mMore = (Button) itemView.findViewById(R.id.more);
            //mSecondaryRecyclerView = (RecyclerView) itemView.findViewById(R.id.secondary_recycler_view);


        }

        // This get called in PrimaryAdapter onBindViewHolder method
        private void bindViews(Context context,String genre, int position) {
            mCuisineName.setText(genre);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
            );

            mSecondaryRecyclerView.setLayoutManager(linearLayoutManager);
            mSecondaryRecyclerView.setHasFixedSize(true);
            //mSecondaryAdapter = new SecondaryAdapter(context);
            //mSecondaryRecyclerView.setAdapter(mSecondaryAdapter);
        }


//        @Override
//        public void onClick(View v){
//            int adapterPosition = getAdapterPosition();
//            mCursor.moveToPosition(adapterPosition);
//            Toast.makeText(v.getContext(), "You clicked cell position " + adapterPosition  , Toast.LENGTH_SHORT).show();
//            //mSecondaryRecyclerView = (RecyclerView) itemView.findViewById(R.id.secondary_recycler_view);
//        }

//        // This get called in CuisineAdapterold onBindViewHolder method
//        private void bindViews(Context context, Cuisine genre, int position) {
//            mCuisineName.setText(genre.getCuisineName());
//
////            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
////                    context,
////                    LinearLayoutManager.HORIZONTAL,
////                    false
////            );
////
////            mSecondaryRecyclerView.setLayoutManager(linearLayoutManager);
////            mSecondaryRecyclerView.setAdapter(getSecondaryAdapter(position));
//        }
    }

    @Override
    public PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cuisine_more_list, parent, false);
        view.setFocusable(true);
        return new PrimaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrimaryViewHolder holder, final int position) {
        mCursor.moveToPosition(position);
        String cuisineNameString = mCursor.getString(MainViewFragment1.COL_CUISINE_NAME);
        holder.bindViews(mContext, cuisineNameString, position);

        //holder.mCuisineName.setText(cuisineNameString);
        //Cuisine genre = mCuisine.get(position);
        //holder.bindViews(mContext, genre, position);

        holder.mMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "You clicked cell position " + position  , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount(){
        if(null==mCursor) {
            return 0;
        }
        return mCursor.getCount();
    }

    void swapCursor(Cursor newCursor){
        mCursor = newCursor;
        notifyDataSetChanged();
        //mEmptyView.setVisibility(getItemCount() ==0 ? View.VISIBLE : View.GONE);
    }



}