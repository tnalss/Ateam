package com.example.lastproject.ea;


import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.al.AlVO;
import com.example.lastproject.common.Common;
import com.example.lastproject.employee.EmployeeVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class WriteEaFragment extends Fragment implements View.OnClickListener  {
    MainActivity activity;
    ArrayList<EaVO> send_list;
    EaVO send_vo;
    EaCodeVO vo;
    AlVO alvo;
    ArrayList<EaFileVO> file_list;
    ChipGroup chip_sgroup, chip_rgroup;
    Chip chip;
    EditText edt_sign_search, edt_refer_search, edt_ea_title, edt_ea_content;
    RadioGroup radioGroup;
    Button btn_sign_add, btn_refer_add,btn_apply,btn_signer_search,btn_refer_search,btn_add;
    BottomSheetDialog sign_dialog, refer_dialog;
    ArrayList<EaCodeVO> list;
    ArrayList<String> value_list;
    ArrayList<EmployeeVO> emp_list,signer_list;
    ArrayAdapter<String> arrayAdapter;
    TextView tv_main,tv_form,tv_dep_title,tv_sign_check,tv_refer_check, tv_start_date, tv_end_date, tv_type;
    LinearLayout line_vacation;
    RecyclerView recv_sign_search, recv_refer_search,recv_sign_add,recv_refer_add, recv_ea_file;
    Spinner spinner_department;
    AlertDialog.Builder my_alert;
    ArrayList<String> path_list;
    ArrayList<String> name_list;
    EaFileAdapter file_adapter;
    String dep;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_ea, container, false);

        activity = (MainActivity) getActivity();

        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(activity, Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)) {
            Log.d("TAG", "onActivityResult: ");
        }else{
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }


        my_alert = new AlertDialog.Builder(getContext());
        line_vacation = v.findViewById(R.id.line_vacation);
        recv_sign_add = v.findViewById(R.id.recv_sign_add);
        recv_refer_add = v.findViewById(R.id.recv_refer_add);
        recv_ea_file = v.findViewById(R.id.recv_ea_file);
        tv_main = v.findViewById(R.id.tv_main);
        tv_form = v.findViewById(R.id.tv_form);
        tv_sign_check = v.findViewById(R.id.tv_sign_check);
        tv_dep_title = v.findViewById(R.id.tv_dep_title);
        tv_refer_check = v.findViewById(R.id.tv_refer_check);
        tv_start_date = v.findViewById(R.id.tv_start_date);
        tv_end_date = v.findViewById(R.id.tv_end_date);
        tv_type = v.findViewById(R.id.tv_type);
        edt_ea_title = v.findViewById(R.id.edt_ea_title);
        edt_ea_content = v.findViewById(R.id.edt_ea_content);
        btn_sign_add = v.findViewById(R.id.btn_sign_add);
        btn_refer_add = v.findViewById(R.id.btn_refer_add);
        btn_apply = v.findViewById(R.id.btn_apply);
        btn_add = v.findViewById(R.id.btn_add);
        radioGroup = v.findViewById((R.id.radioGroup));

        btn_add.setOnClickListener(this);


        vo = (EaCodeVO) getArguments().getSerializable("form");
        tv_main.setText(vo.getCode_value());
        tv_form.setText(vo.getCode_value());
        if(getArguments().getSerializable("al") != null){
            alvo = (AlVO) getArguments().getSerializable("al");
            line_vacation.setVisibility(View.VISIBLE);
            tv_start_date.setText(alvo.getAl_start_date());
            tv_end_date.setText(alvo.getAl_end_date());
            tv_type.setText(alvo.getAl_code_value());
        }



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
        btn_signer_search = sign_dialog.findViewById(R.id.btn_search);
        recv_sign_search=  sign_dialog.findViewById(R.id.recv_sign_search);



        //Chip 사용
        chip_sgroup = sign_dialog.findViewById(R.id.chip_group);
        btn_signer_search.setOnClickListener(this);

        //참조 추가 다이얼
        refer_dialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        refer_dialog.setContentView(inflater.inflate(R.layout.bottom_sheet_layout, null));

        recv_refer_search=  refer_dialog.findViewById(R.id.recv_sign_search);
        edt_refer_search = refer_dialog.findViewById(R.id.edt_sign_search);
        btn_refer_search = refer_dialog.findViewById(R.id.btn_search);
        chip_rgroup = refer_dialog.findViewById(R.id.chip_group);

        btn_refer_search.setOnClickListener(this);


        //부서 스피너
        spinner_department = v.findViewById(R.id.spinner_department);

        new CommonMethod().setParams("cate","D").sendPost("code_list.ea", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            list = gson.fromJson(data,
                    new TypeToken<ArrayList<EaCodeVO>>(){}.getType());
            SpinnerSetting(list);
        });

        //부서 선택 값 담기
        spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dep = spinner_department.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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


    int flag = 1;
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_sign_add){
            sign_dialog.show();
            flag = 1;
            //바텀 다이얼로그 닫혔을때 이벤트
            sign_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    ArrayList<String> chipList = new ArrayList<>();
                    //chipGrop 각각 데이터 list에 담기
                    for(int i=0;i<chip_sgroup.getChildCount();i++){
                        chip = (Chip)chip_sgroup.getChildAt(i);
                        chipList.add(chip.getText().toString());
                    }
                    if(chipList.size()>0){
                        tv_sign_check.setVisibility(View.GONE);
                        recv_sign_add.setVisibility(View.VISIBLE);
                        new CommonMethod().setParams("chipList",new Gson().toJson(chipList)).sendPost("signer.ea",(isResult, data) -> {
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                            signer_list = gson.fromJson(data,
                                    new TypeToken<ArrayList<EmployeeVO>>(){}.getType());
                            recv_sign_add.setAdapter(new EaSignerListAdatper(getLayoutInflater(),signer_list));
                            recv_sign_add.setLayoutManager(CommonMethod.getVManager(getContext()));
                        });
                    }else{
                        recv_sign_add.setVisibility(View.INVISIBLE);
                        tv_sign_check.setVisibility(View.VISIBLE);
                    }



//                    for(int i=0;i<chipList.size();i++){
//                        Log.d("로그", "onDismiss: "+chipList.get(i));
//                    }
                }
            });
//            sign_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    Log.d("로그", "onCancel: ");
//                }
//            });


        }else if(v.getId() == R.id.btn_refer_add){
            flag = 2;
            refer_dialog.show();
            //바텀 다이얼로그 닫혔을때 이벤트
            refer_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    ArrayList<String> chipList = new ArrayList<>();
                    //chipGrop 각각 데이터 list에 담기
                    for (int i = 0; i < chip_rgroup.getChildCount(); i++) {
                        chip = (Chip) chip_rgroup.getChildAt(i);
                        chipList.add(chip.getText().toString());
                    }
                    if (chipList.size() > 0) {
                        tv_refer_check.setVisibility(View.GONE);
                        recv_refer_add.setVisibility(View.VISIBLE);
                        new CommonMethod().setParams("chipList", new Gson().toJson(chipList)).sendPost("signer.ea", (isResult, data) -> {
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                            signer_list = gson.fromJson(data,
                                    new TypeToken<ArrayList<EmployeeVO>>() {
                                    }.getType());
                            recv_refer_add.setAdapter(new EaSignerListAdatper(getLayoutInflater(), signer_list));
                            recv_refer_add.setLayoutManager(CommonMethod.getVManager(getContext()));
                        });
                    } else {
                        recv_refer_add.setVisibility(View.INVISIBLE);
                        tv_refer_check.setVisibility(View.VISIBLE);
                    }
                }
            });
        }else if(v.getId() ==btn_signer_search.getId() && flag==1){

           new CommonMethod().setParams("name", edt_sign_search.getText().toString()).sendPost("name_search.ea", (isResult, data) -> {
               Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
               emp_list = gson.fromJson(data,
                       new TypeToken<ArrayList<EmployeeVO>>(){}.getType());


                   recv_sign_search.setAdapter(new WriteSearchAdapter(    flag  ,getLayoutInflater(),emp_list,this));
                   recv_sign_search.setLayoutManager(CommonMethod.getVManager(getContext()));


           });
        }else if(v.getId()==btn_refer_search.getId() && flag==2) {
            new CommonMethod().setParams("name", edt_refer_search.getText().toString()).sendPost("name_search.ea", (isResult, data) -> {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
                emp_list = gson.fromJson(data,
                        new TypeToken<ArrayList<EmployeeVO>>() {
                        }.getType());
                recv_refer_search.setAdapter(new WriteSearchAdapter(flag, getLayoutInflater(), emp_list, this));
                recv_refer_search.setLayoutManager(CommonMethod.getVManager(getContext()));
            });
        }

        else if(v.getId() == R.id.btn_apply){
            my_alert.setTitle("알림");
            my_alert.setMessage("상신하시겠습니까?");
            send_list = new ArrayList<>();
           if(signer_list !=null) {
               for (int i = 0; i < signer_list.size(); i++) {
                       send_vo = new EaVO();
                       send_vo.setEmp_no(Common.loginInfo.getEmp_no());
                       send_vo.setEa_receiver(signer_list.get(i).getEmp_no());
                       send_vo.setEa_title("[" + vo.getCode_value() + "]" + edt_ea_title.getText().toString());
                       send_vo.setEa_content(edt_ea_content.getText().toString());
                       if(dep !=null){
                            send_vo.setEa_pop(dep);
                       }
                       send_list.add(send_vo);
                   }

                   //OK 버튼 눌렀을 때
                   my_alert.setPositiveButton("상신하기", (dialog, which) -> {
                       Toast.makeText(getContext(), "상신완료", Toast.LENGTH_SHORT).show();
                       if(file_list == null){
                           new CommonMethod().setParams("send_list", new Gson().toJson(send_list)).sendPost("insert.ea", (isResult, data) -> {
                               if(alvo !=null){
                                   new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no())
                                           .setParams("al_start_date",alvo.getAl_start_date())
                                           .setParams("al_end_date",alvo.getAl_end_date())
                                           .setParams("al_code",alvo.getAl_code())
                                           .sendPost("al_v_a.al",((isResult1, data1) -> {
                                               if(isResult1) {
                                                   activity.changeFragment(new EaFragment());
                                               }}));
                               }else{
                                   activity.changeFragment(new EaFragment());
                               }


                           });
                       }else{
                           //첨부파일 있는 결재
                           new CommonMethod().setParams("param", send_list)
                                   .sendPostFiles("ea_insert.fi",path_list,name_list,Common.FILE_CODE, (isResult, data) -> {
                                       if(isResult) {
                                           Toast.makeText(activity, "상신 완료", Toast.LENGTH_SHORT).show();
                                           if (alvo != null) {
                                               new CommonMethod().setParams("emp_no", Common.loginInfo.getEmp_no())
                                                       .setParams("al_start_date", alvo.getAl_start_date())
                                                       .setParams("al_end_date", alvo.getAl_end_date())
                                                       .setParams("al_code", alvo.getAl_code())
                                                       .sendPost("al_v_a.al", ((isResult1, data1) -> {
                                                           if (isResult1) {
                                                               activity.changeFragment(new EaFragment());
                                                           }
                                                       }));
                                           } else {
                                               activity.changeFragment(new EaFragment());
                                           }
                                       }
                           });
                       }

                   });
               my_alert.setNegativeButton("취소하기",(dialog, which) -> {
                   Toast.makeText(getContext(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
               });
               my_alert.show();
           }else{
               Toast.makeText(activity, "결재자를 추가해주세요.", Toast.LENGTH_SHORT).show();
           }
           

        }else if(v.getId() == R.id.btn_add) {
            fileMethod();

        }
    }
    //chip 만들기
    public void signmakeChip(String name){
        // Chip 인스턴스 생성
        chip = new Chip(getContext());
        // Chip 의 텍스트 지정
        chip.setText(name);
        // 체크 표시 사용 여부
        //chip.setCheckable(true);
        // chip close 아이콘 이미지 지정
        chip.setCloseIcon(activity.getDrawable(R.drawable.ic_close));
        // close icon 표시 여부
        chip.setCloseIconVisible(true);
        // chip group 에 해당 chip 추가
        chip_sgroup.addView(chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip_sgroup.removeView(v);
            }
        });

    }public void refermakeChip(String name){
        // Chip 인스턴스 생성
        chip = new Chip(getContext());
        // Chip 의 텍스트 지정
        chip.setText(name);
        // 체크 표시 사용 여부
        //chip.setCheckable(true);
        // chip close 아이콘 이미지 지정
        chip.setCloseIcon(activity.getDrawable(R.drawable.ic_close));
        // close icon 표시 여부
        chip.setCloseIconVisible(true);
        // chip group 에 해당 chip 추가
        chip_rgroup.addView(chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip_rgroup.removeView(v);
            }
        });
    }


    public  void fileMethod(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "파일 선택"), Common.FILE_CODE);
        // onActivityResult GALLERY_CODE <- 코드가 나오면 캘러리 액티비티 종료시점을 알수있음.
    }

    //갤러리 선택시 실행 메소드
    public void galleryMethod(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //2023-01-11 사진 선택을 여러개 할수있게
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "사진 선택"), Common.GALLERY_CODE);
    }


    //내보낼 파일 정보 담기
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void allMethod(Intent data, int type){
//        Log.d(TAG, "data 확인 : " + data.getClipData().getItemAt(0).getUri());

        name_list = new ArrayList<>();
        path_list = new ArrayList<>();  //==> String (path)
        file_list = new ArrayList<>();   // ==> EAFileVO
        String realPath = null;
        if( data.getClipData() == null ){
            //한개 선택시 바로 mData 로 접근
            EaFileVO vo = new EaFileVO();
            if(type==Common.FILE_CODE){
                //파일처리
                realPath = getFilePath(data.getData());
                String name = getFileNameToUri(data.getData());
                name_list.add( name );
                vo.setFile_name( name );
            }
            path_list.add( realPath );
            vo.setFile_path( realPath );
            file_list.add(vo);
            Log.d("로그", "allMethod: "+file_list.get(0).file_name);
        }else{
            //여러개 선택시 clipData 있음
            for (int i = 0; i < data.getClipData().getItemCount(); i++){
                EaFileVO vo = new EaFileVO();
                if(type==Common.GALLERY_CODE){
                    //사진처리
                    String name = getImageNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                    realPath = new CommonMethod().getRealPath(data.getClipData().getItemAt(i).getUri(), getContext(), type);
                }else if(type==Common.FILE_CODE){
                    //파일처리
                    realPath = getFilePath(data.getClipData().getItemAt(i).getUri());
                    String name = getFileNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                }
                path_list.add( realPath );
                vo.setFile_path( realPath );

                file_list.add(vo);

            }
            Log.d("로그", "allMethod: "+file_list.get(0).file_name);
            Log.d("로그", "allMethod: "+file_list.get(1).file_name);
        }
    }

    //파일 경로 찾기
    public String getFilePath(Uri uri){
        String path = null;
        try{
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type= split[0];
        final String[] tempSplit = split[1].split("/");

        if ("primary".equalsIgnoreCase(type)) {
            path = Environment.getExternalStorageDirectory() + "/" + (split.length > 1 ? split[1] : ""); //split[1];
        } else   {
            path = Environment.getExternalStorageDirectory() +"/" + tempSplit[4]+ "/" + tempSplit[5];// (split.length > 1 ? split[1] : ""); //split[1];
        }

        }catch (Exception e){
            Toast.makeText(activity, "파일이 깨져 업로드 선택 할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
        return path;
    }

    // 선택된 파일 이름 가져오기
    public String getFileNameToUri(Uri data){
        Cursor returnCursor = activity.getContentResolver().query(data, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameIndex);
        return fileName;
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }
    //어떤 인텐트로 startActivityForResult 를 실행하든 그 결과는 무조건 ↓
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        new Common().checkDangerousPermissions(getActivity());

    //    permissionCheck = ContextCompat.checkSelfPermission(activity, Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);




        if(requestCode == Common.FILE_CODE && resultCode == RESULT_OK){

            allMethod(data, Common.FILE_CODE);
            //어댑터
            file_adapter = new EaFileAdapter(getLayoutInflater(), file_list,0,activity);
            recv_ea_file.setAdapter(file_adapter);
            recv_ea_file.setLayoutManager(CommonMethod.getVManager(getContext()));
            file_adapter.notifyDataSetChanged();

        }else if(requestCode == Common.GALLERY_CODE && resultCode == RESULT_OK){

            allMethod(data, Common.GALLERY_CODE);

//            //어댑터
//            adapter = new NewBoardAdapter(getLayoutInflater(), file_list, this);
//            b.recvImgs.setAdapter(adapter);
//            b.recvImgs.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//            adapter.notifyDataSetChanged();
        }
    }

}