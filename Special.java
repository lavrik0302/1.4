package com.company;

public class Special {
    protected String number;

    public Special(String number) {
        this.number = number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    public String getnumber() {
        return this.number;
    }

    static boolean checknumber(String s){
        return s.matches("^(\\d{7})$");
    }
    static boolean checkSnumber(String s){
        return s.matches("^(\\d{3})$");
    }
}