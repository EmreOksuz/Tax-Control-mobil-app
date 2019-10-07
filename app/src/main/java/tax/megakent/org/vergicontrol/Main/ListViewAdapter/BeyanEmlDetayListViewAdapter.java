package tax.megakent.org.vergicontrol.Main.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tax.megakent.org.vergicontrol.Main.UserInfo.BeyanEmlBilgi;
import tax.megakent.org.vergicontrol.R;



public class BeyanEmlDetayListViewAdapter extends BaseAdapter {
    // Veri olarak kişi listesini kullanacak.
    private List list;
    LayoutInflater layoutInflater;
    Context context;

    public BeyanEmlDetayListViewAdapter(Context context, List<BeyanEmlBilgi> list) {
        this.context = context;
        // Layout Inflater tanımlanıyor...
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Burada inflate işlemi yapılıyor.
        // Tasarımını yaptığımız layout dosyamızı view olarak alıyoruz
        View satirView = layoutInflater.inflate(R.layout.adapter_beyanemldetaysorgu, null);

        // Öğelerimizi satirView'dan çağırıyoruz.


        // Mevcut pozisyon için kisi nesnesi oluşturuluyor.
        BeyanEmlBilgi beyanEmlBilgi1 = (BeyanEmlBilgi) list.get(position);

        TextView beyanId = (TextView) satirView.findViewById(R.id.BeyanId_tv);
        TextView mahalle = (TextView) satirView.findViewById(R.id.Mahalle_tv);
        TextView mevkiAd = (TextView) satirView.findViewById(R.id.MevkiAd_tv);
        TextView pafta = (TextView) satirView.findViewById(R.id.Pafta_tv);
        TextView ada = (TextView) satirView.findViewById(R.id.Ada_tv);
        TextView parsel = (TextView) satirView.findViewById(R.id.Parsel_tv);
        TextView blokKatBolum = (TextView) satirView.findViewById(R.id.BlokKatBolum_tv);
        TextView cilt = (TextView) satirView.findViewById(R.id.Cilt_tv);
        TextView sahife = (TextView) satirView.findViewById(R.id.Sahife_tv);
        TextView kayitTarihi = (TextView) satirView.findViewById(R.id.KayitTarihi_tv);
        TextView beyanTipi = (TextView) satirView.findViewById(R.id.BeyanTipi_tv);
        TextView yuzOlcum = (TextView) satirView.findViewById(R.id.YuzOlcum_tv);
        TextView hissePay = (TextView) satirView.findViewById(R.id.HissePay_tv);
        TextView hissePayda = (TextView) satirView.findViewById(R.id.HissePayda_tv);
        TextView iktisapTarihi = (TextView) satirView.findViewById(R.id.IktisapTarihi_tv);


        // Öğelerimize verilerimizi yüklüyoruz.
        beyanId.setText(beyanEmlBilgi1.getBeyanId());
        mahalle.setText(beyanEmlBilgi1.getMahalle());
        mevkiAd.setText(beyanEmlBilgi1.getMevkiAd());
        pafta.setText(beyanEmlBilgi1.getPafta());
        ada.setText(beyanEmlBilgi1.getAda());
        parsel.setText(beyanEmlBilgi1.getParsel());
        blokKatBolum.setText(beyanEmlBilgi1.getBlokKatBolum());
        cilt.setText(beyanEmlBilgi1.getCilt());
        sahife.setText(beyanEmlBilgi1.getSahife());
        kayitTarihi.setText(beyanEmlBilgi1.getKayitTarihi());
        beyanTipi.setText(beyanEmlBilgi1.getBeyanId());
        yuzOlcum.setText(beyanEmlBilgi1.getYuzOlcum());
        hissePay.setText(beyanEmlBilgi1.getHissePay());
        hissePayda.setText(beyanEmlBilgi1.getHissePayda());
        iktisapTarihi.setText(beyanEmlBilgi1.getIktisapTarihi());



        // Mevcut satır için işlem tamam ve view return ediliyor.
        return satirView;
    }
}
