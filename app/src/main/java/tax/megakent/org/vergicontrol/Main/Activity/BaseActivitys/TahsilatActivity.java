package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;


import tax.megakent.org.vergicontrol.Main.Activity.PopActivitys.PopDetayTahsilatActivity;
import tax.megakent.org.vergicontrol.Main.UserInfo.TahsilatBilgi;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;


public class TahsilatActivity extends AppCompatActivity {

    List<TahsilatBilgi> tahsilatBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;
    private float toplamBorc = 0.0f;
    private String referansNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelistview_tahsilat);

        Bundle extars = getIntent().getExtras();
        referansNo = extars.getString("referansNo");

        Thread timerThread = new Thread(){
            @Override
            public void run() {
                tableView = (TableView<String[]>) findViewById(R.id.tableview_tahsilat);
                AsynCallWsTahsilat asynCallWsTahsilat = new AsynCallWsTahsilat();
                asynCallWsTahsilat.execute();
            }
        };
        timerThread.start();
    }
    protected void onPause(){
        super.onPause();
    }

    private class AsynCallWsTahsilat extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess CS = new WebServiceAccess();
            String responce = CS.TahsilatService(referansNo);
          //  System.out.println(responce);
            return responce;

        }


        protected void onPostExecute(String result){
            super.onPostExecute(result);
            final List<TahsilatBilgi> tahsilatBilgiList = new ArrayList<>();
            String[] veri = null;
            XmlReader.processXMLTahsilat(result, tahsilatBilgiList);
            tahsilatBilgiList1.add(new TahsilatBilgi());
            for (TahsilatBilgi tahsilatBilgi : tahsilatBilgiList) {
                veri = tahsilatBilgi.toString().split(",");
                tahsilatBilgiList1.add(new TahsilatBilgi(veri[0], veri[1], veri[2], veri[3], veri[4]));
                toplamBorc += Float.parseFloat(veri[4]);//toplam borcu hesaplıyoruz
            }
            String topBorc = String.valueOf(toplamBorc);
            tahsilatBilgiList1.remove(0);//İlk gelen isimler gerçek isim olmadığı için sildik

            tahsilatBilgiList1.add(new TahsilatBilgi(null,"Toplam:",null,null,topBorc));
            String[] header = {"Borc Türü","İşlem Tarihi","Tahsilat Referans No","Tutar"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(TahsilatActivity.this,header));
            tableIndisleri = new String[tahsilatBilgiList1.size()][4];
            for (int i=0; i<tahsilatBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
                TahsilatBilgi b = tahsilatBilgiList1.get(i);
                tableIndisleri[i][0] = b.getBorcTur();
                tableIndisleri[i][1] = b.getIslmeTarihi();
                tableIndisleri[i][2] = b.getTahsilatRefNo();
                tableIndisleri[i][3] = b.getTutari()+" ₺";
            }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(4,295);
            coulmModel.setColumnWidth(2,240);

           // tableView.setColumnModel(coulmModel);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(TahsilatActivity.this,tableIndisleri));
            tableView.addDataClickListener(new TableDataClickListener<String[]>() {

                @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                if (rowIndex!=tahsilatBilgiList1.size()-1){//En son satıra basınca yeni activity açmamasını kontrol ediyorum
                    Intent intent = new Intent(TahsilatActivity.this,PopDetayTahsilatActivity.class);
                    TahsilatBilgi b = tahsilatBilgiList1.get(rowIndex);//Tıkladığım satırdaki veriyi nesneye olarak alıyorum
                    intent.putExtra("tahsilatReferansNo",b.getTahsilatRefNo());//sonra o nesneye get'ile erişiyorum ve putExtra

                    startActivity(intent);
                }else
                    Toast.makeText(TahsilatActivity.this, "Gecersiz Talep", Toast.LENGTH_SHORT).show();
            }
        });

        }
    }
}





