package com.example.tanseer.deliciousfoodies.mainview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanseer.deliciousfoodies.R;

import java.util.ArrayList;

/**
 * Created by Supreme on 8/25/2017.
 */



public class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.SecondaryViewHolder> {
    private ArrayList<DishList> mDishLists;
    private Context mContext;

    public SecondaryAdapter(Context context, ArrayList<DishList> dishLists) {
        mContext = context;
        mDishLists = dishLists;
    }

    @Override
    public SecondaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.secondary_recycler_view_item, parent, false);
        view.setFocusable(true);
        return new SecondaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecondaryViewHolder holder, final int position) {
        DishList list = mDishLists.get(position);
        //holder.bindView(name);
        holder.bindView(list);

        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //callFragment(recipeStepSelected);
                Toast.makeText(mContext, "You clicked cell position " + position  , Toast.LENGTH_SHORT).show();

            }
        });

    }

    public class SecondaryViewHolder extends RecyclerView.ViewHolder {


        private ImageView mDishImage;
        private TextView mDishName;
        private TextView mDishType;
        private ImageButton mDishRatingIcon;
        private TextView mDishRatingNumber;
        private TextView mDishDeliveryTime;
        private TextView mDishPrice;
        private final View mView;

        private SecondaryViewHolder(View view) {
            super(view);
            mView = view;
            mDishImage = (ImageView) itemView.findViewById(R.id.dish_image);
            mDishName = (TextView) itemView.findViewById(R.id.dish_name);
            mDishType = (TextView) itemView.findViewById(R.id.dish_type);
            mDishRatingIcon = (ImageButton) itemView.findViewById(R.id.dish_rating_icon);
            mDishRatingNumber= (TextView) itemView.findViewById(R.id.dish_rating_number);
            mDishDeliveryTime = (TextView) itemView.findViewById(R.id.dish_delivery_time);
            mDishPrice = (TextView) itemView.findViewById(R.id.dish_price);


        }

        private void bindView(DishList dishList) {
            //mTextView.setText(name);
            //mDishImage.setImageResource(dishList.getDishImage());
            mDishName.setText(dishList.getDishName());
            mDishType.setText(dishList.getDishType());
            //mDishRatingIcon.setImageResource(dishList.getDishRatingIcon());
            mDishRatingNumber.setText(dishList.getDishRatingNumber());
            mDishDeliveryTime.setText(dishList.getDishDeliveryTime());
            mDishPrice.setText(dishList.getDishPrice());


        }
    }

    @Override
    public int getItemCount() {
        return mDishLists.size();
    }

}
