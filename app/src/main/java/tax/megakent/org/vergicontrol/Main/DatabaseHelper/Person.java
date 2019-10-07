package tax.megakent.org.vergicontrol.Main.DatabaseHelper;

import java.io.Serializable;


public class Person implements Serializable {


    private static final long serialVersionUID = 1L;
    private int id;
    private String personTC;
    private String personPassword;
    private String sicilNo;


    Person(String pName,String pPassword,String sicilNo){
        super();
        this.personTC = pName;
        this.personPassword = pPassword;
        this.sicilNo = sicilNo;
    }
    public String getPersonTC() {
        return personTC;
    }

    public void setPersonTC(String personName) {
        this.personTC = personName;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecurityCode() {
        return sicilNo;
    }

    public void setSecurityCode(String securityCode) {
        this.sicilNo = securityCode;
    }
}
