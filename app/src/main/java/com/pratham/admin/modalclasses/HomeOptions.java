package com.pratham.admin.modalclasses;

public class HomeOptions {

    public String optionName, optionType;
    private int optionImage;

    public HomeOptions(String optionName, String optionType, int optionImage) {
        this.optionName = optionName;
        this.optionType = optionType;
        this.optionImage = optionImage;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public int getOptionImage() {
        return optionImage;
    }

    public void setOptionImage(int optionImage) {
        this.optionImage = optionImage;
    }
}
