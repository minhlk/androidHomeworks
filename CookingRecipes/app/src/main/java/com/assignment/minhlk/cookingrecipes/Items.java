package com.assignment.minhlk.cookingrecipes;

/**
 * Created by minhlk on 3/13/17.
 */

class Items {

    String image,title,link,caption;

    public Items(String image, String title, String link, String caption) {
        this.image = image;
        this.title = title;
        this.link = link;
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
