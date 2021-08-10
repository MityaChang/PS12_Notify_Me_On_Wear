package sg.edu.rp.c302.id19034275.ps12_notify_me_on_wear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvReminders;
    Button btnAdd;
    ArrayAdapter<Task> aa;
    ArrayList<Task> al;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvReminders = (ListView) findViewById(R.id.lvReminders);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        dbh = new DBHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });

        lvReminders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task item = al.get(i);
                Intent intent = new Intent(MainActivity.this,UpdateDeleteActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("description",item.getDescription());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<Task>();
        if (!dbh.getTasks().isEmpty()) {
            al = dbh.getTasks();
        }

        aa = new ArrayAdapter<Task>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        aa.notifyDataSetChanged();
        lvReminders.setAdapter(aa);


    }
}