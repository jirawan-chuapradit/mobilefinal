package it.ac.kmitl.a59070027;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener {

    Button loginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginBtn = getView().findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==loginBtn){
            login();
        }
    }

    private void login() {

        EditText _username = getView().findViewById(R.id.login_username);
        EditText _password = getView().findViewById(R.id.login_password);

        String _userStr = _username.getText().toString();
        String _passwordStr = _password.getText().toString();

        if(_userStr.isEmpty() || _passwordStr.isEmpty()){
            Toast.makeText(getActivity()
                    ,"USERNAME OR PASSWORD IS EMPTY"
                    ,Toast.LENGTH_SHORT).show();
            Log.d("USER","USERNAME OR PASSWORD IS EMPTY");
        }else if(_userStr.length() <=3){
            Toast.makeText(getActivity()
                    ,"USERNAME OR PASSWORD LEES THAN 3"
                    ,Toast.LENGTH_SHORT).show();
            Log.d("USER", "USERAME OR PASSWORD LEES THAN 3");
        }else if(!Character.isUpperCase(_userStr.charAt(0))){
            Toast.makeText(getActivity()
                    ,"USERNAME OR PASSWORD WAS NOT UPPER CASE"
                    ,Toast.LENGTH_SHORT).show();
            Log.d("USER", "USERAME OR PASSWORD WAS WRONG");
        } else{
            SharedPreferences.Editor editor = getContext().getSharedPreferences("Test", Context.MODE_PRIVATE).edit();
            editor.putString("_username", _userStr);
            editor.putString("_password", _passwordStr);
            editor.apply();
            Log.d("test","test");
        }
    }
}
