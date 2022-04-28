package com.nhnacademy.servlet;

import java.util.List;
import java.util.Objects;

public class MartUtil {

    private static List<Food> foodStands;

    private MartUtil(){
    }

    public static void setFood(List<Food> foods) {
        if (Objects.isNull(foodStands)) {
            foodStands = foods;
        }
    }

    public static List<Food> getFoodStands() {
        return foodStands;
    }


}
