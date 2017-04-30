package team3.phms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Newnote extends AppCompatActivity {

    Button savebutton;
    Button delbutton;
    DBHandler dbHandler;
    EditText noteTitle;
    TextView note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnote);

        noteTitle.setText(getIntent().getStringExtra("title"));
        note.setText(getIntent().getStringExtra("note"));

        noteTitle.findViewById(R.id.noteTitle);
        note.findViewById(R.id.note);

        savebutton =(Button)findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                saveNote();
                Intent toNotes= new Intent(Newnote.this,Notes.class);
                startActivity(toNotes);
            }
        });

        delbutton =(Button)findViewById(R.id.delbutton);
        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();

                Intent toNotes= new Intent(Newnote.this,Notes.class);
                startActivity(toNotes);
            }
        });



    }

    public void saveNote() {
        NotesList name = new NotesList(noteTitle.getText().toString());
        name.set_note(note.getText().toString());
        dbHandler.addNote(name);

    }
    public void delete() {
        String name = noteTitle.getText().toString();
        dbHandler.deleteNote(name);
    }
}
