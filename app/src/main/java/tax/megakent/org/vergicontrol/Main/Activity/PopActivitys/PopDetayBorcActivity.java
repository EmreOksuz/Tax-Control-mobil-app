package tax.megakent.org.vergicontrol.Main.Activity.PopActivitys;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.BorcBilgi;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;


public class PopDetayBorcActivity extends Activity {

    private Bundle extars;
    private String gelenRef;
    private String gelenGelirId;

    private List<BorcBilgi> borcBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detayborc_pop);

        this.setFinishOnTouchOutside(true);//activity dışında bir yere dokununca ekranı kapatır.
        //Ekran setlemeleri yapıyorum
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.5));
        //Xml verilerini alıyorum.
        extars = getIntent().getExtras();
        //önceki activityden gelenleri alıyorum
        gelenRef = extars.getString("referansNo");
        gelenGelirId = extars.getString("gelirId");

        Thread timerThread = new Thread(){
            @Override
            public void run() {
                tableView = (TableView<String[]>) findViewById(R.id.tableview_borcDetay);
                AsynCallWsBorcDetay asynCallWsBorcDetay = new AsynCallWsBorcDetay();
                asynCallWsBorcDetay.execute();

            }
        };
        timerThread.start();
    }
    protected void onPause(){
        super.onPause();
    }
    private class AsynCallWsBorcDetay extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.BorcServiceDetay(gelenGelirId,gelenRef);
            return responce;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            final List<BorcBilgi> borcBilgiList = new ArrayList<>();
            String[] veri = null;
            XmlReader.processXMLBorcDetay(result, borcBilgiList);

            borcBilgiList1.add(new BorcBilgi());
            for (BorcBilgi borcBilgi : borcBilgiList) {
                veri = borcBilgi.toString().split(",");
                borcBilgiList1.add(new BorcBilgi(veri[0], veri[1], veri[2], veri[3], veri[4],veri[5],veri[6],veri[7]));
            }
            borcBilgiList1.remove(0);

            String[] header = {"Gelir Türü","Tutar"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(PopDetayBorcActivity.this,header));
            tableIndisleri = new String[borcBilgiList1.size()][2];
            for (int i=0; i<borcBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
                BorcBilgi b = borcBilgiList1.get(i);
                tableIndisleri[i][0] = b.getBorcTur();
                tableIndisleri[i][1] = b.getToplam()+" ₺";//for'un en son iterasyonunda Toplam tutarı yazıyor.Çünkü b nesnesinin son elemanı
                //Toplam: ve topBorc
            }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(2,300);
            coulmModel.setColumnWidth(0,680);
            coulmModel.setColumnWidth(1,700);
            tableView.setColumnModel(coulmModel);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(PopDetayBorcActivity.this,tableIndisleri));

        }
    }
}


