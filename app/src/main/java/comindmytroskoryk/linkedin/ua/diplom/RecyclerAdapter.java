package comindmytroskoryk.linkedin.ua.diplom;


import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.okhttp.internal.framed.Http2;

import java.util.ArrayList;

import retrofit.http.HTTP;

/**
 * Created by Dem on 24.01.2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Unswer> getUNSWER = new ArrayList<>();
    private ViewHolder holder;
    private int count= 1;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvPOS;
        public TextView tvNAME;
        public TextView tvDESCR;
        public ImageButton imbSECOND;


        public ViewHolder(View v) {
            super(v);
            tvPOS = (TextView) v.findViewById(R.id.tvPOS);
            tvNAME = (TextView) v.findViewById(R.id.tvNAME);
            tvDESCR = (TextView) v.findViewById(R.id.tvDESCR);
            imbSECOND = (ImageButton) v.findViewById(R.id.imbSECOND);
        }
    }


    // Конструктор
    public RecyclerAdapter(ArrayList<Unswer> datasets) {
        getUNSWER = datasets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_layout, parent, false);

        ViewHolder vh = new ViewHolder(item);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Unswer unswer = getUNSWER.get(position);
        holder.tvPOS.setText(String.valueOf(count));
        holder.tvNAME.setText(unswer.getTitle());
        holder.tvDESCR.setText(Html.fromHtml(unswer.getDescription()));
        count++;


    }

    @Override
    public int getItemCount() {
        return getUNSWER.size();
    }
}
