package zimmerman.regis.regisapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import zimmerman.regis.regisapplication.Adapters.MyAdapter;
import zimmerman.regis.regisapplication.domain.Classes;
import zimmerman.regis.regisapplication.service.CreateAccountSvcSQLiteImpl;
import zimmerman.regis.regisapplication.service.ICreateAccountSvc;

public class FacultyEditActivity extends AppCompatActivity {

    ListView listView;
    Button addNew;
    ArrayList<Classes> classes;
    //MyAdapter myAdapter;
    CreateAccountSvcSQLiteImpl dBMain;

    private ICreateAccountSvc classesSvc = null;
    private ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_edit);
        //Toolbar addition
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dBMain = new CreateAccountSvcSQLiteImpl(FacultyEditActivity.this);
        classes = new ArrayList<>();
        findid();

        classesSvc = CreateAccountSvcSQLiteImpl.getInstance(getApplicationContext());

        //loadDataIntoListView();


        addNewClass();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Classes> list = classesSvc.retrieveAll();
        adapter = new ArrayAdapter<Classes>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FacultyEditActivity.this,
                        ClassDetailsActivity.class);
                intent.putExtra("schedule", list.get(position));
                startActivity(intent);
            }
        });
    }

    /*
    private void loadDataIntoListView() {

        classes = dBMain.getClassNameTitle();
        myAdapter = new MyAdapter(this,classes);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    */


    private void addNewClass() {
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FacultyEditActivity.this,
                        AddClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        listView = (ListView) findViewById(R.id.classesListViewId);
        addNew = (Button) findViewById(R.id.addNewButtonId);
    }
}