package com.example.registryapp;

import android.widget.SpinnerAdapter;

public class Model {

    private  String companyName;
    private SpinnerAdapter dropdown_list;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SpinnerAdapter getDropdown_list() {
        return dropdown_list;
    }

    public void setDropdown_list(SpinnerAdapter dropdown_list) {
        this.dropdown_list = dropdown_list;
    }

    public void setDropdown_list() {
    }
}
