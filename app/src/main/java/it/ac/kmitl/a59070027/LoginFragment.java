package it.ac.kmitl.a59070027;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener {

    Button loginBtn;
    TextView registerBtn;
    String userId1,password1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences preferences = getContext().getSharedPreferences("Test", Context.MODE_PRIVATE);
        userId1 =preferences.getString("userId","0");
        password1=preferences.getString("password","0");

        if(userId1 != "0" && password1 != "0"){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragemnt())
                    .addToBackStack(null)
                    .commit();
            Log.d("ADD", "GOTO Home");
        }

        loginBtn = getView().findViewById(R.id.loginBtn);
        registerBtn = getView().findViewById(R.id.login_registerBtn);
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==loginBtn){
            login();
        }else if(v==registerBtn){
            register();
        }
    }

    private void register() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new RegisterFragment())
                .commit();
    }

    private void login() {

        EditText _username = getView().findViewById(R.id.login_username);
        EditText _password = getView().findViewById(R.id.login_password);

        String _userStr = _username.getText().toString();
        String _passwordStr = _password.getText().toString();

        if(_userStr.isEmpty() || _passwordStr.isEmpty()){
            Toast.makeText(getActivity()
                    ,"Please fill out this form"
                    ,Toast.LENGTH_SHORT).show();
            Log.d("USER","USERNAME OR PASSWORD IS EMPTY");
        }else {

            //open to use db
            SQLiteDatabase db = getActivity().openOrCreateDatabase("mobilefinal",Context.MODE_PRIVATE ,null);
            String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS user (userId TEXT(12), name TEXT, age INTEGER, password TEXT)";
            db.execSQL(CREATE_TEST_TABLE);


            Cursor cursor = db.rawQuery("SELECT * FROM user", null);

            boolean checkExsits = false;
            String userIdDB,passwordDb;
            Log.d("username:",_userStr);
            Log.d("username:",_passwordStr);

            //set values to each parameter
            while (cursor.moveToNext()){
                userIdDB =cursor.getString(0);

                passwordDb = cursor.getString(3);

                Log.d("DB: ", userIdDB);
                Log.d("DB: ", passwordDb);

                if((_userStr.equals(userIdDB)) && (_passwordStr.equals(passwordDb))){
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("Test", Context.MODE_PRIVATE).edit();
                    editor.putString("userId", _userStr);
                    editor.putString("password", _passwordStr);
                    editor.putString("name",cursor.getString(1) );
                    editor.putString("age",cursor.getString(2) );
                    editor.apply();
                    Log.d("userid: ", _userStr);
                    checkExsits = true;
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new HomeFragemnt())
                            .addToBackStack(null)
                            .commit();
                    Log.d("ADD", "GOTO Home");
                }
            }
            cursor.close();

            if(checkExsits == false){
                Toast.makeText(getActivity()
                        ,"Invalid user or password"
                        ,Toast.LENGTH_SHORT).show();
            }

        }
    }
}
