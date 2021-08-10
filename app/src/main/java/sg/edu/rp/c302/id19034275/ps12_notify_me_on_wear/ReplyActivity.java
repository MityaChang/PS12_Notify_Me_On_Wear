package sg.edu.rp.c302.id19034275.ps12_notify_me_on_wear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {
    CharSequence reply;
    Intent intent;
    Bundle remoteInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        reply = null;
        intent = getIntent();
        remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            reply = remoteInput.getCharSequence("status");
        }

        if (reply != null) {
            Toast.makeText(ReplyActivity.this, "You have indicated: " + reply,
                    Toast.LENGTH_SHORT).show();
            String replyText = reply.toString();
            if(replyText.equalsIgnoreCase("Completed")){
                DBHelper dbh = new DBHelper(ReplyActivity.this);
                int size = dbh.getTasks().size();
                int id = dbh.getTasks().get(size-1).getId();
                dbh.deleteTask(id);
                dbh.close();
                finish();
            }

        }

    }
}