package tax.megakent.org.vergicontrol.Main.UserInfo;

import java.io.Serializable;


public class BorcBilgi implements Serializable{

    private String BorcReferansNo;
    private String GelirId;
    private String BorcTur;
    private String DonemTaksit;
    private String SonOdemeTarihi;
    private String Tutari;
    private String Gecikme;
    private String Toplam;


    public BorcBilgi(String borcReferansNo,String gelirId,String borcTur,
                        String donemTaksit,String sonOdemeTarihi,String tutari,String gecikme,String toplam) {


        this.setBorcReferansNo(borcReferansNo);
        this.setGelirId(gelirId);
        this.setBorcTur(borcTur);
        this.setDonemTaksit(donemTaksit);
        this.setSonOdemeTarihi(sonOdemeTarihi);
        this.setTutari(tutari);
        this.setGecikme(gecikme);
        this.setToplam(toplam);
    }

    public BorcBilgi() {

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

    public String getSonOdemeTarihi() {
        return SonOdemeTarihi;
    }

    public void setSonOdemeTarihi(String sonOdemeTarihi) {
        SonOdemeTarihi = sonOdemeTarihi;
    }
    public String getTutari() {
        return Tutari;
    }

    public void setTutari(String tutari) {
        this.Tutari = tutari;
    }

    public String getGecikme() {
        return Gecikme;
    }

    public void setGecikme(String gecikme) {
        Gecikme = gecikme;
    }

    public String getGelirId() {
        return GelirId;
    }

    public void setGelirId(String gelirId) {
        GelirId = gelirId;
    }

    public String getToplam() {
        return Toplam;
    }

    public void setToplam(String toplam) {
        Toplam = toplam;
    }
    @Override
    public String toString(){
        return this.BorcReferansNo+ "," +this.GelirId + ","+ this.BorcTur +","+ this.DonemTaksit + "," +
                this.SonOdemeTarihi + "," + this.Tutari +"," +this.Gecikme + ","+ this.Toplam;
    }
}
