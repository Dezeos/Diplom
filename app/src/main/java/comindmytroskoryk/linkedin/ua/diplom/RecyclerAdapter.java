package comindmytroskoryk.linkedin.ua.diplom;


import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



/*
Класс реализует адаптер для формирования RecyclerView
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Unswer> getUnswer = new ArrayList<>();
    private String apiKey = "";
    private int count = 1;

    private ViewHolder holder;



    /*
     Конструктор сохраняет в классе адаптера информацию по группе и API KEY
    */
    public RecyclerAdapter(ArrayList<Unswer> datasets,String key) {
        getUnswer = datasets;
        apiKey = key;
    }


    /*
    Вложенный класс ViewHolder реализует разбиение View элементов из xml файла (example_layout)
     с последующим объявлением их, как отдельных элементов.
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {

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


    /*
    Метод реализует формирование View элемента из  xml файла (example_layout),
    с последующей передачей его конструктору класса ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Создается один View элемент из xml файла (example_layout)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_layout, parent, false);

        ViewHolder vh = new ViewHolder(item);
        return vh;

    }




    /*
    Метод реализует заполнение каждого элемента списка в соответствии с полученным ответом (зависит от position),
    также добавляеться реализация нажатия кнопки-картинки и запуск активности для редактирования.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Unswer UNSWER = getUnswer.get(position);
        final String TITLE = UNSWER.getTitle();
        final String DESCRIPTION = UNSWER.getDescription();

        holder.tvPOS.setText(String.valueOf(count));
        holder.tvNAME.setText(UNSWER.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvDESCR.setText(Html.fromHtml(UNSWER.getDescription(),Html.FROM_HTML_MODE_LEGACY));
        }
        else {
            holder.tvDESCR.setText(Html.fromHtml(UNSWER.getDescription()));
        }
        holder.imbSECOND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent( v.getContext(), EditActivity.class );
                intent2.putExtra("Title", TITLE);
                intent2.putExtra("Description", DESCRIPTION);
                intent2.putExtra("aboutGROUPS", getUnswer);
                intent2.putExtra("apiKey", apiKey);
                v.getContext().startActivity(intent2);
                count = 0;
            }
        });
        count++;

    }

    @Override
    public int getItemCount() {
        return getUnswer == null ? 0 : getUnswer.size();
    }


}
