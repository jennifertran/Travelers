package comp3350.travelers.objects;

public class CreditCard {
    private String cardNum;
    private String type;
    private String expireDate;
    private String securityNum;
    private String nameOnCard;

    public CreditCard(String cardNum, String type, String expireDate, String securityNum, String nameOnCard) {
        this.cardNum = cardNum;
        this.type = type;
        this.expireDate = expireDate;
        this.securityNum = securityNum;
        this.nameOnCard = nameOnCard;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCompany() {
        return type;
    }

    public void setCompany(String type) {
        this.type = type;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getSecurityNum() {
        return securityNum;
    }

    public void setSecurityNum(String securityNum) {
        this.securityNum = securityNum;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }


}
