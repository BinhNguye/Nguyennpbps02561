package edu.poly.inf205_app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    EditText edtTenCV;
    EditText edtId;
    Button btnSave;
    Button btnXoa;
    Button btnSua;
    ListView lvCV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "BEFi8jqGkl3lffchyK24aRir6ta6MnDglSNVQCaI", "hmg0eotwyLcpgXJkccxY0dIVyVPl7fbUmM4KnhcT");


        //Anh xa
        edtId = (EditText)findViewById(R.id.editTextIdCongViec);
        edtTenCV = (EditText)findViewById(R.id.editTextTenCongViec);
        btnSave = (Button)findViewById(R.id.buttonSaveCongViec);
        btnXoa = (Button)findViewById(R.id.buttonXoaCongViec);
        btnSua = (Button)findViewById(R.id.buttonSuaCongViec);
        lvCV = (ListView)findViewById(R.id.listViewCongViec);


        //Click button save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  id = edtId.getText().toString();
                String ten = edtTenCV.getText().toString();
                ParseObject work = new ParseObject("CongViec");
                work.put("Id", id);
                work.put("TenCongViec", ten);
                work.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        final ArrayList<String> mangCV = new ArrayList<String>();


                        //Parse , get CongViec
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("CongViec");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> scoreList, ParseException e) {
                                if (e == null) {
                                    for (ParseObject cv : scoreList) {
                                        mangCV.add(cv.getString("Id"));
                                        mangCV.add(cv.getString("TenCongViec"));
                                    }
                                    ArrayAdapter adapter = new ArrayAdapter(
                                            MainActivity.this,
                                            android.R.layout.simple_list_item_1,
                                            mangCV
                                    );

                                    lvCV.setAdapter(adapter);
                                    Toast.makeText(MainActivity.this, "" + scoreList.size(), Toast.LENGTH_LONG).show();
                                } else {
                                    Log.d("score", "Error: " + e.getMessage());
                                }
                            }
                        });
                        Toast.makeText(
                                MainActivity.this,
                                "Save thanh cong !",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = edtId.getText().toString();
                String ten = edtTenCV.getText().toString();
                ParseObject work = new ParseObject("CongViec");
                work.put("Id", id);
                work.put("TenCongViec", ten);
                work.deleteInBackground(new DeleteCallback() {
                    @Override
                    public void done(ParseException e) {
                        final ArrayList<String> mangCV = new ArrayList<String>();


                        //Parse , get CongViec
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("CongViec");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> scoreList, ParseException e) {
                                if (id == id) {
                                    for (ParseObject cv : scoreList) {
                                        mangCV.remove(cv.getString("Id"));
                                        mangCV.remove(cv.getString("TenCongViec"));
                                    }
                                    ArrayAdapter adapter = new ArrayAdapter(
                                            MainActivity.this,
                                            android.R.layout.simple_list_item_1,
                                            mangCV
                                    );

                                    lvCV.setAdapter(adapter);
                                    Toast.makeText(MainActivity.this, "" + scoreList.size(), Toast.LENGTH_LONG).show();
                                } else {
                                    Log.d("score", "Error: " + e.getMessage());
                                }
                            }
                        });
                        Toast.makeText(
                                MainActivity.this,
                                "Xoa thanh cong !",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                });

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String ten = edtTenCV.getText().toString();
                final String id = edtId.getText().toString();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("CongViec");

            // Retrieve the object by id
                query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
                    public void done(ParseObject work, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            work.put("Id", id);
                            work.put("TenCongViec", ten);
                            work.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {

                                    final ArrayList<String> mangCV = new ArrayList<String>();


                                    //Parse , get CongViec
                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("CongViec");
                                    query.findInBackground(new FindCallback<ParseObject>() {
                                        public void done(List<ParseObject> scoreList, ParseException e) {
                                            if (e == null) {
                                                for (ParseObject cv : scoreList) {
                                                   // mangCV.set(cv.getString("Id"));
                                                 //   mangCV.set(cv.getString("TenCongViec"));
                                                }
                                                ArrayAdapter adapter = new ArrayAdapter(
                                                        MainActivity.this,
                                                        android.R.layout.simple_list_item_1,
                                                        mangCV
                                                );

                                                lvCV.setAdapter(adapter);
                                                Toast.makeText(MainActivity.this, "" + scoreList.size(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Log.d("score", "Error: " + e.getMessage());
                                            }
                                        }
                                    });
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Sua thanh cong !",
                                            Toast.LENGTH_LONG
                                    ).show();

                                }
                            });
                        }
                    }
                });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
