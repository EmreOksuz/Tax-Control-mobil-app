package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnPxWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.AbonelikBilgi;


import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;


public class AbonelikActivity extends AppCompatActivity {

    List<AbonelikBilgi> abonelikBilgiList1 = new ArrayList<>();
    private static TableView<String[]> tableView;
    private String[][] tableIndisleri;
    private String referansNo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelistview_abonelik);
        Bundle extars = getIntent().getExtras();
        referansNo = extars.getString("referansNo");

        Thread timerThread = new Thread(){
            @Override
            public void run() {
                tableView = (TableView<String[]>) findViewById(R.id.tableview_abonelik);
                AsynCallWsAbonelik asynCallWsTahsilat = new AsynCallWsAbonelik();
                asynCallWsTahsilat.execute();
            }
        };
        timerThread.start();
    }
    protected void onPause(){
        super.onPause();
    }


    private class AsynCallWsAbonelik extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.AbonelikService(referansNo);
            return responce;
        }
        protected void onPostExecute(String result){
            //txtvwdata.setText("");
            super.onPostExecute(result);
            List<AbonelikBilgi> abonelikBilgiList = new ArrayList<>();

            //txtvwdata.setText(debtInformation.toString());
            XmlReader.processXMLAbone(result, abonelikBilgiList);
            String Out = "";

            abonelikBilgiList1.add(new AbonelikBilgi());
            for (AbonelikBilgi abonelikBilgi : abonelikBilgiList) {
                //System.out.println(debtInformation.toString());
                //Out += debQuery.toString();

                String[] veri = abonelikBilgi.toString().split(",");
                abonelikBilgiList1.add(new AbonelikBilgi(veri[0], veri[1], veri[2], veri[3]));

                //toplam += Float.parseFloat(veri[5]);

            }
            abonelikBilgiList1.remove(0);
            // txtvwdata.setText("Abone Ad-Soyad: "+isim+ "\t"+"Toplam Borç: " + toplam);

            String[] header = {"Gelir ID","Gelir Türü","Abone ID","Abone No"};
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(AbonelikActivity.this,header));
            tableIndisleri = new String[abonelikBilgiList1.size()][4];
            for (int i=0; i<abonelikBilgiList1.size(); i++){//çünkü burada ekranda görülecek olan verilerden dolayı
                AbonelikBilgi b = abonelikBilgiList1.get(i);
                tableIndisleri[i][0] = b.getGelirID();
                tableIndisleri[i][1] = b.getGelirTuru();
                tableIndisleri[i][2] = b.getAboneId();
                tableIndisleri[i][3] = b.getAboneNo();
            }

            int colorEvenRows = getResources().getColor(R.color.colorBlueAcik);
            int colorOddRows = getResources().getColor(R.color.colorWhite);
            TableColumnPxWidthModel coulmModel = new TableColumnPxWidthModel(4,300);
            coulmModel.setColumnWidth(2,200);
           coulmModel.setColumnWidth(1,400);
            coulmModel.setColumnWidth(0,200);
            tableView.setColumnModel(coulmModel);
            tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.
                    alternatingRowColors(colorEvenRows,colorOddRows));//Satırları tek ve çift olarak farklı renklerde yapıyor
            tableView.setDataAdapter(//tüm işlemler ardından ekranda gösterme işlemi tamamlanıyor.
                    new SimpleTableDataAdapter(AbonelikActivity.this,tableIndisleri));
            tableView.addDataClickListener(new TableDataClickListener<String[]>() {
                @Override
                public void onDataClicked(int rowIndex, String[] clickedData) {
                    AbonelikBilgi abonelikBilgi = abonelikBilgiList1.get(rowIndex);//Tıkladığım satırdaki veriyi nesneye olarak alıyorum
                    if (abonelikBilgi.getGelirID().toString().equals("EML")){//En son satıra basınca yeni activity açmamasını kontrol ediyorum
                        Intent intent = new Intent(AbonelikActivity.this,BeyanEmlDetayActivity.class);
                        intent.putExtra("aboneId",abonelikBilgi.getAboneId());//sonra o nesneye get'ile erişiyorum ve putExtra
                        intent.putExtra("gelirId",abonelikBilgi.getGelirID());//metodu ile "keyIsmi aynı olmalı" diğer intente gönderiyorum.
                        startActivity(intent);
                    }if(abonelikBilgi.getGelirID().toString().equals("CTV") || abonelikBilgi.getGelirID().toString().equals("IR") ) {
                        Intent intent = new Intent(AbonelikActivity.this,TahakkukActivity.class);
                        intent.putExtra("aboneId",abonelikBilgi.getAboneId());//sonra o nesneye get'ile erişiyorum ve putExtra
                        intent.putExtra("gelirId",abonelikBilgi.getGelirID());//metodu ile "keyIsmi aynı olmalı" diğer intente gönderiyorum.

                        startActivity(intent);
                    }
                }
            });
        }
    }
}
