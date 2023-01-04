package com.example.lastproject.ea;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WriteEaFragment extends Fragment implements View.OnClickListener  {
    EditText edt_sign_search;
    Button btn_sign_add;
    BottomSheetDialog dialog;
    ArrayList<EaCodeVO> list;
    ArrayList<String> value_list;
    ArrayAdapter<String> arrayAdapter;
    TextView tv_main,tv_form;
    Spinner spinner_department;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_ea, container, false);
        tv_main = v.findViewById(R.id.tv_main);
        tv_form = v.findViewById(R.id.tv_form);
        btn_sign_add = v.findViewById(R.id.btn_sign_add);
      //  edt_sign_search = v.findViewById(R.id.edt_sign_search);
        EaCodeVO vo = (EaCodeVO) getArguments().getSerializable("form");
        tv_main.setText(vo.getCode_value());
        tv_form.setText(vo.getCode_value());
        btn_sign_add.setOnClickListener(this);
        dialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        dialog.setContentView(inflater.inflate(R.layout.bottom_sheet_layout, null));
        dialog.findViewById(R.id.edt_sign_search);
        spinner_department = v.findViewById(R.id.spinner_department);
        new CommonMethod().setParams("cate","D").sendPost("code_list.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaCodeVO>>(){}.getType());
            SpinnerSetting(list);
        });


        return v;
    }

    //spinner 값 세팅
    public void SpinnerSetting(ArrayList<EaCodeVO> list){
        value_list = new ArrayList<>();
        for (int i=0; i<list.size();i++){
            value_list.add(list.get(i).getCode_value().toString());
        }
        arrayAdapter = new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, value_list);
        spinner_department.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_sign_add){
            dialog.show();
        }
    }
}