package sg.edu.rp.c346.id2006248.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class NDPSongs2 extends AppCompatActivity {

    Button btnFiveStars, btnHome;
    ListView lv;
    Spinner spinner;
    boolean tf = true;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndpsongs2);

        btnFiveStars = findViewById(R.id.btnFiveStar);
        lv = findViewById(R.id.lv);
        spinner = findViewById(R.id.spinner);
        btnHome = findViewById(R.id.btnHome);

        DBHelper db = new DBHelper(NDPSongs2.this);

        ArrayList<Song> al = db.getAllSongs();

        db.close();

        // ArrayAdapter<Song> aa = new ArrayAdapter<>(NDPSongs2.this, android.R.layout.simple_list_item_1, al);

        adapter = new CustomAdapter(NDPSongs2.this,R.layout.row,al);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        btnFiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPSongs2.this);
                al.clear();

                if(tf){
                    al.addAll(dbh.getAllSongs(5));
                    adapter.notifyDataSetChanged();
                    tf = false;
                    btnFiveStars.setText(R.string.show_all_songs);
                    spinner.setEnabled(false);
                }
                else{
                    al.addAll(dbh.getAllSongs());
                    adapter.notifyDataSetChanged();
                    tf = true;
                    btnFiveStars.setText(R.string.show_all_songs_with_5_stars);
                    spinner.setEnabled(true);
                }

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = al.get(position);
                Intent intent = new Intent(NDPSongs2.this,NDPSongs3.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });


        DBHelper db2 = new DBHelper(NDPSongs2.this);

        ArrayList<Song> al2 = db.getAllSongsYear();

        db2.close();

        ArrayAdapter<Song> aa2 = new ArrayAdapter<>(NDPSongs2.this, android.R.layout.simple_list_item_1, al2);
        aa2.notifyDataSetChanged();

        spinner.setAdapter(aa2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String text = spinner.getSelectedItem().toString();
                int textInt = Integer.parseInt(text);
                DBHelper dbh = new DBHelper(NDPSongs2.this);
                al.clear();
                al.addAll(dbh.getAllSongsYear(textInt));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NDPSongs2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}