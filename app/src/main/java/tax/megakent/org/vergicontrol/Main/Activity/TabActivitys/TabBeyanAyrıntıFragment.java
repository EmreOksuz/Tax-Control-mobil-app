package tax.megakent.org.vergicontrol.Main.Activity.TabActivitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tax.megakent.org.vergicontrol.Main.ListViewAdapter.BeyanEmlDetayListViewAdapter;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.Main.UserInfo.BeyanEmlBilgi;
import tax.megakent.org.vergicontrol.Main.XmlReader.XmlReader;
import tax.megakent.org.vergicontrol.R;



public class TabBeyanAyrıntıFragment extends Fragment {

    private List<BeyanEmlBilgi> beyanEmlBilgiList1 = new ArrayList<>();
    private List<BeyanEmlBilgi> beyanEmlBilgiList2 = new ArrayList<>();
    static ListView list;//ekrandaki liste
    BeyanEmlDetayListViewAdapter beyanEmlDetayListViewAdapter;
    private String gelenAboneId,gelenBeyanId;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             gelenAboneId = bundle.getString("aboneId");
             gelenBeyanId = bundle.getString("beyanId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beyanayrintitahakkuk, container, false);
        asyncall();
        return rootView;
    }
    private void asyncall() {

        Thread thred = new Thread(new Runnable() {
            @Override
            public void run() {
                AsynCallWsBeyanDetaySorgu asyncall = new AsynCallWsBeyanDetaySorgu();
                asyncall.execute();
            }
        });
        thred.start();
    }
    public class AsynCallWsBeyanDetaySorgu extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String responce = webServiceAccess.BeyanEmlSorgu(gelenAboneId);
            return responce;
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            final List<BeyanEmlBilgi> beyanEmlBilgiList = new ArrayList<>();
            String[] veri;
            XmlReader.procexxXMLBeyanEMLSorgu(result, beyanEmlBilgiList);

            beyanEmlBilgiList1.add(new BeyanEmlBilgi());
            for (BeyanEmlBilgi beyanEmlBilgi : beyanEmlBilgiList) {
                veri = beyanEmlBilgi.toString().split(",");
                if (beyanEmlBilgi.getBeyanId().equals(gelenBeyanId)) {
                    beyanEmlBilgiList1.add(new BeyanEmlBilgi(veri[0], veri[1], veri[2], veri[3]
                            , veri[4], veri[5], veri[6], veri[7], veri[8], veri[9]
                            , veri[10], veri[11], veri[12], veri[13], veri[14]));
                }

            }
            beyanEmlBilgiList1.remove(0);//İlk gelen isimler gerçek isim olmadığı için sildik



            beyanEmlDetayListViewAdapter = new BeyanEmlDetayListViewAdapter(getActivity(),beyanEmlBilgiList1);
            list = (ListView) getView().findViewById(R.id.listview_beyanEmlDetay);
            list.setAdapter(beyanEmlDetayListViewAdapter);
        }
    }
}
