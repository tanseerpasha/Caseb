package com.example.tanseer.deliciousfoodies.service;

/**
 * Created by Supreme on 8/24/2017.
 */

public class LoadDataFromFirebaseDb {}
        //extends IntentService {

//    private CuisineAdapterold cuisineAdapterold;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mMessagesDatabaseReference;
//    private FirebaseAuth mFirebaseAuth;
//    private ChildEventListener mChildEventListener;
//    private ArrayList<Cuisine> mCuisine = new ArrayList<Cuisine>();
//
//    public LoadDataFromFirebaseDb() {
//
//        super("LoadDataFromFirebaseDb");
//        Log.d("TANSEERDBVALUE", "" + "serviceinside");
//    }
//
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//        Log.d("TANSEERDBVALUE", "" + "service");
//
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("CUISINES");
//        cuisineAdapterold = new CuisineAdapterold(mCuisine);
//
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
//                    Cuisine cuisine1 = new Cuisine(cuisine_name);
//                    mCuisine.add(cuisine1);
//
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
//
//    }



