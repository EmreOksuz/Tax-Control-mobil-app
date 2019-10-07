package tax.megakent.org.vergicontrol.Main.SoapWebAccess;


public class  WebServiceConfig {

    private String SOAP_ADDRESS ;
    private String WSDL_TARGET_NAMESPACE ;
    private String OPERATION_NAME_BORCSORGU;
    private String OPERATION_NAME_BORCDETAY_SORGU ;
    private String OPERATION_NAME_TAHSILATSORGU ;
    private String OPERATION_NAME_TAHSILATDETAY_SORGU ;
    private String OPERATION_NAME_ABONELIKSORGU;
    private String OPERATION_NAME_SICILSORGU;

    public WebServiceConfig(){

    }

    public String getSOAP_ADDRESS() {
        return SOAP_ADDRESS;
    }

    public String getWSDL_TARGET_NAMESPACE() {
        return WSDL_TARGET_NAMESPACE;
    }

    public String getOPERATION_NAME_BORCSORGU() {
        return OPERATION_NAME_BORCSORGU;
    }

    public String getOPERATION_NAME_BORCDETAY_SORGU() {
        return OPERATION_NAME_BORCDETAY_SORGU;
    }

    public String getOPERATION_NAME_TAHSILATSORGU() {
        return OPERATION_NAME_TAHSILATSORGU;
    }

    public String getOPERATION_NAME_TAHSILATDETAY_SORGU() {
        return OPERATION_NAME_TAHSILATDETAY_SORGU;
    }

    public String getOPERATION_NAME_ABONELIKSORGU() {
        return OPERATION_NAME_ABONELIKSORGU;
    }

    public String getOPERATION_NAME_SICILSORGU() {
        return OPERATION_NAME_SICILSORGU;
    }

    public void setSOAP_ADDRESS(String SOAP_ADDRESS) {
        this.SOAP_ADDRESS = SOAP_ADDRESS;
    }

    public void setWSDL_TARGET_NAMESPACE(String WSDL_TARGET_NAMESPACE) {
        this.WSDL_TARGET_NAMESPACE = WSDL_TARGET_NAMESPACE;
    }

    public void setOPERATION_NAME_BORCSORGU(String OPERATION_NAME_BORCSORGU) {
        this.OPERATION_NAME_BORCSORGU = OPERATION_NAME_BORCSORGU;
    }

    public void setOPERATION_NAME_BORCDETAY_SORGU(String OPERATION_NAME_BORCDETAY_SORGU) {
        this.OPERATION_NAME_BORCDETAY_SORGU = OPERATION_NAME_BORCDETAY_SORGU;
    }

    public void setOPERATION_NAME_TAHSILATSORGU(String OPERATION_NAME_TAHSILATSORGU) {
        this.OPERATION_NAME_TAHSILATSORGU = OPERATION_NAME_TAHSILATSORGU;
    }

    public void setOPERATION_NAME_TAHSILATDETAY_SORGU(String OPERATION_NAME_TAHSILATDETAY_SORGU) {
        this.OPERATION_NAME_TAHSILATDETAY_SORGU = OPERATION_NAME_TAHSILATDETAY_SORGU;
    }

    public void setOPERATION_NAME_ABONELIKSORGU(String OPERATION_NAME_ABONELIKSORGU) {
        this.OPERATION_NAME_ABONELIKSORGU = OPERATION_NAME_ABONELIKSORGU;
    }

    public void setOPERATION_NAME_SICILSORGU(String OPERATION_NAME_SICILSORGU) {
        this.OPERATION_NAME_SICILSORGU = OPERATION_NAME_SICILSORGU;
    }
}