package tax.megakent.org.vergicontrol.Main.UserInfo;

import org.w3c.dom.Element;



public class SicilBilgi {

    private String AdSoyad;
    private String TelNo;
    private String E_posta;
    private String BabaAdi;
    private String AnaAdi;
    private String DogumYeri;
    private String DogumTarihi;
    private String Adres;
    private String SicilNo;
    private String ReferansNo;

    public SicilBilgi(){

    }
    public SicilBilgi(String adSoyad,String sicilNo,String referansNo,String telNo,
                      String e_posta,String babaAdi,String anaAdi,String dogumYeri,String dogumTarihi,String adres){
        this.setAdSoyad(adSoyad);
        this.setTelNo(telNo);
        this.setE_posta(e_posta);
        this.setBabaAdi(babaAdi);
        this.setAnaAdi(anaAdi);
        this.setDogumYeri(dogumYeri);
        this.setDogumTarihi(dogumTarihi);
        this.setAdres(adres);
        this.setSicilNo(sicilNo);
        this.setReferansNo(referansNo);

    }

    public String getAdSoyad() {
        return AdSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        AdSoyad = adSoyad;
    }

    public String getTelNo() {
        return TelNo;
    }

    public void setTelNo(String telNo) {
        TelNo = telNo;
    }

    public String getE_posta() {
        return E_posta;
    }

    public void setE_posta(String e_posta) {
        E_posta = e_posta;
    }

    public String getBabaAdi() {
        return BabaAdi;
    }

    public void setBabaAdi(String babaAdi) {
        BabaAdi = babaAdi;
    }

    public String getAnaAdi() {
        return AnaAdi;
    }

    public void setAnaAdi(String anaAdi) {
        AnaAdi = anaAdi;
    }

    public String getDogumYeri() {
        return DogumYeri;
    }

    public void setDogumYeri(String dogumYeri) {
        DogumYeri = dogumYeri;
    }

    public String getDogumTarihi() {
        return DogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        DogumTarihi = dogumTarihi;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    public String getSicilNo() {
        return SicilNo;
    }

    public void setSicilNo(String sicilNo) { SicilNo = sicilNo;}
    public String getReferansNo() {
        return ReferansNo;
    }

    public void setReferansNo(String referansNo) {
        ReferansNo = referansNo;
    }
    @Override
    public String toString(){
        return this.AdSoyad + ","+ this.SicilNo +","+ this.ReferansNo + "," + this.TelNo + "," + this.E_posta
                +","+ this.BabaAdi+","+ this.AnaAdi+","+ this.DogumYeri+","+ this.DogumTarihi+","+ this.Adres;
    }

}
