package com.example.lastproject.employee;

import static android.app.Activity.RESULT_OK;

import static com.example.lastproject.common.Common.CAMERA_CODE;
import static com.example.lastproject.common.Common.GALLERY_CODE;
import static com.example.lastproject.common.Common.SEARCH_ADDRESS_ACTIVITY;
import static com.example.lastproject.common.Common.loginInfo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
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
import com.example.lastproject.databinding.FragmentEmpUpdateBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;


public class EmpUpdateFragment extends Fragment implements View.OnClickListener {
    private MainActivity activity;
    private FragmentEmpUpdateBinding binding;
    private EmployeeVO vo;
    private String img_path;
    private String[] dialog_item={"카메라", "갤러리"};
    private AlertDialog dialog;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        binding = FragmentEmpUpdateBinding.inflate(inflater,container,false);

        Bundle bundle=getArguments();
        vo = (EmployeeVO) bundle.getSerializable("vo");

        //권한확인
        new Common().checkDangerousPermissions(getActivity());

        if(vo.getProfile_path()!=null){
            Glide.with(this).load(vo.getProfile_path()).error(R.drawable.error_user_profile).into(binding.ivEmpProfile);
        }
        binding.svForm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });
        binding.tvEmpNo.setText(vo.getEmp_no());
        binding.edtEmpName.setText(vo.getEmp_name());
        binding.tvBirth.setText(vo.getBirth().substring(0,10));
        if(vo.getGender().equals("남")){
            binding.radioMale.setChecked(true);
        }else{
            binding.radioFemale.setChecked(true);
        }

        binding.edtEmpEmail.setText(vo.getEmail());
        binding.edtEmpPhone.setText(vo.getPhone());

        int div = vo.getAddress().indexOf("/");
        if(div != -1){
            String tempAddress = vo.getAddress().substring(0,div-1);
            binding.edtAddress.setText(tempAddress);
            binding.edtAddressDetail.setText(vo.getAddress().substring(div+2));
        } else{
            binding.edtAddress.setText(vo.getAddress());
        }
        binding.edtSalary.setText(vo.getSalary()+"");

        if(vo.getAdmin().equals("L0")){
            binding.radioEmp.setChecked(true);
        }else if(vo.getAdmin().equals("L1")){
            binding.radioL1.setChecked(true);
        } else {
            binding.radioOut.setVisibility(View.VISIBLE);
            binding.radioOut.setChecked(true);
        }


        binding.ivBack.setOnClickListener(v -> {
            activity.onBackPressed();
        });


        // 수정 버튼을 누름.
        binding.btnEmpUpdate.setOnClickListener(v -> {
            //입력판단 입력유무판단.
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
            //생일 년/월/일 포맷으로 변경해서 넣어줘야함.
            vo.setBirth( binding.tvBirth.getText().toString());
            vo.setBranch_name(binding.spnBranch.getText().toString());
            vo.setDepartment_name(binding.spnDept.getText().toString());
            vo.setRank_name(binding.spnRank.getText().toString());
            vo.setAddress(binding.edtAddress.getText().toString()+" / "+binding.edtAddressDetail.getText().toString());
            vo.setSalary(Integer.parseInt(binding.edtSalary.getText().toString()));
            if(binding.radioEmp.isChecked()){
                vo.setAdmin("L0");
            } else if(binding.radioEmp.isChecked()) {
                vo.setAdmin("L1");
            } else if(binding.radioEmp.isChecked()){
                vo.setAdmin("X0");
            }
            // 본인이 본인 정보를 변경한 경우는 loginInfo의 내용도 수정해주어야함.
            if(loginInfo.getEmp_no().equals(vo.getEmp_no())){
                loginInfo.setEmp_name(vo.getEmp_name());
                loginInfo.setEmail(vo.getEmail());
                loginInfo.setPhone(vo.getPhone());
                loginInfo.setBirth(vo.getBirth());
                loginInfo.setBranch_name(vo.getBranch_name());
                loginInfo.setRank_name(vo.getRank_name());
                loginInfo.setSalary(vo.getSalary());
            }

            new CommonMethod().setParams("param",vo).sendPostFile("update.emp",img_path,(isResult, data) -> {
                if(isResult) {
//                    EmployeeVO vo2 = new Gson().fromJson(data,EmployeeVO.class);
//                    Bundle bundle2 = new Bundle();
//                    bundle2.putSerializable("vo",vo2);
//                    bundle2.putString("status",vo2.getAtt_code());
//                    Fragment fragment = new EmpDetailFragment();
//                    fragment.setArguments(bundle2);
//                    activity.changeFragment(fragment);
                    activity.getSupportFragmentManager().popBackStack();
                    //팝백스택이 되면 update.emp 스프링도 바꿔야되는데? 버그있나 더 확인하고 바꾸기

                }
            });

        });
        binding.ivEmpProfile.setOnClickListener(v -> {
            showDialog();
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
                binding.spnBranch.setText(vo.getBranch_name());
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
                binding.spnDept.setText(vo.getDepartment_name());
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
                binding.spnRank.setText(vo.getRank_name());
            }
        });


        return binding.getRoot();
    }

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

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            binding.tvBirth.setText(year+"/"+(month+1)+"/"+dayOfMonth);
            vo.setBirth(year+"/"+(month+1)+"/"+dayOfMonth);
        }
    };
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        if( requestCode == CAMERA_CODE && resultCode == RESULT_OK ){
            Glide.with(this).load(img_path).into(binding.ivEmpProfile);
            dialog.dismiss();
        } else if (requestCode == GALLERY_CODE && resultCode == RESULT_OK ){

            img_path = new CommonMethod().getRealPath(intent.getData(),getActivity()); // 가짜 URI 주소로 실제 물리적인 사진파일 위치를 받아옴.
            Glide.with(this).load(img_path).into(binding.ivEmpProfile);
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

    private void showDialog(){
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
    private void galleryMethod(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,"select picture"),GALLERY_CODE);

    }
    //카메라
    private void cameraMethod(){
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