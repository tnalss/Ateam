package com.example.lastproject.employee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;

import com.example.lastproject.common.Common;
import com.example.lastproject.databinding.FragmentManageEmpBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ManageEmpFragment extends Fragment {

    FragmentManageEmpBinding binding;
    ArrayList<EmployeeVO> list;
    MainActivity activity;

    void noResult(){
        if(list.size()==0){
            binding.tvNoResult.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoResult.setVisibility(View.GONE);
        }
    }
    void search(){
        if(binding.edtFind.length()>0){
            //쿼리날려서 리스트잡고 리스트바꿔서보내주자.
            new CommonMethod().setParams("keyword",binding.edtFind.getText().toString().trim()).sendPost("keyword.emp",(isResult, data) -> {
                if(isResult){
                    list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                    binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list,activity));
                    binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    noResult();
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new CommonMethod().sendPost("list.emp",(isResult, data) -> {
            if(isResult){
                list = new Gson().fromJson(data,new TypeToken<ArrayList<EmployeeVO>>(){}.getType());

                binding.recvEmpList.setAdapter(new EmpListAdapter(getLayoutInflater(),list,activity));
                binding.recvEmpList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                noResult();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageEmpBinding.inflate(inflater,container,false);
        activity = (MainActivity) getActivity();
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        //사원검색기능을 위한 디자인
        binding.ivPersonSearch.setOnClickListener(v->{
            if(binding.tvMenuTitle.getVisibility()==View.VISIBLE) {
                binding.tvMenuTitle.setVisibility(View.GONE);
                binding.edtFind.setVisibility(View.VISIBLE);
                binding.ivPersonSearch.setVisibility(View.GONE);
                binding.searchButton.setVisibility(View.VISIBLE);
            }
        });

        binding.searchButton.setOnClickListener(v -> {

            binding.tvMenuTitle.setVisibility(View.VISIBLE);
            binding.edtFind.setVisibility(View.GONE);
            binding.ivPersonSearch.setVisibility(View.VISIBLE);
            binding.searchButton.setVisibility(View.GONE);
            search();

        });

        //신규사원추가버튼
        binding.btnNewEmp.setOnClickListener(v -> {
            activity.changeFragment(new EmpInsertFragment());
        });

        //edittext 값변하면 실시간 검색기능
        binding.edtFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.ivExcel.setOnClickListener(v -> {

            new Common().checkDangerousPermissions(activity);
            saveExcel(list);


        });


        return binding.getRoot();
    }

    private void saveExcel(ArrayList<EmployeeVO> list){
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet();

        Row row = sheet.createRow(0);
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue("사번");
        cell = row.createCell(1);
        cell.setCellValue("성명");
        cell = row.createCell(2);
        cell.setCellValue("성별");
        cell = row.createCell(3);
        cell.setCellValue("전화번호");
        cell = row.createCell(4);
        cell.setCellValue("이메일");
        cell = row.createCell(5);
        cell.setCellValue("생일");
        cell = row.createCell(6);
        cell.setCellValue("주소");
        cell = row.createCell(7);
        cell.setCellValue("입사일");
        cell = row.createCell(8);
        cell.setCellValue("지점");
        cell = row.createCell(9);
        cell.setCellValue("부서");
        cell = row.createCell(10);
        cell.setCellValue("직위");

        for(int i = 0; i < list.size() ; i++){ // 데이터 엑셀에 입력
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(list.get(i).getEmp_no());
            cell = row.createCell(1);
            cell.setCellValue(list.get(i).getEmp_name());
            cell = row.createCell(2);
            cell.setCellValue(list.get(i).getGender());
            cell = row.createCell(3);
            cell.setCellValue(list.get(i).getPhone());
            cell = row.createCell(4);
            cell.setCellValue(list.get(i).getEmail());
            cell = row.createCell(5);
            cell.setCellValue(list.get(i).getBirth());
            cell = row.createCell(6);
            cell.setCellValue(list.get(i).getAddress());
            cell = row.createCell(7);
            cell.setCellValue(list.get(i).getHire_date());
            cell = row.createCell(8);
            cell.setCellValue(list.get(i).getBranch_name());
            cell = row.createCell(9);
            cell.setCellValue(list.get(i).getDepartment_name());
            cell = row.createCell(10);
            cell.setCellValue(list.get(i).getRank_name());

        }

        File xlsFile = new File(activity.getExternalFilesDir(null),"list.xls");
        try{
            FileOutputStream os = new FileOutputStream(xlsFile);
            workbook.write(os); // 외부 저장소에 엑셀 파일 생성
        }catch (IOException e){
            e.printStackTrace();
        }
        //Log.d("TAG", "saveExcel: "+xlsFile.getAbsolutePath()+"에 저장되었습니다");
        //Toast.makeText(getContext(),xlsFile.getAbsolutePath()+"에 저장되었습니다",Toast.LENGTH_SHORT).show();
        Uri path = FileProvider.getUriForFile(getContext(),activity.getPackageName()+".fileprovider",xlsFile) ;
               // Uri.fromFile(xlsFile);

        Log.d("TAG", "saveExcel: "+ path);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/excel");
        shareIntent.putExtra(Intent.EXTRA_STREAM,path);
        startActivity(Intent.createChooser(shareIntent,"엑셀 내보내기"));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}