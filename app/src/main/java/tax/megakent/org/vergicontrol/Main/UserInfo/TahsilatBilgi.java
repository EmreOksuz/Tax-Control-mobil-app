package tax.megakent.org.vergicontrol.Main.UserInfo;



public class TahsilatBilgi {



    private String BankaRefNo;
    private String BorcTur;
    private String IslmeTarihi;
    private String TahsilatRefNo;
    private String Tutari;

    public TahsilatBilgi(String bankaRefNo,String borcTur,String islmeTarihi,String tahsilatRefNo,String tutari) {

        this.setBankaRefNo(bankaRefNo);
        this.setBorcTur(borcTur);
        this.setIslmeTarihi(islmeTarihi);
        this.setTahsilatRefNo(tahsilatRefNo);
        this.setTutari(tutari);
    }

    public TahsilatBilgi() {};




    public String getBankaRefNo() {
        return BankaRefNo;
    }

    public void setBankaRefNo(String bankaRefNo) {
        BankaRefNo = bankaRefNo;
    }

    public String getBorcTur() {
        return BorcTur;
    }

    public void setBorcTur(String borcTur) {
        BorcTur = borcTur;
    }

    public String getTahsilatRefNo() {
        return TahsilatRefNo;
    }

    public void setTahsilatRefNo(String tahsilatRefNo) {
        TahsilatRefNo = tahsilatRefNo;
    }

    public String getTutari() {
        return Tutari;
    }

    public void setTutari(String tutari) {
        Tutari = tutari;
    }

    public String getIslmeTarihi() {
        return IslmeTarihi;
    }

    public void setIslmeTarihi(String islmeTarihi) {
        IslmeTarihi = islmeTarihi;
    }
    @Override
    public String toString(){
        return this.BankaRefNo + ","+ this.BorcTur +"," + this.IslmeTarihi + "," + this.TahsilatRefNo +"," +this.Tutari;
    }
}
