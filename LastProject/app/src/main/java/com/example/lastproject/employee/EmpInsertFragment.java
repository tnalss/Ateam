package com.example.lastproject.employee;

import static android.app.Activity.RESULT_OK;

import static com.example.lastproject.common.Common.CAMERA_CODE;
import static com.example.lastproject.common.Common.GALLERY_CODE;
import static com.example.lastproject.common.Common.SEARCH_ADDRESS_ACTIVITY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import com.example.lastproject.common.Common;
import com.example.lastproject.common.SimpleCode;
import com.example.lastproject.common.WebViewActivity;
import com.example.lastproject.databinding.FragmentEmpInsertBinding;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;



public class EmpInsertFragment extends Fragment implements View.OnClickListener {

    private FragmentEmpInsertBinding binding;
    private MainActivity activity;
    private EmployeeVO vo= new EmployeeVO();

    String img_path;
    String[] dialog_item={"카메라", "갤러리"};
    AlertDialog dialog;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentEmpInsertBinding.inflate(inflater,container,false);

        //권한확인
        new Common().checkDangerousPermissions(getActivity());


        //뒤로가기
        binding.ivBack.setOnClickListener(v -> {
           activity.onBackPressed();
        });
        // 신규사원 저장하는 부분인데 비밀번호를 난수로 이메일로 보내줘야할듯?

        binding.cvEmpProfile.setOnClickListener(v -> {
            showDialog();
        });


        //스피너 클릭시 키보드 끄기 기능 필요함!//터치리스너로 하였음!
        binding.svForm.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        binding.llBirth.setOnClickListener(this);
        binding.edtAddress.setOnClickListener(this);
        // 지점 목록
        new CommonMethod().setParams("top_code","B").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                ArrayList<SimpleCode> dep_list;
                dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> b = new ArrayList<>();
                for (int i = 0 ; i < dep_list.size() ; i++){
                    b.add(dep_list.get(i).getCode_value());
                }
                binding.spnBranch.attachDataSource(b);
            }
        });

        //부서 목록
        new CommonMethod().setParams("top_code","D").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
            ArrayList<SimpleCode> dep_list;
            dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
            ArrayList<String> d = new ArrayList<>();
            for (int i = 0 ; i < dep_list.size() ; i++){
                d.add(dep_list.get(i).getCode_value());
            }
            binding.spnDept.attachDataSource(d);
            }
        });
        //직책 목록
        new CommonMethod().setParams("top_code","R").sendPost("codeList.cm",(isResult, data) -> {
            if(isResult){
                ArrayList<SimpleCode> dep_list;
                dep_list = new Gson().fromJson(data,new TypeToken<ArrayList<SimpleCode>>(){}.getType());
                ArrayList<String> r = new ArrayList<>();
                for (int i = 0 ; i < dep_list.size() ; i++){
                    r.add(dep_list.get(i).getCode_value());
                }
                binding.spnRank.attachDataSource(r);
            }
        });


        binding.btnEmpInsert.setOnClickListener(v -> {
            //입력유무판단.
            if(binding.edtEmpName.getText().length()<1 || binding.edtEmpPhone.getText().length()<1||
            binding.edtAddress.getText().length()<1||binding.edtSalary.getText().length()<1|| binding.tvBirth.getText().length()<1||
            binding.edtAddressDetail.getText().length()<1) {
                Toast.makeText(activity, "빈칸을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }


            //문자열로변환필요
            vo.setEmp_name(binding.edtEmpName.getText().toString());
            if(! binding.radioMale.isChecked())
                vo.setGender("여");
            else
                vo.setGender("남");
            vo.setEmail(binding.edtEmpEmail.getText().toString());
            vo.setPhone(binding.edtEmpPhone.getText().toString());
            //다른정보들도 추가 요함 생일은 다이어로그에서 알아서 넣음
            vo.setBranch_name(binding.spnBranch.getText().toString());
            vo.setDepartment_name(binding.spnDept.getText().toString());
            vo.setRank_name(binding.spnRank.getText().toString());
            vo.setAddress(binding.edtAddress.getText().toString()+" / "+binding.edtAddressDetail.getText().toString());
            vo.setSalary(Integer.parseInt(binding.edtSalary.getText().toString()));

            new CommonMethod().setParams("param",vo).sendPostFile("insert.emp",img_path,(isResult, data) -> {
                if(isResult) {
                   EmployeeVO vo2 = new Gson().fromJson(data,EmployeeVO.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("vo",vo2);
                    bundle.putSerializable("status",vo.getAtt_code());
                    Fragment fragment = new EmpDetailFragment();
                    fragment.setArguments(bundle);
                    activity.changeFragment(fragment);

                }
            });
        });


        View v = binding.getRoot();

        return v;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            binding.tvBirth.setText(year+"년 "+(monthOfYear+1)+"월 "+dayOfMonth+"일");
            vo.setBirth(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
        }
    };


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ll_birth || v.getId()==R.id.tv_birth){

            DatePickerDialog dialog = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, listener, 1990, 0, 1);
            dialog.show();
        } else if (v.getId() == R.id.edt_address){
            //주소검색 웹 뷰를 띄움
            Intent i = new Intent(getContext(), WebViewActivity.class);
            startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
//        switch (requestCode) {
//            case SEARCH_ADDRESS_ACTIVITY:
//                if (resultCode == RESULT_OK) {
//                    String data = intent.getExtras().getString("data");
//                    if (data != null) {
//                        binding.edtAddress.setText(data);
//                    }
//                }
//                break;
//        }

        if( requestCode == CAMERA_CODE && resultCode == RESULT_OK ){
            Glide.with(this).load(img_path).into(binding.cvEmpProfile);
            dialog.dismiss();

        } else if (requestCode == GALLERY_CODE && resultCode == RESULT_OK ){
            img_path = new CommonMethod().getRealPath(intent.getData(),getActivity()); // 가짜 URI 주소로 실제 물리적인 사진파일 위치를 받아옴.
            Glide.with(this).load(img_path).into(binding.cvEmpProfile);
            dialog.dismiss();
        } else if (requestCode == SEARCH_ADDRESS_ACTIVITY && resultCode==RESULT_OK){

            String data = intent.getExtras().getString("data");
                    if (data != null) {
                        binding.edtAddress.setText(data);
                    }
        }
    }
    //키보드 숨겨주는 메소드
    private void hideKeyboard()    {
        if (getActivity() != null && getActivity().getCurrentFocus() != null)        {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("사진 업로드 방법 선택").setSingleChoiceItems(dialog_item, -1, (dialog, i) -> {
            if((dialog_item[i]).equals("카메라")){
                cameraMethod();
            }else{
                galleryMethod();
            }
        } );
        dialog = builder.create();
        dialog.show();
    }

    //갤러리
    public void galleryMethod(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,"select picture"),GALLERY_CODE);

    }
    //카메라
    public void cameraMethod(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = new CommonMethod().createFile(getActivity());//임시파일 만들어 오기 <- provider 사용시 필요함.
        img_path=file.getAbsolutePath(); //파일의 실제경로 저장 storage/emulated/0......
        if(file !=null){//파일이 만들어졌다면
            Uri imgUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName()+".fileprovider",file);
            //manifest의 provider를 보면 authorities를 보면 위 내용을 적어야 된다고 해놓았음.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
            }else{
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
            }
        }

        startActivityForResult(intent,CAMERA_CODE);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}