package com.example.lastproject.notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.common.Common;
import com.example.lastproject.ea.FormListFragment;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

public class Insert_noActivity extends AppCompatActivity {
    ImageView img_sec_close, img_sec_file;
    EditText  edt_sec_title, edt_sec_content;
    TextView tv_sec_ok;
    ArrayList<String> path_list;
    ArrayList<String> name_list;
    ArrayList<NoticeFileVO> file_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_no);
        getSupportActionBar().hide();


        img_sec_close = findViewById(R.id.img_sec_close);
        img_sec_file = findViewById(R.id.img_sec_file);
        tv_sec_ok = findViewById(R.id.tv_sec_ok);
        edt_sec_title = findViewById(R.id.edt_sec_title);
        edt_sec_content = findViewById(R.id.edt_sec_content);




        tv_sec_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeVO vo = new NoticeVO();
                vo.setBoard_title(edt_sec_title.getText().toString());
                vo.setBoard_content(edt_sec_content.getText().toString());
                vo.setBoard_cate("O1");
                vo.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()) );

                //첨부파일 없는 게시글
                if(path_list == null) {
                new CommonMethod().setParams("vo", new Gson().toJson( vo )).sendPost("insert.no", (isResult, data) -> {
                    if (isResult) {
                        Toast.makeText(Insert_noActivity.this, "글 등록 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                    Log.d("로그", "실패: ");

                    }

                });

                } else {
                    //첨부파일 있는 게시글

                }

                


            }
        });

        // 취소
        img_sec_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    //갤러리 선택시 실행 메소드
    public void galleryMethod(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //2023-01-11 사진 선택을 여러개 할수있게
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "사진 선택"), Common.GALLERY_CODE);
    }

    //파일 선택시 실행 메소드
    public void fileMethod(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
//        intent.putExtra(Intent.)

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "파일 선택"), Common.FILE_CODE);
    }

    //내보낼 파일 정보 담기
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void allMethod(Intent data, int type){
//        Log.d(TAG, "data 확인 : " + data.getClipData().getItemAt(0).getUri());

        name_list = new ArrayList<>();
        path_list = new ArrayList<>();  //==> String (path)
        file_list = new ArrayList<>();   // ==> BoardFileVO
        String realPath = null;
        if( data.getClipData() == null ){
            //한개 선택시 바로 mData 로 접근
            NoticeFileVO vo = new NoticeFileVO();
            if(type==Common.FILE_CODE){
                //파일처리
                realPath = getFilePath(data.getData());
                String name = getFileNameToUri(data.getData());
                name_list.add( name );
                vo.setFile_name( name );
            }
            path_list.add( realPath );
            vo.setPath( realPath );
            file_list.add(vo);
        }else{
            //여러개 선택시 clipData 있음
            for (int i = 0; i < data.getClipData().getItemCount(); i++){
                NoticeFileVO vo = new NoticeFileVO();
                if(type==Common.GALLERY_CODE){
                    //사진처리
                    String name = getImageNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                    realPath = CommonMethod.getRealPath(data.getClipData().getItemAt(i).getUri(), this, type);
                }else if(type==Common.FILE_CODE){
                    //파일처리
                    realPath = getFilePath(data.getClipData().getItemAt(i).getUri());
                    String name = getFileNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                }
                path_list.add( realPath );
                vo.setPath( realPath );

                file_list.add(vo);
            }
        }
    }

    //파일 경로 찾기
    public String getFilePath(Uri uri){
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type= split[0];
        String path = null;
        if ("primary".equalsIgnoreCase(type)) {
            path = Environment.getExternalStorageDirectory() + "/" + (split.length > 1 ? split[1] : ""); //split[1];
        } else if ("home".equalsIgnoreCase(type)) {
            path = Environment.getExternalStorageDirectory() + "/Documents/" + (split.length > 1 ? split[1] : ""); //split[1];
        }
        return path;
    }

    // 선택된 파일 이름 가져오기
    public String getFileNameToUri(Uri data){
        Cursor returnCursor = getContentResolver().query(data, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameIndex);
        return fileName;
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }

}