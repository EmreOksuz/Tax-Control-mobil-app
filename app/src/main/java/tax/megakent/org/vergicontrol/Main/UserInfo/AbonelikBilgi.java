package tax.megakent.org.vergicontrol.Main.UserInfo;

public class AbonelikBilgi {
    private String GelirID;
    private String GelirTuru;
    private String AboneNo;
    private String AboneId;

    public AbonelikBilgi(){

    }
    public AbonelikBilgi(String gelirID,String gelirTuru,String aboneId,String aboneNo){
        this.setGelirID(gelirID);
        this.setGelirTuru(gelirTuru);
        this.setAboneId(aboneId);
        this.setAboneNo(aboneNo);

    }
    public String getGelirID() {
        return GelirID;
    }

    public void setGelirID(String gelirID) {
        GelirID = gelirID;
    }

    public String getGelirTuru() {
        return GelirTuru;
    }

    public void setGelirTuru(String gelirTuru) {
        GelirTuru = gelirTuru;
    }

    public String getAboneNo() {
        return AboneNo;
    }

    public void setAboneNo(String aboneNo) {
        AboneNo = aboneNo;
    }

    public String getAboneId() {
        return AboneId;
    }

    public void setAboneId(String aboneId) {
        AboneId = aboneId;
    }
    @Override
    public String toString(){
        return this.GelirID + ","+ this.GelirTuru +","+ this.AboneId + "," + this.AboneNo;
    }
}
