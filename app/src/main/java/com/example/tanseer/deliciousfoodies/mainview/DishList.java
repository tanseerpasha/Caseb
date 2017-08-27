package com.example.tanseer.deliciousfoodies.mainview;

/**
 * Created by Supreme on 8/18/2017.
 */

public class DishList {

    //private String dishImage;
    private String dishName;
    private String dishType;
    //private String dishRatingIcon;
    private String dishRatingNumber;
    private String dishDeliveryTime;
    private String dishPrice;

    public DishList(){}


    public DishList(String dishName, String dishType, String dishRatingNumber,
                    String dishDeliveryTime, String dishPrice){
        //this.dishImage = dishImage;
        this.dishName = dishName;
        this.dishType = dishType;
        //this.dishRatingIcon = dishRatingIcon;
        this.dishRatingNumber = dishRatingNumber;
        this.dishDeliveryTime = dishDeliveryTime;
        this.dishPrice = dishPrice;
    }

//    public String getDishImage() {
//        return dishImage;
//    }

    public String getDishName() {
        return dishName;
    }

    public String getDishType() {
        return dishType;
    }

//    public String getDishRatingIcon() {
//        return dishRatingIcon;
//    }

    public String getDishRatingNumber() {
        return dishRatingNumber;
    }

    public String getDishDeliveryTime() {
        return dishDeliveryTime;
    }

    public String getDishPrice() {
        return dishPrice;
    }
}
