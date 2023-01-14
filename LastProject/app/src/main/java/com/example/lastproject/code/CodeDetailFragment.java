package com.example.lastproject.code;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.databinding.FragmentCodeDetailBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class CodeDetailFragment extends Fragment {
    private String top_code="";
    private FragmentCodeDetailBinding binding;
    private MainActivity activity;
    private ArrayList<CodeVO> list;
    private CodeVO vo ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCodeDetailBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        vo = (CodeVO) bundle.getSerializable("vo");

        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });

        binding.tvCodeCategory.setText(vo.getCode_category());
        binding.tvCodeName.setText(vo.getCode_name());


        new CommonMethod().setParams("keyword",vo.getCode_category()).sendPost("findByCate.cd",(isResult, data) -> {
            if(isResult){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {}.getType());
                binding.recvCodeDetail.setAdapter(new CodeDetailAdapter(inflater,list,activity));
                binding.recvCodeDetail.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

            }
        });

        binding.tvModify.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin")){
                Toast.makeText(activity, "기본 상위 코드는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }


        });

        binding.tvDelete.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin")){
                Toast.makeText(activity, "기본 상위 코드는 삭제할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            new CommonMethod().setParams("code_category",vo.getCode_category()).sendPost("deleteTopCode.cd",(isResult, data) -> {
                activity.getSupportFragmentManager().popBackStack();
            });
        });



        return binding.getRoot();
    }
}