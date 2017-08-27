package com.example.tanseer.deliciousfoodies.mainview;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanseer.deliciousfoodies.R;
import com.example.tanseer.deliciousfoodies.data.Contract;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Supreme on 8/24/2017.
 */

public class MainViewFragment1 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String LOG_TAG = MainViewFragment1.class.getSimpleName();

    // For the main view we're showing only a names from the stored data.
    // Specify the columns we need
    private static final String[] CUISINE_COLUMNS_PROJECTION = {    //projection
            Contract.CuisineNameEntry.COLUMN_CUISINE_NAME,
    };

    public static final int COL_CUISINE_NAME = 0;

    private static final int CUISINES_LOADER = 0;
    private CuisineAdapterold mAdapter;
    private RecyclerView mRecylerView;
    private ArrayList<Cuisine> mCuisine = new ArrayList<Cuisine>();
    private Context context = getActivity();
    private int mPosition = RecyclerView.NO_POSITION;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;

    public MainViewFragment1() {
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
        mAdapter = new CuisineAdapterold(mCuisine );
        mRecylerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(CUISINES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle bundle){
        switch (loaderId){
            case CUISINES_LOADER:
                Uri recipeNameQuery = Contract.CuisineNameEntry.CONTENT_URI;
                return new CursorLoader(getActivity(),
                        recipeNameQuery,
                        CUISINE_COLUMNS_PROJECTION,
                        null,
                        null,
                        null);

            default:
                throw  new RuntimeException("Loader not Implemented" + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mAdapter.swapCursor(data);
        if(mPosition == RecyclerView.NO_POSITION){
            mPosition = 0;
        }
        mRecylerView.smoothScrollToPosition(mPosition);

        //setMainRecipeIndex(data.getString(COL_RECIPE_ID));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mAdapter.swapCursor(null);
    }
}
