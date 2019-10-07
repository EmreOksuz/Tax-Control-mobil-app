package tax.megakent.org.vergicontrol.Main.UserInfo;



public class TahakkukBilgi {

    private String BorcReferansNo;
    private String BorcTur;
    private String DonemTaksit;
    private String SonOdomeTarihi;
    private String Tutar;
    private String Odendi;

    public TahakkukBilgi(){}
    public TahakkukBilgi(String borcTur,String donemTaksit,
                         String sonOdomeTarihi,String odendi){
        this.setBorcTur(borcTur);
        this.setDonemTaksit(donemTaksit);
        this.setSonOdomeTarihi(sonOdomeTarihi);
        this.setOdendi(odendi);


    }

    public String getBorcReferansNo() {
        return BorcReferansNo;
    }

    public void setBorcReferansNo(String borcReferansNo) {
        BorcReferansNo = borcReferansNo;
    }

    public String getBorcTur() {
        return BorcTur;
    }

    public void setBorcTur(String borcTur) {
        BorcTur = borcTur;
    }

    public String getDonemTaksit() {
        return DonemTaksit;
    }

    public void setDonemTaksit(String donemTaksit) {
        DonemTaksit = donemTaksit;
    }

    public String getSonOdomeTarihi() {
        return SonOdomeTarihi;
    }

    public void setSonOdomeTarihi(String sonOdomeTarihi) {
        SonOdomeTarihi = sonOdomeTarihi;
    }

    public String getTutar() {
        return Tutar;
    }

    public void setTutar(String tutar) {
        Tutar = tutar;
    }

    public String getOdendi() {
        return Odendi;
    }

    public void setOdendi(String odendi) {
        Odendi = odendi;
    }
    @Override
    public String toString(){
        return  this.BorcTur +","+ this.DonemTaksit + ","
                + this.SonOdomeTarihi + ","+ this.Odendi;
    }
}
