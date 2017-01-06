package fr.esigelec.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gpillet on 06/01/2017.
 *
 * adpater for the ListView used to display the ranking
 */

public class ClassementAdapter extends ArrayAdapter<ClassementRow> {

    public ClassementAdapter(Context context, List<ClassementRow> classement) {
        super(context, 0, classement);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cls_layout,parent, false);
        }

        ClassementRowViewHolder viewHolder = (ClassementRowViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ClassementRowViewHolder();
            viewHolder.position = (TextView) convertView.findViewById(R.id.sPos);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.sName);
            viewHolder.points = (TextView) convertView.findViewById(R.id.sPts);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<ClassementRow> row
        ClassementRow row = getItem(position);

        viewHolder.position.setText(row.getPos()+"");
        viewHolder.nom.setText(row.getNom());
        viewHolder.points.setText(row.getPoints()+"");

        return convertView;
    }

    private class ClassementRowViewHolder{
        public TextView position;
        public TextView nom;
        public TextView points;
    }
}
