package airbnb.model;

import java.util.Date;

public class PaymentInfo {
    private String number;
    private String cvc;
    private Date expiration;

    private Double totalPrice;

    public PaymentInfo() {

    }

    public PaymentInfo(String number, String cvc, Date expiration, Double totalPrice) {
        this.number = number;
        this.cvc = cvc;
        this.expiration = expiration;
        this.totalPrice = totalPrice;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
