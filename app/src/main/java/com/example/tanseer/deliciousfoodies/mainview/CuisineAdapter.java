package com.example.tanseer.deliciousfoodies.mainview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanseer.deliciousfoodies.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supreme on 8/18/2017.
 */

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.PrimaryViewHolder> {

    private ArrayList<Cuisine> mCuisines;
    private Context context;
    private RecyclerView mSecondaryRecyclerView;
    private SecondaryAdapter mDishAdapter;
    //private ArrayList<DishList> mDishes = new ArrayList<DishList>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    List<ArrayList<DishList>> lists = new ArrayList<>();





    public CuisineAdapter(ArrayList<Cuisine> cuisine) {
        mCuisines = cuisine;


    }

    public class PrimaryViewHolder extends RecyclerView.ViewHolder {
        private TextView mPrimaryMovieGenre, mPrimaryMore;

        private PrimaryViewHolder(View itemView) {
            super(itemView);
            mPrimaryMovieGenre = (TextView) itemView.findViewById(R.id.primary_movie_genre);
            mPrimaryMore = (TextView) itemView.findViewById(R.id.primary_movie_genre1);
            mSecondaryRecyclerView = (RecyclerView) itemView.findViewById(R.id.secondary_recycler_view);
        }

        // This get called in PrimaryAdapter onBindViewHolder method
        private void bindViews(Context context,Cuisine cuisine, int position) {

            ArrayList<DishList> mDishes = new ArrayList<>();
            lists.add(mDishes);

            mPrimaryMovieGenre.setText(cuisine.getCuisinename());
            mPrimaryMore.setText(cuisine.getCuisinemore());
            getSecondaryAdapter(cuisine.getCuisinename(), position);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
            );

            mDishAdapter = new SecondaryAdapter(context, mDishes);
            mSecondaryRecyclerView.setLayoutManager(linearLayoutManager);
            mSecondaryRecyclerView.setAdapter(mDishAdapter);

        }
    }

    @Override
    public PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.primary_recycler_view_item, parent, false);
        view.setFocusable(true);
        return new PrimaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrimaryViewHolder holder, final int position) {
        Cuisine cuisine = mCuisines.get(position);
        holder.bindViews(context, cuisine, position);

        holder.mPrimaryMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked cell position " + position  , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCuisines.size();
    }

    private void getSecondaryAdapter(final String cuisineName, final int poistion) {





        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child(cuisineName);

        Log.d("TANSEERSECONDARYPOS",cuisineName );

        //if(position <= getItemCount()) {

            Log.d("TANSEERSECONDARY", mMessagesDatabaseReference.toString());
            mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {



                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        //String dish_name = (String) messageSnapshot.child("dishes").child("dishName").getValue();
                        String dish_name = (String) messageSnapshot.child("dishName").getValue();
                        String dish_type = (String) messageSnapshot.child("dishType").getValue();
                        String dish_price = (String) messageSnapshot.child("dishPrice").getValue();
                        String dish_rating = (String) messageSnapshot.child("dishRatingNumber").getValue();
                        String dish_time = (String) messageSnapshot.child("dishDeliveryTime").getValue();
                        Log.d("TANSEERSECONDARY", dish_name + "_" + dish_type + "_" + dish_price
                                + "_" + dish_rating + "_" + dish_time);

//                        DishList dishes1 = new DishList(dish_name,
//                                "Non-veg", "3.5", "45mins",
//                                "$400");
                        //DishList dishes1 = new DishList();
                        lists.get(poistion).add(messageSnapshot.getValue(DishList.class));
                        //mDishes.add(messageSnapshot.getValue(DishList.class));

                    }
                    mDishAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        //}
    }

}