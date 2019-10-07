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

import tax.megakent.org.vergicontrol.Main.Activity.PopActivitys.PopDetayBorcActivity;
import tax.megakent.org.vergicontrol.Main.UserInfo.BorcBilgi;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;



public class BorcActivity extends AppCompatActivity{

    private List<BorcBilgi> borcBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;
    private float toplamBorc = 0.0f;
    private String referansNo;


    @Override
        protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelistview_borc);
        Bundle extars = getIntent().getExtras();
        referansNo = extars.getString("referansNo");

        Thread timerThread = new Thread(){
                @Override
                public void run() {
                    tableView = (TableView<String[]>) findViewById(R.id.tableview_borc);
                    AsynCallWsBorc asynCallWsBorc = new AsynCallWsBorc();//Soap için gerekli method
                    asynCallWsBorc.execute();
                }
            };

        timerThread.start();

        }
        protected void onPause(){
            super.onPause();
        }

    private class AsynCallWsBorc extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            //TODO:Tc kimlik bilgisi Bandle'ile daha sonra alınacak.Şuan zaman kazanmak için manuel yaptık
            String responce = webServiceAccess.BorcService(referansNo);//Web'e istek yapıyoruz

            //System.out.println(responce);
            return responce;

        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            final List<BorcBilgi> borcBilgiList = new ArrayList<>();
            String[] veri = null;
            XmlReader.processXMLBorc(result, borcBilgiList);

            borcBilgiList1.add(new BorcBilgi());
            for (BorcBilgi borcBilgi : borcBilgiList) {
                veri = borcBilgi.toString().split(",");
                borcBilgiList1.add(new BorcBilgi(veri[0], veri[1], veri[2], veri[3], veri[4],veri[5],veri[6],veri[7]));
                toplamBorc+=Float.parseFloat(veri[7]);//toplam borcu hesaplıyoruz

            }
            String topBorc = String.valueOf(toplamBorc);
            borcBilgiList1.remove(0);//İlk gelen isimler gerçek isim olmadığı için sildik
            borcBilgiList1.add(new BorcBilgi(null,null,"Toplam:",null,null,null,null,topBorc));//burada niye 3. ve 5. setledim çünkü
            String[] header = {"Borc Türü","Dönem Taksit","Ödeme Tarihi","Tutar"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(BorcActivity.this,header));
            tableIndisleri = new String[borcBilgiList1.size()][4];
            for (int i=0; i<borcBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
                BorcBilgi b = borcBilgiList1.get(i);
                tableIndisleri[i][0] = b.getBorcTur();
                tableIndisleri[i][1] = b.getDonemTaksit();
                tableIndisleri[i][2] = b.getSonOdemeTarihi();
                tableIndisleri[i][3] = b.getToplam()+" ₺";//for'un en son iterasyonunda Toplam tutarı yazıyor.Çünkü b nesnesinin son elemanı
                                                    //Toplam: ve topBorc
            }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(4,250);
            coulmModel.setColumnWidth(2,340);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(BorcActivity.this,tableIndisleri));
            tableView.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    if (rowIndex!=borcBilgiList1.size()-1){//En son satıra basınca yeni activity açmamasını kontrol ediyorum
                        Intent intent = new Intent(BorcActivity.this,PopDetayBorcActivity.class);
                        BorcBilgi b = borcBilgiList1.get(rowIndex);//Tıkladığım satırdaki veriyi nesneye olarak alıyorum
                        intent.putExtra("referansNo",b.getBorcReferansNo());//sonra o nesneye get'ile erişiyorum ve putExtra
                        intent.putExtra("gelirId",b.getGelirId());//metodu ile "keyIsmi aynı olmalı" diğer intente gönderiyorum.
                        startActivity(intent);
                    }else
                        Toast.makeText(BorcActivity.this, "Gecersiz Talep", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
