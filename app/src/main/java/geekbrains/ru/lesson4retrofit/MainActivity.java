package geekbrains.ru.lesson4retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import geekbrains.ru.lesson4retrofit.DataBase.OrmApp;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private TextView mInfoTextView;
    private ProgressBar progressBar;
    private EditText editText;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        editText = findViewById(R.id.editText);
        mInfoTextView =  findViewById(R.id.tvLoad);
        progressBar =  findViewById(R.id.progressBar);
        findViewById(R.id.btnLoad).setOnClickListener(this);
        findViewById(R.id.btnLoadRepos).setOnClickListener(this);
        findViewById(R.id.btnLoadDB).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (checkInternet()) return;
        switch (view.getId()) {
            case R.id.btnLoad:
                presenter.onClickLoadUser(editText.getText().toString());
                break;
            case R.id.btnLoadRepos:
                presenter.onClickRepos(editText.getText().toString());
                break;
            case R.id.btnLoadDB:
                presenter.onClickLoadFromDB(editText.getText().toString());
        }
    }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isConnected()) {
            Toast.makeText(this, "Подключите интернет", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


    @Override
    public void setViewText(String string) {
        mInfoTextView.setText(string);
        progressBar.setVisibility(View.GONE);
    }
}
