package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import tax.megakent.org.vergicontrol.Main.Activity.TabActivitys.TabGeneratorActivity;
import tax.megakent.org.vergicontrol.Main.ListViewAdapter.BeyanEmlDetayListViewAdapter;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.BeyanEmlBilgi;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;


//BeyanId,BeyanTipi,Kayıt Tarihi,.Mevki
public class BeyanEmlDetayActivity extends AppCompatActivity {

    Bundle extars;
    private static String aboneId,gelirId;
    private List<BeyanEmlBilgi> beyanEmlBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;

    ListView list;//ekrandaki liste
    BeyanEmlDetayListViewAdapter beyanEmlDetayListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beyanemldetaysorgu);

        extars = getIntent().getExtras();
        aboneId = extars.getString("aboneId");
        gelirId = extars.getString("gelirId");

        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                tableView = (TableView<String[]>) findViewById(R.id.tableview_beyanEml);
                AsynCallWsBeyanEmlSorgu asynCallWsBeyanEmlSorgu = new AsynCallWsBeyanEmlSorgu();
                asynCallWsBeyanEmlSorgu.execute();

            }
        });
        thread.start();
    }
    protected void onPause(){
        super.onPause();
    }
    private class AsynCallWsBeyanEmlSorgu extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.BeyanEmlSorgu(aboneId);
            return responce;
        }
        protected void onPostExecute(String result){
            final List<BeyanEmlBilgi> beyanEmlBilgiList = new ArrayList<>();
            XmlReader.procexxXMLBeyanEMLSorgu(result, beyanEmlBilgiList);

            beyanEmlBilgiList1.add(new BeyanEmlBilgi());
            for (BeyanEmlBilgi beyanEmlBilgi : beyanEmlBilgiList) {

                String[] veriDizisi = beyanEmlBilgi.toString().split(",");
                beyanEmlBilgiList1.add(new BeyanEmlBilgi(veriDizisi[0],veriDizisi[1], veriDizisi[2], veriDizisi[3]
                        , veriDizisi[4], veriDizisi[5], veriDizisi[6], veriDizisi[7], veriDizisi[8], veriDizisi[9]
                        , veriDizisi[10], veriDizisi[11], veriDizisi[12], veriDizisi[13],veriDizisi[14]));
            }
            beyanEmlBilgiList1.remove(0);
            String[] header = {"BeyanId ","Beyan Tipi","Tarih","Mevki"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(BeyanEmlDetayActivity.this,header));
            tableIndisleri = new String[beyanEmlBilgiList1.size()][4];
            for (int i=0; i<beyanEmlBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
                BeyanEmlBilgi b = beyanEmlBilgiList1.get(i);
                tableIndisleri[i][0] = b.getBeyanId();
                tableIndisleri[i][1] = b.getBeyanTipi();//for'un en son iterasyonunda Toplam tutarı yazıyor.Çünkü b nesnesinin son elemanı
                tableIndisleri[i][2] = b.getKayitTarihi();
                tableIndisleri[i][3] = b.getMevkiAd();
            }
            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(4,200);
            coulmModel.setColumnWidth(0,280);
            coulmModel.setColumnWidth(1,350);
            tableView.setColumnModel(coulmModel);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(BeyanEmlDetayActivity.this,tableIndisleri));
            tableView.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    Intent intent = new Intent(BeyanEmlDetayActivity.this, TabGeneratorActivity.class);
                    //tablonun kendine ait detayı
                    intent.putExtra("aboneId",aboneId);
                    BeyanEmlBilgi beyanEmlBilgi = beyanEmlBilgiList1.get(rowIndex);
                    intent.putExtra("beyanId",beyanEmlBilgi.getBeyanId());
                    intent.putExtra("gelirId",gelirId);
                    startActivity(intent);

                }
            });


        }
    }
}
