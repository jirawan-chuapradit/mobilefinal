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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragemnt extends Fragment {
    ArrayList<String> _menu = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _menu.add("PROFILE SETUP");
        _menu.add("MY FRIENDS");
        _menu.add("SIGN OUT");


        ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,_menu);

        ListView listView = getView().findViewById(R.id.list_view);
        listView.setAdapter(_menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(_menu.get(position).equals("PROFILE SETUP")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();
                    _menu.clear();
                    Log.d("Menu", "GOTO profile");
                }else if(_menu.get(position).equals("MY FRIENDS")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new FriendsFragment())
                            .addToBackStack(null)
                            .commit();
                    _menu.clear();
                    Log.d("Menu", "GOTO friends");
                }else if(_menu.get(position).equals("SIGN OUT")){
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("Test", Context.MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO Login");
                }
            }
        });


        SharedPreferences preferences = getContext().getSharedPreferences("Test", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "0");

        TextView hello = getView().findViewById(R.id.hello);
        hello.setText("Hello " + name);

    }
}
