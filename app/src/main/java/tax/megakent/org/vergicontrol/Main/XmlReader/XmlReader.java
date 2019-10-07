package tax.megakent.org.vergicontrol.Main.XmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import tax.megakent.org.vergicontrol.Main.UserInfo.AbonelikBilgi;
import tax.megakent.org.vergicontrol.Main.UserInfo.BeyanEmlBilgi;
import tax.megakent.org.vergicontrol.Main.UserInfo.BorcBilgi;
import tax.megakent.org.vergicontrol.Main.UserInfo.SicilBilgi;
import tax.megakent.org.vergicontrol.Main.UserInfo.TahakkukBilgi;
import tax.megakent.org.vergicontrol.Main.UserInfo.TahsilatBilgi;


public class XmlReader {

    public static void processXMLAbone(String xmlData, List<AbonelikBilgi> abonelikBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("AbonelikSorguResult");
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("AbonelikDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(i).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        abonelikBilgiList.add(getAboneBilgi(current));
                    }else break;
                }
            }
            //lets print Employee list information
            for (AbonelikBilgi abonelikBilgi : abonelikBilgiList) {
                System.out.println(abonelikBilgi.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static AbonelikBilgi getAboneBilgi(Node node) {

        AbonelikBilgi abonelikBilgi  = new AbonelikBilgi();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            abonelikBilgi.setGelirID(getTagValue("GelirID", element));
            abonelikBilgi.setGelirTuru(getTagValue("GelirTuru", element));
            abonelikBilgi.setAboneId(getTagValue("AboneID", element));
            abonelikBilgi.setAboneNo(getTagValue("AboneNo", element));

        }

        return abonelikBilgi;
    }

    public static void processXMLBorc(String xmlData, List<BorcBilgi> borcBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("BorcSorguResult");

            for (int i = 0; i < nodeList.getLength(); i++) {

                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("BorcDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(0).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        borcBilgiList.add(getBorcbilgi(current));
                    }else break;
                }
               /* for (BorcBilgi debtInformation : borcBilgiList) {
                    System.out.println(debtInformation.toString());
                }*/
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    public static void processXMLBorcDetay(String xmlData, List<BorcBilgi> borcBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("BorcDetaySorguResponse");

            for (int i = 0; i < nodeList.getLength(); i++) {

                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("BorcDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(0).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        borcBilgiList.add(getBorcbilgi(current));
                    }else break;
                }
               /* for (BorcBilgi debtInformation : borcBilgiList) {
                    System.out.println(debtInformation.toString());
                }*/
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static BorcBilgi getBorcbilgi(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();

        BorcBilgi borcBilgi  = new BorcBilgi();

        if (node.getNodeType() == node.ELEMENT_NODE){
            Element element = (Element) node;
            borcBilgi.setBorcReferansNo(getTagValue("BorcReferansNo", element));
            borcBilgi.setGelirId(getTagValue("GelirID", element));
            borcBilgi.setBorcTur(getTagValue("BorcTur", element));
            borcBilgi.setDonemTaksit(getTagValue("DonemTaksit", element));
            borcBilgi.setSonOdemeTarihi(getTagValue("SonOdemeTarih", element));
            borcBilgi.setTutari(getTagValue("Tutar", element));
            borcBilgi.setGecikme(getTagValue("Gecikme", element));
            borcBilgi.setToplam(getTagValue("Toplam", element));
        }
        return borcBilgi;
    }

    public static String checkSicilControl(String xmlData) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        SicilBilgi sicilBilgi = new SicilBilgi();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("SicilSorguResult");
            for (int i = 0; i <nodeList.getLength() ; i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    sicilBilgi.setSicilNo(getTagValue("SicilNo",element));
                    return sicilBilgi.getSicilNo();
                }
            }

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    public static void processXMLSicil(String xmlData, List<SicilBilgi> sicilList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("SicilSorguResponse");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node current = nodeList.item(i);
                if (current != null){
                    sicilList.add(getSicilBilgi(nodeList.item(i)));
                }else break;
            }
            //lets print Employee list information
            for (SicilBilgi debtInformation : sicilList) {
                System.out.println(debtInformation.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static SicilBilgi getSicilBilgi(Node node) {
        SicilBilgi sicilBilgi  = new SicilBilgi();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            sicilBilgi.setAdSoyad(getTagValue("AdSoyad", element));
            sicilBilgi.setSicilNo(getTagValue("SicilNo", element));
            sicilBilgi.setReferansNo(getTagValue("ReferansNo", element));
            sicilBilgi.setAdres(getTagValue("Adres", element));
            sicilBilgi.setTelNo(getTagValue("CepTelefonu", element));
            sicilBilgi.setE_posta(getTagValue("EpostaAdresi", element));
            sicilBilgi.setBabaAdi(getTagValue("BabaAdi", element));
            sicilBilgi.setAnaAdi(getTagValue("AnaAdi", element));
            sicilBilgi.setDogumYeri(getTagValue("DogumYeri", element));
            sicilBilgi.setDogumTarihi(getTagValue("DogumTarihi", element));

        }

        return sicilBilgi;
    }

    public static void processXMLTahsilat(String xmlData, List<TahsilatBilgi> tahsilatBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("TahsilatSorguResult");

            for (int i = 0; i < nodeList.getLength(); i++) {

                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("TahsilatDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(0).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        tahsilatBilgiList.add(getTahsilatBilgi(current));
                    }else break;
                }
               /* for (BorcBilgi debtInformation : borcBilgiList) {
                    System.out.println(debtInformation.toString());
                }*/
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    public static void processXMLTahsilatDetay(String xmlData, List<TahsilatBilgi> tahsilatBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("TahsilatDetaySorguResponse");

            for (int i = 0; i < nodeList.getLength(); i++) {

                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("TahsilatDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(0).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        tahsilatBilgiList.add(getTahsilatBilgi(current));
                    }else break;
                }
               /* for (BorcBilgi debtInformation : borcBilgiList) {
                    System.out.println(debtInformation.toString());
                }*/
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static TahsilatBilgi getTahsilatBilgi(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();

        TahsilatBilgi tahsilatBilgi  = new TahsilatBilgi();

        if (node.getNodeType() == node.ELEMENT_NODE){
            Element element = (Element) node;
            // borcBilgi.setAd(getTagValue("Ad", element));
            // borcBilgi.setSoyad(getTagValue("Soyad",element));
            tahsilatBilgi.setBankaRefNo(getTagValue("BankaReferansNo", element));
            tahsilatBilgi.setBorcTur(getTagValue("BorcTur", element));
            tahsilatBilgi.setIslmeTarihi(getTagValue("IslemTarih", element));
            tahsilatBilgi.setTahsilatRefNo(getTagValue("TahsilatReferansNo", element));
            tahsilatBilgi.setTutari(getTagValue("Tutar", element));
        }
        return tahsilatBilgi;
    }

    public static void procexxXMLTahakukukSorgu(String xmlData, List<TahakkukBilgi> tahakkukBilgiList){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("TahakkukSorguResponse");
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("TahakkukDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(i).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        tahakkukBilgiList.add(getTahakkukBilgi(current));
                    }else break;
                }
            }
            //lets print Employee list information
            for (TahakkukBilgi tahakkukBilgi : tahakkukBilgiList) {
                System.out.println(tahakkukBilgi.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static TahakkukBilgi getTahakkukBilgi(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();

        TahakkukBilgi tahakkukBilgi  = new TahakkukBilgi();

        if (node.getNodeType() == node.ELEMENT_NODE){
            Element element = (Element) node;
            tahakkukBilgi.setBorcTur(getTagValue("BorcTur", element));
            tahakkukBilgi.setDonemTaksit(getTagValue("DonemTaksit", element));
            tahakkukBilgi.setSonOdomeTarihi(getTagValue("SonOdemeTarih", element));
            tahakkukBilgi.setOdendi(getTagValue("Odendi", element));
        }
        return tahakkukBilgi;
    }

    public static void procexxXMLBeyanEMLSorgu(String xmlData, List<BeyanEmlBilgi> beyanEmlBilgiList) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("BeyanEMLSorguResult");

            for (int i = 0; i < nodeList.getLength(); i++) {

                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("BeyanEMLDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(0).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        beyanEmlBilgiList.add(getBeyanEMLBilgi(current));
                    }else break;
                }
               /* for (BorcBilgi debtInformation : borcBilgiList) {
                    System.out.println(debtInformation.toString());
                }*/
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    private static BeyanEmlBilgi getBeyanEMLBilgi(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();

        BeyanEmlBilgi beyanEmlBilgi  = new BeyanEmlBilgi();

        if (node.getNodeType() == node.ELEMENT_NODE){
            Element element = (Element) node;
            // borcBilgi.setAd(getTagValue("Ad", element));
            // borcBilgi.setSoyad(getTagValue("Soyad",element));
            beyanEmlBilgi.setBeyanId(getTagValue("BeyanID", element));
           // beyanEmlBilgi.setMahalle(getTagValue("Mahelle", element));
            beyanEmlBilgi.setMevkiAd(getTagValue("MevkiAd", element));
           // beyanEmlBilgi.setPafta(getTagValue("Pafta", element));
          //  beyanEmlBilgi.setAda(getTagValue("Ada", element));
          //  beyanEmlBilgi.setParsel(getTagValue("Parsel", element));
          //  beyanEmlBilgi.setBlokKatBolum(getTagValue("BlokKatBolum", element));
          //  beyanEmlBilgi.setCilt(getTagValue("Cilt", element));
          //  beyanEmlBilgi.setSahife(getTagValue("Sahife", element));
            beyanEmlBilgi.setKayitTarihi(getTagValue("KayitTarihi", element));
            beyanEmlBilgi.setBeyanTipi(getTagValue("BeyanTipi", element));
            beyanEmlBilgi.setYuzOlcum(getTagValue("Yuzolcum", element));
            beyanEmlBilgi.setHissePay(getTagValue("HissePay", element));
            beyanEmlBilgi.setHissePayda(getTagValue("HissePayda", element));
            beyanEmlBilgi.setIktisapTarihi(getTagValue("IktisapTarihi", element));
        }
        return beyanEmlBilgi;
    }

    public static void procexxXMLBeyanTahakukukSorgu(String xmlData, List<TahakkukBilgi> tahakkukBilgiList){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes());
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("BeyanTahakkukSorguResponse");
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList details = ((Element)nodeList.item(i)).getElementsByTagName("TahakkukDetay");//iç içe aynı Tag olduğu için bunu yazdık
                NodeList billingChildren = details.item(i).getChildNodes();
                for(int j = 0; i < billingChildren.getLength(); j++) {
                    Node current = billingChildren.item(j);
                    if (current !=null){
                        tahakkukBilgiList.add(getTahakkukBilgi(current));
                    }else break;
                }
            }
            //lets print Employee list information
            for (TahakkukBilgi tahakkukBilgi : tahakkukBilgiList) {
                System.out.println(tahakkukBilgi.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private static String getTagValue(String tag, Element element) {

        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        if (node==null) return null;//burası kiritik
        return node.getNodeValue();
    }
}
