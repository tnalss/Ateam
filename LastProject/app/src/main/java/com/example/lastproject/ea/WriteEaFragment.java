package com.example.lastproject.ea;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WriteEaFragment extends Fragment implements View.OnClickListener  {
    MainActivity activity;
    ChipGroup chip_group;
    Chip chip;
    EditText edt_sign_search, edt_apply_search;
    RadioGroup radioGroup;
    Button btn_sign_add, btn_refer_add, btn_search,btn_apply;
    BottomSheetDialog sign_dialog, refer_dialog;
    ArrayList<EaCodeVO> list;
    ArrayList<String> value_list;
    ArrayAdapter<String> arrayAdapter;
    TextView tv_main,tv_form,tv_dep_title;
    Spinner spinner_department;
    AlertDialog.Builder my_alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_ea, container, false);
        my_alert = new AlertDialog.Builder(getContext());
        activity = (MainActivity) getActivity();
        tv_main = v.findViewById(R.id.tv_main);
        tv_form = v.findViewById(R.id.tv_form);
        tv_dep_title = v.findViewById(R.id.tv_dep_title);
        btn_sign_add = v.findViewById(R.id.btn_sign_add);
        btn_refer_add = v.findViewById(R.id.btn_refer_add);
        btn_apply = v.findViewById(R.id.btn_apply);
        radioGroup = v.findViewById((R.id.radioGroup));

        EaCodeVO vo = (EaCodeVO) getArguments().getSerializable("form");
        tv_main.setText(vo.getCode_value());
        tv_form.setText(vo.getCode_value());

        //라디오 버튼 클릭 이벤트
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_depart){
                    tv_dep_title.setVisibility(View.VISIBLE);
                    spinner_department.setVisibility(View.VISIBLE);
                }else{
                    tv_dep_title.setVisibility(View.GONE);
                    spinner_department.setVisibility(View.GONE);
                }
            }
        });

        //결재선 추가 버튼
        btn_sign_add.setOnClickListener(this);

        //참조 추가 버튼
        btn_refer_add.setOnClickListener(this);

        //결재선 추가 다이얼
        sign_dialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        sign_dialog.setContentView(inflater.inflate(R.layout.bottom_sheet_layout, null));
        edt_sign_search = sign_dialog.findViewById(R.id.edt_sign_search);
        btn_search = sign_dialog.findViewById(R.id.btn_search);

        //Chip 사용
        chip_group = sign_dialog.findViewById(R.id.chip_group);

        btn_search.setOnClickListener(this);

        //참조 추가 다이얼
        refer_dialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        refer_dialog.setContentView(inflater.inflate(R.layout.bottom_sheet_layout, null));
        edt_apply_search = refer_dialog.findViewById(R.id.edt_sign_search);




        //부서 스피너
        spinner_department = v.findViewById(R.id.spinner_department);

        new CommonMethod().setParams("cate","D").sendPost("code_list.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaCodeVO>>(){}.getType());
            SpinnerSetting(list);
        });
        btn_apply.setOnClickListener(this);

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
            sign_dialog.show();

            sign_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Log.d("로그", "onDismiss: ");
                }
            });
//            sign_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    Log.d("로그", "onCancel: ");
//                }
//            });


        }else if(v.getId() == R.id.btn_refer_add){
            refer_dialog.show();
        }else if(v.getId() == R.id.btn_search){
           new CommonMethod().setParams("name", edt_sign_search.getText().toString()).sendPost("name_search.ea", (isResult, data) -> {
               Log.d("로그", "onClick: " + data);
           });
        }else if(v.getId() == R.id.btn_apply){
            my_alert.setTitle("알림");
            my_alert.setMessage("상신하시겠습니까?");
            //OK 버튼 눌렀을 때
            my_alert.setPositiveButton("상신하기", (dialog, which) -> {
                Toast.makeText(getContext(), "상신완료", Toast.LENGTH_SHORT).show();
                activity.changeFragment(new EaFragment());
            });
            my_alert.setNegativeButton("취소하기",(dialog, which) -> {
                Toast.makeText(getContext(), "돼지", Toast.LENGTH_SHORT).show();
            });
            my_alert.show();
        }
    }
    //chip 만들기
    public void makeChip(){
        // Chip 인스턴스 생성
        chip = new Chip(getContext());
        // Chip 의 텍스트 지정
        chip.setText(edt_sign_search.getText().toString());
        // 체크 표시 사용 여부
        //chip.setCheckable(true);
        // chip close 아이콘 이미지 지정
        chip.setCloseIcon(activity.getDrawable(R.drawable.ic_close));
        // close icon 표시 여부
        chip.setCloseIconVisible(true);
        // chip group 에 해당 chip 추가
        chip_group.addView(chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip_group.removeView(v);
            }
        });
    }


}