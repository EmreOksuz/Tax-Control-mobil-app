package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.TahakkukBilgi;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;



public class TahakkukActivity extends AppCompatActivity {

    private List<TahakkukBilgi>  tahakkukBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;
    private static String aboneId,gelirId;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahakkuk);

        Bundle extars = getIntent().getExtras();
        aboneId = extars.getString("aboneId");
        gelirId = extars.getString("gelirId");

        Thread timerThread = new Thread(){
            @Override
            public void run() {
                tableView = (TableView<String[]>) findViewById(R.id.tableview_tahakkuk);
                AsynCallWsTahakkukSorgu asynCallWsBorc = new AsynCallWsTahakkukSorgu();//Soap için gerekli method
                asynCallWsBorc.execute();
            }
        };

        timerThread.start();

    }
    protected void onPause(){
        super.onPause();
    }

    public class AsynCallWsTahakkukSorgu extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.TahakukukSorgu(gelirId, aboneId);
            return responce;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            final List<TahakkukBilgi> tahakkukBilgiList = new ArrayList<>();
            String[] veri = null;
            XmlReader.procexxXMLTahakukukSorgu(result, tahakkukBilgiList);

            tahakkukBilgiList1.add(new TahakkukBilgi());
            for (TahakkukBilgi tahakkukBilgi : tahakkukBilgiList) {
                veri = tahakkukBilgi.toString().split(",");
                tahakkukBilgiList1.add(new TahakkukBilgi(veri[0], veri[1], veri[2], veri[3]));

            }
            tahakkukBilgiList1.remove(0);//İlk gelen isimler gerçek isim olmadığı için sildik
            String[] header = {"Borc Türü", "Dönem Taksit", "Ödeme Tarihi", "Tutar"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getApplicationContext(), header));
            tableIndisleri = new String[tahakkukBilgiList1.size()][4];
            for (int i = 0; i < tahakkukBilgiList1.size(); i++) {//çünkü burada ekranda görülecek olan verilerden dolayı
                TahakkukBilgi b = tahakkukBilgiList1.get(i);
                tableIndisleri[i][0] = b.getBorcTur();
                tableIndisleri[i][1] = b.getDonemTaksit();
                tableIndisleri[i][2] = b.getSonOdomeTarihi();
                tableIndisleri[i][3] = b.getOdendi();//for'un en son iterasyonunda Toplam tutarı yazıyor.Çünkü b nesnesinin son elemanı
                //Toplam: ve topBorc
            }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(4, 250);
            coulmModel.setColumnWidth(2, 340);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows, colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(getApplicationContext(), tableIndisleri));
        }
    }
}
