package tax.megakent.org.vergicontrol.Main.SoapWebAccess;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;




public class WebServiceAccess {

    public String BorcService(String referansno) {

        String SOAP_ACTION = "http://tempuri.org/BorcSorgu";
        String OPERATION_NAME = "BorcSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        PropertyInfo pi = new PropertyInfo();
        pi.setName("referansNo");
        pi.setValue(referansno);
        pi.setType(String.class);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;


        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;


        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();
    }//liste
    public String BorcServiceDetay(String gelirID,String borcReferansNo) {


        String result;

        String SOAP_ACTION = "http://tempuri.org/BorcDetaySorgu";
        String OPERATION_NAME = "BorcDetaySorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("borcReferansNo");
        pi2.setValue(borcReferansNo);
        pi2.setType(String.class);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("gelirID");
        pi.setValue(gelirID);
        pi.setType(String.class);
        request.addProperty(pi2);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();

    }//kayıt

    public String TahsilatService(String referansno) {

        String SOAP_ACTION = "http://tempuri.org/TahsilatSorgu";
        String OPERATION_NAME = "TahsilatSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ
        PropertyInfo pi = new PropertyInfo();
        pi.setName("referansNo");
        pi.setValue(referansno);
        pi.setType(String.class);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;


        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;


        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();


    }//liste
    public String TahsilatServiceDetay(String tahsilatReferansNo) {


        String result;

        String SOAP_ACTION = "http://tempuri.org/TahsilatDetaySorgu";
        String OPERATION_NAME = "TahsilatDetaySorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);


        PropertyInfo pi = new PropertyInfo();
        pi.setName("tahsilatReferansNo");
        pi.setValue(tahsilatReferansNo);
        pi.setType(String.class);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();

    }//kayıt

    public String AbonelikService(String referansno) {

        String SOAP_ACTION = "http://tempuri.org/AbonelikSorgu";
        String OPERATION_NAME = "AbonelikSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ
        PropertyInfo pi = new PropertyInfo();
        pi.setName("referansNo");
        pi.setValue(referansno);
        pi.setType(String.class);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;


        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;


        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();


    }//liste

    public String SicilService(String sicilNo,String referansNo) {


        String SOAP_ACTION = "http://tempuri.org/SicilSorgu";
        String OPERATION_NAME = "SicilSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("sicilNo");
        pi2.setValue(sicilNo);
        pi2.setType(String.class);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("referansNo");
        pi.setValue(referansNo);
        pi.setType(String.class);
        request.addProperty(pi2);
        request.addProperty(pi);

        //request.addAttribute("referansNo","65239212594");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;


        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();

    }//liste
    public String SicilServiceSicilNo(String sicilNo,String referansNo) {


        String result;

        String SOAP_ACTION = "http://tempuri.org/SicilSorgu";
        String OPERATION_NAME = "SicilSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("sicilNo");
        pi2.setValue(referansNo);
        pi2.setType(String.class);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("referansNo");
        pi.setValue(sicilNo);
        pi.setType(String.class);
        request.addProperty(pi2);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;
            XmlReader xmlReaderSicil = new XmlReader();
            result = xmlReaderSicil.checkSicilControl(response);

        } catch (Exception exception) {
            //response=exception.toString();

            result = "ERROR....";
        }
        return result;
        //.length()+"";
        // return response.toString();

    }//kayıt

    public String TahakukukSorgu(String gelirId,String aboneId){
        String result;

        String SOAP_ACTION = "http://tempuri.org/TahakkukSorgu";
        String OPERATION_NAME = "TahakkukSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("gelirID");
        pi2.setValue(gelirId);
        pi2.setType(String.class);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("aboneID");
        pi.setValue(aboneId);
        pi.setType(String.class);
        request.addProperty(pi2);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();

    }
    public String BeyanEmlSorgu(String aboneId){
        String result;

        String SOAP_ACTION = "http://tempuri.org/BeyanEMLSorgu";
        String OPERATION_NAME = "BeyanEMLSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ


        PropertyInfo pi = new PropertyInfo();
        pi.setName("aboneID");
        pi.setValue(aboneId);
        pi.setType(String.class);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
        //.length()+"";
        // return response.toString();

    }

    public String BeyanTahakkukSorgu(String gelenGelirId, String gelenBeyanId) {
        String result;

        String SOAP_ACTION = "http://tempuri.org/BeyanTahakkukSorgu";
        String OPERATION_NAME = "BeyanTahakkukSorgu";
        String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
        String SOAP_ADDRESS = "http://81.214.73.178/EBelediyeWebService/TahsilatService.asmx";

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);

        //SETLEME BAŞARISIZ

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("gelirID");
        pi2.setValue(gelenGelirId);
        pi2.setType(String.class);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("beyanID");
        pi.setValue(gelenBeyanId);
        pi.setType(String.class);
        request.addProperty(pi2);
        request.addProperty(pi);
        //request.addAttribute("referansNo","65239212594");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        String response = null;

        try {
            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            httpTransport.debug = true;
            httpTransport.call(SOAP_ACTION, envelope);
            Log.d("HTTP:\n", envelope.bodyIn.toString());
            response = httpTransport.responseDump;

        } catch (Exception exception) {
            //response=exception.toString();

            response = "ERROR....";
        }
        return response;
    }

}

