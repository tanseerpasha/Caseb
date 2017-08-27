package com.example.tanseer.deliciousfoodies.mainview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tanseer.deliciousfoodies.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d("TANSEERDBVALUE", "" + "service");
//        //Intent intent = new Intent(this, LoadDataFromFirebaseDb.class);
//        //this.startService(intent);
//        Log.d("TANSEERDBVALUE", "" + "service1");




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_cuisine_names, new MainViewFragment())
                    .commit();
        }
    }

    //@Override
//    protected void onStart1(){
//        super.onStart();
//        mMessagesDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Vector<ContentValues> cVVector_Cuisine_Names = new Vector<ContentValues>();
//                cVVector_Cuisine_Names.clear();
//
//
//                String test ="";
//
//                //Log.d("TANSEERDBVALUE", "" + dataSnapshot.getValue());
//                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
//                    //ArrayList cuisine_name = (ArrayList) messageSnapshot.child("cuisine_name").getValue();
//                    String cuisine_name = (String) messageSnapshot.child("cuisine_name").getValue();
//                    //String message = (String) messageSnapshot.child("message").getValue();
//                    Log.d("TANSEERDBVALUE", "" + cuisine_name);
//                    ContentValues cuisineValues = new ContentValues();
//                    cuisineValues.put(Contract.CuisineNameEntry.COLUMN_CUISINE_NAME, cuisine_name);
//                    cVVector_Cuisine_Names.add(cuisineValues);
//
//                }
//
//                if( cVVector_Cuisine_Names.size() > 0 ) {
//                    ContentValues[] cvArray = new ContentValues[cVVector_Cuisine_Names.size()];
//                    cVVector_Cuisine_Names.toArray(cvArray);
//
//                    getApplicationContext().getContentResolver().delete(Contract.CuisineNameEntry.CONTENT_URI,null,null);
//                    getApplicationContext().getContentResolver().bulkInsert(Contract.CuisineNameEntry.CONTENT_URI, cvArray);
//
//                    Log.d("TANSEERDB", "Sync Completetest. " + cVVector_Cuisine_Names.size() + " Inserted");
//
//                }
//
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}
