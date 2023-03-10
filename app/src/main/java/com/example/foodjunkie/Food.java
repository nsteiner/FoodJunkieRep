package com.example.foodjunkie;

public class Food {
    private String name;
    private String birthday;
    private String sex;

    private String imageURL;

    public Food(String name, String birthday, String sex, String imageURL) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;

    }
}
