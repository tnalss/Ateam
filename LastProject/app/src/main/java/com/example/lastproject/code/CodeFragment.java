package com.example.lastproject.code;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentCodeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class CodeFragment extends Fragment {

    private FragmentCodeBinding binding;
    private MainActivity activity;
    private ArrayList<CodeVO> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCodeBinding.inflate(inflater, container, false);
        activity = (MainActivity) getActivity();

        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        allList(inflater);

        //검색기능을 위한 디자인
        binding.ivSearch.setOnClickListener(v -> {
            if (binding.tvMenuTitle.getVisibility() == View.VISIBLE) {
                binding.tvMenuTitle.setVisibility(View.GONE);
                binding.edtFind.setVisibility(View.VISIBLE);
                binding.ivSearch.setVisibility(View.GONE);
                binding.searchButton.setVisibility(View.VISIBLE);
            }
        });

        binding.searchButton.setOnClickListener(v -> {

            binding.tvMenuTitle.setVisibility(View.VISIBLE);
            binding.edtFind.setVisibility(View.GONE);
            binding.ivSearch.setVisibility(View.VISIBLE);
            binding.searchButton.setVisibility(View.GONE);
            allList(inflater);

        });


        binding.edtFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.edtFind.getText().toString().trim().length() > 0) {
                    new CommonMethod().setParams("keyword", binding.edtFind.getText().toString().trim()).sendPost("findBy.cd", (isResult, data) -> {
                        if (isResult) {
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                            list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {
                            }.getType());
                            noResult();
                            binding.recvTopCode.setAdapter(new CodeAdapter(inflater, list, activity));
                            binding.recvTopCode.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.btnNewCode.setOnClickListener(v -> {
            final LinearLayout linear = (LinearLayout) View.inflate(getContext(), R.layout.item_dialog_new_top_code, null);

            new AlertDialog.Builder(getContext())
                    .setView(linear)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            EditText edt_top_code = (EditText) linear.findViewById(R.id.edt_top_code);
                            EditText edt_code_value = (EditText) linear.findViewById(R.id.edt_code_value);
                            String top_code = edt_top_code.getText().toString().trim().toUpperCase(Locale.ROOT);
                            String code_value = edt_code_value.getText().toString().trim();
                            Pattern ps = Pattern.compile("^[a-zA-Z]*$");

                            if( !ps.matcher(top_code).matches()||top_code.length()>1){
                                Toast.makeText(activity, "현재는 A-Z값만 입력가능합니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            new CommonMethod().setParams("top_code",top_code).setParams("code_value",code_value).setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("newTopCode.cd",(isResult, data) -> {
                                if(isResult){
                                    if(data.equals("false")){
                                        Toast.makeText(activity, "중복된 상위 코드가 있습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                    allList(inflater);
                                }

                            });

                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        });

        return binding.getRoot();
    }

    void noResult() {
        if (list.size() == 0) {
            binding.tvNoResult.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoResult.setVisibility(View.GONE);
        }
    }

    void allList(LayoutInflater inflater) {
        new CommonMethod().sendPost("topCodeList.cd", (isResult, data) -> {
            if (isResult) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {
                }.getType());
                binding.recvTopCode.setAdapter(new CodeAdapter(inflater, list, activity));
                binding.recvTopCode.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }
        });
    }


}