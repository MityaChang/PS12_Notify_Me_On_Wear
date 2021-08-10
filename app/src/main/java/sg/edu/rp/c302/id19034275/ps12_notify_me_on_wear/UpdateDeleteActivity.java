package sg.edu.rp.c302.id19034275.ps12_notify_me_on_wear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeleteActivity extends AppCompatActivity {
    EditText etTitle,etDesc,etID;
    Button btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        etTitle = findViewById(R.id.etNameUP);
        etDesc = findViewById(R.id.etDescUP);
        etID = findViewById(R.id.etIdUp);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        DBHelper dbh = new DBHelper(UpdateDeleteActivity.this);
        Intent i = getIntent();
        etID.setText(i.getIntExtra("id",0)+ "");
        etTitle.setText(i.getStringExtra("title"));
        etDesc.setText(i.getStringExtra("description"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etID.getText().toString());
                String title = etTitle.getText().toString();
                String desc = etDesc.getText().toString();
                Task task = new Task(id,title,desc);

                dbh.updateTask(task);
                Toast.makeText(UpdateDeleteActivity.this, "Task successfully updated",
                        Toast.LENGTH_SHORT).show();
                dbh.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etID.getText().toString());
                dbh.deleteTask(id);
                Toast.makeText(UpdateDeleteActivity.this, "Task successfully deleted",
                        Toast.LENGTH_SHORT).show();
                dbh.close();
                finish();
            }
        });
    }
}