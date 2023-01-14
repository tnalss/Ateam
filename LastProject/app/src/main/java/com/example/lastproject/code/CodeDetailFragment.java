package com.example.lastproject.code;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.lastproject.databinding.FragmentCodeDetailBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;


public class CodeDetailFragment extends Fragment {
    private String top_code="";
    private FragmentCodeDetailBinding binding;
    private MainActivity activity;
    private ArrayList<CodeVO> list;
    private CodeVO vo;

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


        allList(inflater);
//        if(vo.getCreater().equals("admin")){
//            binding.tvModify.setVisibility(View.GONE);
//            binding.tvDelete.setVisibility(View.GONE);
//        }
        binding.tvModify.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin")){
                Toast.makeText(activity, "기본 상위 코드는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            final LinearLayout linear = (LinearLayout) View.inflate(activity, R.layout.item_dialog_update_top_code, null);

            new AlertDialog.Builder(activity)
                    .setView(linear)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            EditText edt_code_value = (EditText) linear.findViewById(R.id.edt_code_value);

                            String code_value = edt_code_value.getText().toString().trim();

                            new CommonMethod().setParams("top_code",vo.getCode_category()).setParams("code_value",code_value).setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("updateTopCode.cd",(isResult, data) -> {
                                if(isResult){

                                    activity.getSupportFragmentManager().popBackStack();
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

        binding.tvDelete.setOnClickListener(v -> {
            if(vo.getCreater().equals("admin")){
                Toast.makeText(activity, "기본 상위 코드는 삭제할 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(getContext()).setTitle("확인").setMessage("하위코드까지 삭제 됩니다.\n정말로 삭제하시겠습니까?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        new CommonMethod().setParams("code_category",vo.getCode_category()).sendPost("deleteTopCode.cd",(isResult, data) -> {
                            activity.getSupportFragmentManager().popBackStack();
                        });
                    }).setNegativeButton(android.R.string.no, (dialog, which) -> {
                    }).show();

        });


        binding.btnNewCode.setOnClickListener(v -> {
            final LinearLayout linear = (LinearLayout) View.inflate(getContext(), R.layout.item_dialog_new_bottom_code, null);

            new AlertDialog.Builder(getContext())
                    .setView(linear)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            EditText edt_top_code = (EditText) linear.findViewById(R.id.edt_top_code);
                            EditText edt_code_value = (EditText) linear.findViewById(R.id.edt_code_value);
                            String bottom_code = edt_top_code.getText().toString().trim().toUpperCase(Locale.ROOT);
                            String code_value = edt_code_value.getText().toString().trim();
                            Pattern ps = Pattern.compile("^[0-9]*$");

                            if( !ps.matcher(bottom_code).matches()||bottom_code.length()>1){
                                Toast.makeText(activity, "숫자값만 입력가능합니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            new CommonMethod().setParams("code_category",vo.getCode_category()).setParams("bottom_code",bottom_code).setParams("code_value",code_value).setParams("emp_no", Common.loginInfo.getEmp_no()).sendPost("newBottomCode.cd",(isResult, data) -> {
                                if(isResult){
                                    if(data.equals("false")){
                                        Toast.makeText(activity, "중복된 코드가 있습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                    binding.tvNoBottomCode.setVisibility(View.GONE);
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
    void allList(LayoutInflater inflater){
        new CommonMethod().setParams("keyword",vo.getCode_category()).sendPost("findByCate.cd",(isResult, data) -> {
            if(isResult){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                list = gson.fromJson(data, new TypeToken<ArrayList<CodeVO>>() {}.getType());
                binding.recvCodeDetail.setAdapter(new CodeDetailAdapter(inflater,list,activity));
                binding.recvCodeDetail.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
                if(list.size()==0){
                    binding.tvNoBottomCode.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}