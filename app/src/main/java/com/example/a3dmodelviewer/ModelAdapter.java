package com.example.a3dmodelviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.Base64;
import java.util.List;

public class ModelAdapter extends ArrayAdapter<Model> {
    private final Context context;

    public ModelAdapter(Context context, List<Model> models) {
        super(context, R.layout.activity_list_of_model, models);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_list_of_model, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.image = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        TextView name = ((ViewHolder) view.getTag()).name;
        ImageView image = ((ViewHolder) view.getTag()).image;
        Model model = this.getItem(position);
        name.setText(model.name);
        Bitmap picture = getImage(Base64.getDecoder().decode(model.image));
        image.setImageBitmap(ThumbnailUtils.extractThumbnail(picture, 200, 200));
        return view;
    }

    private static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
