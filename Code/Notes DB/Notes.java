package team3.phms;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Notes extends Activity {

    Button addnote;
    Button updatenote;
    ListView notesListView;
    DBHandler dbHandler;
    String[] notes = {"No Notes to show"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        if (dbHandler.dbToString() != null) {
            notes = dbHandler.dbToString();
        }

        ListAdapter notesAdapter= new ArrayAdapter<String>(this, android.R.layout.activity_list_item, notes);
        notesListView = (ListView) findViewById(R.id.notesListView);
        notesListView.setAdapter(notesAdapter);
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String noteClicked = getname(position);
                String note = dbHandler.getnote(noteClicked);
                Intent toNote= new Intent(Notes.this,Newnote.class);
                toNote.putExtra("title", noteClicked);
                toNote.putExtra("note", note);

                startActivity(toNote);
            }
        });

        addnote=(Button)findViewById(R.id.addnote); //add note
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent but1= new Intent(Notes.this,Newnote.class);
                startActivity(but1);
            }

        });


        updatenote=(Button)findViewById(R.id.updatenote);  //update note
        updatenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent but2= new Intent(Notes.this,Updatenote.class);
                startActivity(but2);
            }

        });

    }

    public String getname(int position){
        return notes[position];
    }

}

