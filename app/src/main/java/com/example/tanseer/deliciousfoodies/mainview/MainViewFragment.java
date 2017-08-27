package com.example.tanseer.deliciousfoodies.mainview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanseer.deliciousfoodies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Supreme on 8/25/2017.
 */

public class MainViewFragment  extends Fragment{

    private static final String LOG_TAG = MainViewFragment.class.getSimpleName();
    private RecyclerView mRecylerView;
    private Context context = getActivity();

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;
    private TextView textView;
    private CuisineAdapter mCuisineAdapter;
    private ArrayList<Cuisine> mCuisines = new ArrayList<Cuisine>();

    public MainViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cuisine_main, container, false);
        mRecylerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_cuisine_names);
        View emptyView = rootView.findViewById(R.id.recyclerview_recipe_empty);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(context));
        //View emptyView = rootView.findViewById(R.id.recyclerview_forecast_empty);
        // The ForecastAdapter will take data from a source and
        // use it to populate the RecyclerView it's attached to.


        mCuisineAdapter = new CuisineAdapter(mCuisines);
        mRecylerView.setAdapter(mCuisineAdapter);
        loadCuisineData();

        return rootView;
    }

    private void loadCuisineData(){
        //Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("cuisines");

        Log.d("TANSEERDBVALUE", mMessagesDatabaseReference.toString());

        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
//                    long cuisine_id = (long) messageSnapshot.child("cuisine_id").getValue();
//                    String cuisine_name = (String) messageSnapshot.child("cuisine_name").getValue();
//                    ArrayList arrayList = (ArrayList) messageSnapshot.child("dishes").getValue();
//                    Log.d("TANSEERDBVALUE", "" + cuisine_id);
//                    Log.d("TANSEERDBVALUE", "" + cuisine_name);
//                    Log.d("TANSEERDBVALUE1", "" + arrayList.toString() + arrayList.size());
//
//                    Log.d("TANSEERDBVALUE123", dataSnapshot.getValue().toString());
//
//                    //Cuisine cuisine = dataSnapshot.getValue(Cuisine.class);
//                    //Cuisine cuisine = new Cuisine(cuisine_id, cuisine_name, arrayList);
//                    mCuisines.add(cuisine);
//
//
//
//                    for (DataSnapshot messageSnapshot1: messageSnapshot.child("dishes").getChildren()) {
//                        String dish_name = (String) messageSnapshot1.child("dish_name").getValue();
//                        Log.d("TANSEERDBVALUE", "" + dish_name);
//                    }
//                    Cuisine cuisine = dataSnapshot.getValue(Cuisine.class);
//                    Log.d("TANSEERDBVAL", cuisine.getCuisinename());
//                    mCuisines.add(cuisine);

                    String cuisine_name = (String) messageSnapshot.child("cuisinename").getValue();
                    String  cuisine_more = (String) messageSnapshot.child("cuisinemore").getValue();
                    ArrayList<DishList> dishes_list = (ArrayList<DishList>) messageSnapshot.child("dishes").getValue();
                    Log.d("TANSEERDBVALUE", "" + cuisine_more);
                    Log.d("TANSEERDBVALUE", "" + cuisine_name);
                    Log.d("TANSEERDBVALUE", "" + dishes_list);


                    Cuisine cuisine = messageSnapshot.getValue(Cuisine.class);
                    //Log.d("TANSEERDBVAL", cuisine.getCuisinename());
                    mCuisines.add(cuisine);

                }

                mCuisineAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
