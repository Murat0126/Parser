package com.company.StrokaKg;

public class StrokaKg {

    private String price;
    private String phoneNumber;

    public String getCountRooms() {
        return countRooms;
    }

    public void setCountRooms(String countRooms) {
        this.countRooms = countRooms;
    }

    private String countRooms;


    public StrokaKg( String price, String phoneNumber, String countRooms) {

        this.price = price;
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String view ) {
        this.price = view;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Stroka.kg{ " +
                price +'\'' + phoneNumber + '\'' +
                countRooms + '\'' +
                " }";
    }
}













