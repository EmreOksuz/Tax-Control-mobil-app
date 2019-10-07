package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

import tax.megakent.org.vergicontrol.Main.DatabaseHelper.DBHelper;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.SicilBilgi;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;



public class SicilActivity extends AppCompatActivity {


    List<SicilBilgi> sicilBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;
    private String referansNo;

    private String sicilNo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelistview_sicil);
        Bundle extars = getIntent().getExtras();
        DBHelper dbHelper=new DBHelper(getApplicationContext());

        referansNo = extars.getString("referansNo");
        sicilNo =dbHelper.getSicilNo(referansNo);

     /*   Bundle extars1 = getIntent().getExtras();
        sicilNo = extars1.getString("sicilNo");*/

            Thread timerThread = new Thread(){
                @Override
                public void run() {
                    tableView = (TableView<String[]>) findViewById(R.id.tableview_sicil);

                    AsynCallWsSicil asynCallWsTahsilat = new AsynCallWsSicil();
                    asynCallWsTahsilat.execute();
                }
            };
            timerThread.start();
        }
        protected void onPause(){
            super.onPause();
        }


    private class AsynCallWsSicil extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.SicilService(sicilNo,referansNo);
            return responce;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            List<SicilBilgi> sicilBilgiList = new ArrayList<>();
            String[] veri = null;
            XmlReader.processXMLSicil(result, sicilBilgiList);
            String Out = "";

            sicilBilgiList1.add(new SicilBilgi());
            for (SicilBilgi sicilBilgi : sicilBilgiList) {


                 veri = sicilBilgi.toString().split(",");
                sicilBilgiList1.add(new SicilBilgi(veri[0], veri[1], veri[2], veri[3], veri[4],
                        veri[5],veri[6],veri[7],veri[8],veri[9]));

            }
            //sicilBilgiList1.remove(0);
            String[] header = {"Sicil Bilgilerim","           "};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(SicilActivity.this,header));
            tableIndisleri = new String[10][2];
          //  for (int i=0; i<sicilBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
            tableIndisleri[0][0] = "TC Kimlik No:";
            tableIndisleri[1][0] = "Ad Soyad:";
            tableIndisleri[2][0] = "Sicil No:";
            tableIndisleri[3][0] = "Telefon No:";
            tableIndisleri[4][0] = "Baba Adı:";
            tableIndisleri[5][0] = "Anne Adı:";
            tableIndisleri[6][0] = "Doğum Yeri:";
            tableIndisleri[7][0] = "Doğum Tarihi:";
            tableIndisleri[8][0] = "E-posta:";
            tableIndisleri[9][0] = "Adres:";

            SicilBilgi data = sicilBilgiList1.get(1);
            tableIndisleri[0][1] = data.getReferansNo();
            tableIndisleri[1][1] = data.getAdSoyad();
            tableIndisleri[2][1] = data.getSicilNo();
            tableIndisleri[3][1] = data.getTelNo();
            tableIndisleri[4][1] = data.getBabaAdi();
            tableIndisleri[5][1] = data.getAnaAdi();
            tableIndisleri[6][1] = data.getDogumYeri();
            tableIndisleri[7][1] = data.getDogumTarihi();
            tableIndisleri[8][1] = data.getE_posta();
            tableIndisleri[9][1] = data.getAdres();
          //  }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(2,500);
            //coulmModel.setColumnWidth(2,340);
            tableView.setColumnModel(coulmModel);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(SicilActivity.this,tableIndisleri));


        }
    }
}


