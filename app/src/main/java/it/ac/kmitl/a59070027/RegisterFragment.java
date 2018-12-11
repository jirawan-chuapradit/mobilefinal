package it.ac.kmitl.a59070027;

import android.content.ContentValues;
import android.content.Context;
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

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText user,name,age,password;
    private String userStr,nameStr,passwordStr;
    private ContentValues _row;
    private int ageInt;
    private Button registerBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        registerBtn = getView().findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        if(v==registerBtn){
            register();
        }
    }

    private void register() {
        user = getView().findViewById(R.id.user_id);
        name = getView().findViewById(R.id.name);
        age = getView().findViewById(R.id.age);
        password = getView().findViewById(R.id.password);

        userStr = user.getText().toString();
        nameStr = name.getText().toString();
        passwordStr = password.getText().toString();

        int c = 0;
        for(int i=0;i<nameStr.length();i++)
        {
            char ch=nameStr.charAt(i);
            if(ch==' ')
                c++;
        }



        ageInt = Integer.parseInt(age.getText().toString());

        if(userStr.length() <6 || userStr.length()>12){
            Log.d("REGISER","User Id ต้องมีความยาวอยู่ในช่วง 6 - 12 ตัวอัก\n" +
                    "ษร");
        }else if(c != 1){
            Log.d("REGISER","Name ต้องมีทั้ง ชื่อและนามสกุล โดยคั่นด้วย\n" +
                    "space 1 space เท่านั้น");
        }else if(ageInt <10 || ageInt>80){
            Log.d("REGISER","Age ต้องเป็นตัวเลขเท่านั้นและอยู่ในช่วง 10 - 80");
        }else if(passwordStr.length()<=6){
            Log.d("REGISER","Password มีความยาวมากกว่า 6");
        }else {

                User itemUser = new User();
                itemUser.set_row(userStr,nameStr,ageInt,passwordStr);
                _row = itemUser.get_row();

            SQLiteDatabase db = getActivity().openOrCreateDatabase("mobilefinal",Context.MODE_PRIVATE ,null);
            String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS user (userId TEXT(12), name TEXT, age INTEGER, password TEXT)";
            db.execSQL(CREATE_TEST_TABLE);

            db.insert("user",null,_row);

            Log.d("Register", "DATA HAS BEEN SAVED IN DATABASE");

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new LoginFragment())
                    .addToBackStack(null)
                    .commit();
            Log.d("ADD", "GOTO Login");
        }


    }
}
