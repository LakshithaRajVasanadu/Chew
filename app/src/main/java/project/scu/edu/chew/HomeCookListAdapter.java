package project.scu.edu.chew;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lakshitha on 2/2/16.
 */


public class HomeCookListAdapter extends ArrayAdapter<HomeCook> {

    private final List<HomeCook> homeCooks;

    public HomeCookListAdapter(Context context, int resource, List<HomeCook> animals) {
        super(context, resource, animals);
        this.homeCooks = animals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeCook homeCook = homeCooks.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.home_cook_list_row, null);

        TextView nameTextView = (TextView) row.findViewById(R.id.home_cook_row_name);
        nameTextView.setText(homeCook.getName());

        try {
            ImageView imageView = (ImageView) row.findViewById(R.id.home_cook_row_image);
            InputStream inputStream = getContext().getAssets().open(homeCook.getImagePath());
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}





