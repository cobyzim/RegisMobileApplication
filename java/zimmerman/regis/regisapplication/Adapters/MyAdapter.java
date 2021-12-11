package zimmerman.regis.regisapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import zimmerman.regis.regisapplication.R;
import zimmerman.regis.regisapplication.domain.Classes;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Classes> arrayList;

    public MyAdapter(Context context, ArrayList<Classes> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mycustomlistview, null);
            TextView t1_id = (TextView) convertView.findViewById(R.id.id_text);
            TextView t2_name = (TextView) convertView.findViewById(R.id.name_text);


            Classes classes = arrayList.get(position);
            t1_id.setText(String.valueOf(classes.getId()));
            t2_name.setText(classes.getCourseNameTitle());

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }


}
