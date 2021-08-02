package sg.edu.rp.c346.id2006248.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class NDPSongs3 extends AppCompatActivity {

    EditText etSongID, etName, etYear, etSong;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndpsongs3);

        etSongID = findViewById(R.id.etSongID);
        etName = findViewById(R.id.etName2);
        etYear = findViewById(R.id.etYear2);
        etSong = findViewById(R.id.etSong2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb = findViewById(R.id.ratingBar3);

        Intent intent = getIntent();
        Song data = (Song) intent.getSerializableExtra("data");

        etSongID.setText(String.valueOf(data.getId()));
        etSong.setText(data.getTitle());
        etName.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));
        rb.setRating(data.getStars());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPSongs3.this);
                dbh.deleteSong(data.getId());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPSongs3.this);
                data.setTitle(etSong.getText().toString());
                data.setSingers(etName.getText().toString());
                String data3 = etYear.getText().toString();
                int intData3 = Integer.parseInt(data3);

                data.setYear(intData3);
                data.setStars((int) rb.getRating());
                dbh.updateSong(data);
                dbh.close();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NDPSongs3.this,NDPSongs2.class);
                startActivity(intent);
            }
        });

    }
}