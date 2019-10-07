package tax.megakent.org.vergicontrol.Main.UserInfo;



public class BeyanEmlBilgi {

    private String BeyanId;
    private String Mahalle;
    private String MevkiAd;
    private String Pafta;
    private String Ada;
    private String Parsel;
    private String BlokKatBolum;
    private String Cilt;
    private String Sahife;
    private String KayitTarihi;
    private String BeyanTipi;
    private String YuzOlcum;
    private String HissePay;
    private String HissePayda;
    private String IktisapTarihi;

    public BeyanEmlBilgi(){}
    public BeyanEmlBilgi(String beyanId,String mahalle,
                         String mevkiAd,String pafta,String ada,String parsel,String blokKatBolum,
                         String cilt,String sahife,String kayitTarihi,String beyanTipi,
                         String yuzOlcum,String hissePay,String hissePayda,String iktisapTarihi){

        this.setBeyanId(beyanId);this.setHissePayda(hissePayda);this.setMahalle(mahalle);
        this.setMevkiAd(mevkiAd);this.setYuzOlcum(yuzOlcum);this.setCilt(cilt);this.setBlokKatBolum(blokKatBolum);
        this.setKayitTarihi(kayitTarihi);this.setPafta(pafta);this.setHissePay(hissePay);
        this.setBeyanTipi(beyanTipi);this.setSahife(sahife);this.setAda(ada);this.setParsel(parsel);
        this.setIktisapTarihi(iktisapTarihi);

    }

    public String getBeyanId() {
        return BeyanId;
    }

    public void setBeyanId(String beyanId) {
        BeyanId = beyanId;
    }

    public String getMahalle() {
        return Mahalle;
    }

    public void setMahalle(String mahalle) {
        Mahalle = mahalle;
    }

    public String getMevkiAd() {
        return MevkiAd;
    }

    public void setMevkiAd(String mevkiAd) {
        MevkiAd = mevkiAd;
    }

    public String getPafta() {
        return Pafta;
    }

    public void setPafta(String pafta) {
        Pafta = pafta;
    }

    public String getAda() {
        return Ada;
    }

    public void setAda(String ada) {
        Ada = ada;
    }

    public String getParsel() {
        return Parsel;
    }

    public void setParsel(String parsel) {
        Parsel = parsel;
    }

    public String getBlokKatBolum() {
        return BlokKatBolum;
    }

    public void setBlokKatBolum(String blokKatBolum) {
        BlokKatBolum = blokKatBolum;
    }

    public String getCilt() {
        return Cilt;
    }

    public void setCilt(String cilt) {
        Cilt = cilt;
    }

    public String getSahife() {
        return Sahife;
    }

    public void setSahife(String sahife) {
        Sahife = sahife;
    }

    public String getKayitTarihi() {
        return KayitTarihi;
    }

    public void setKayitTarihi(String kayitTarihi) {
        KayitTarihi = kayitTarihi;
    }

    public String getBeyanTipi() {
        return BeyanTipi;
    }

    public void setBeyanTipi(String beyanTipi) {
        BeyanTipi = beyanTipi;
    }

    public String getYuzOlcum() {
        return YuzOlcum;
    }

    public void setYuzOlcum(String yuzOlcum) {
        YuzOlcum = yuzOlcum;
    }

    public String getHissePay() {
        return HissePay;
    }

    public void setHissePay(String hissePay) {
        HissePay = hissePay;
    }

    public String getHissePayda() {
        return HissePayda;
    }

    public void setHissePayda(String hissePayda) {
        HissePayda = hissePayda;
    }

    public String getIktisapTarihi() {
        return IktisapTarihi;
    }

    public void setIktisapTarihi(String iktisatTarihi) {
        IktisapTarihi = iktisatTarihi;
    }

    @Override
    public String toString(){
        return this.BeyanId+ ","+ this.Mahalle +","+ this.MevkiAd+","+ this.Pafta+
        ","+ this.Ada +","+ this.Parsel+","+ this.BlokKatBolum+","+ this.Cilt +
                ","+ this.Sahife +","+ this.KayitTarihi+","+ this.BeyanTipi+","+ this.YuzOlcum
                +","+ this.HissePay +","+ this.HissePayda +","+this.IktisapTarihi;
    }

}
