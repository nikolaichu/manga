package belstu.diploma.mobile.mangabook.ui;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import belstu.diploma.mobile.mangabook.R;
import belstu.diploma.mobile.mangabook.model.Book;

public class BookAdapter extends ArrayAdapter<Book> {

    ArrayList<Book> bookList = new ArrayList<>();

    public BookAdapter(Context context, int textViewResourceId, ArrayList<Book> objects) {
        super(context, textViewResourceId, objects);
        bookList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        textView.setText(bookList.get(position).getBookNameRus());
        imageView.setImageURI(Uri.parse(bookList.get(position).getCover()));
        return v;

    }
}
