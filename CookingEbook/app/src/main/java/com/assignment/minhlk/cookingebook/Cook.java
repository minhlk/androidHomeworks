package com.assignment.minhlk.cookingebook;

import java.util.ArrayList;

/**
 * Created by minhlk on 3/12/17.
 */

class Cook {
    String name;
    int picture;

    public Cook(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }


}
