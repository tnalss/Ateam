package com.example.lastproject.notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.ArrayList;

public class Insert_noActivity extends AppCompatActivity {
    ImageView img_sec_close, img_sec_file;
    EditText  edt_sec_title, edt_sec_content;
    TextView tv_sec_ok;
    RecyclerView recv_no_file;
    ArrayList<String> path_list;
    ArrayList<String> name_list;
    ArrayList<NoticeFileVO> file_list;
    NoticeFileAdapter file_adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_no);
        getSupportActionBar().hide();

        recv_no_file = findViewById(R.id.recv_no_file);
        img_sec_close = findViewById(R.id.img_sec_close);
        img_sec_file = findViewById(R.id.img_sec_file);
        tv_sec_ok = findViewById(R.id.tv_sec_ok);
        edt_sec_title = findViewById(R.id.edt_sec_title);
        edt_sec_content = findViewById(R.id.edt_sec_content);

        img_sec_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryMethod();
            }
        });


        new Common().checkDangerousPermissions(this);
        tv_sec_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeVO vo = new NoticeVO();
                vo.setBoard_title(edt_sec_title.getText().toString());
                vo.setBoard_content(edt_sec_content.getText().toString());
                vo.setBoard_cate("O1");
                vo.setEmp_no(Integer.parseInt(Common.loginInfo.getEmp_no()) );

                //???????????? ?????? ?????????
                if(path_list == null) {
                new CommonMethod().setParams("vo", new Gson().toJson( vo )).sendPost("insert.no", (isResult, data) -> {
                    if (isResult) {
                        Toast.makeText(Insert_noActivity.this, "??? ?????? ??????", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                    Log.d("??????", "??????1: ");

                    }

                });

                } else {
                    //???????????? ?????? ?????????
                    new CommonMethod().setParams("param", vo).sendPostFiles("secinsert.no", path_list, name_list, Common.GALLERY_CODE, (isResult, data) -> {
                        if(isResult){
                            Toast.makeText(Insert_noActivity.this, "??? ?????? ??????", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Log.d("??????", "??????2: " + data);
                        }
                    });
                }


            }
        });

        // ??????
        img_sec_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    //????????? ????????? ?????? ?????????
    public void galleryMethod(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //2023-01-11 ?????? ????????? ????????? ????????????
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "?????? ??????"), Common.GALLERY_CODE);
    }

    //?????? ????????? ?????? ?????????
    public void fileMethod(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
//        intent.putExtra(Intent.)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "?????? ??????"), Common.FILE_CODE);
    }

    //?????? ???????????? startActivityForResult ??? ???????????? ??? ????????? ????????? ???
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Common.GALLERY_CODE && resultCode == RESULT_OK){

            allMethod(data, Common.GALLERY_CODE);

            //?????????
            file_adapter = new NoticeFileAdapter(getLayoutInflater(), file_list,this);
            recv_no_file.setAdapter(file_adapter);
            recv_no_file.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            file_adapter.notifyDataSetChanged();

        }else {


        }
    }

    //????????? ?????? ?????? ??????
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void allMethod(Intent data, int type){
        name_list = new ArrayList<>();
        path_list = new ArrayList<>();  //==> String (path)
        file_list = new ArrayList<>();   // ==> BoardFileVO
        String realPath = null;
        if( data.getClipData() == null ){
            //?????? ????????? ?????? mData ??? ??????
            NoticeFileVO vo = new NoticeFileVO();
            if(type==Common.FILE_CODE){
                //????????????
                realPath = getFilePath(data.getData());
                String name = getFileNameToUri(data.getData());
                name_list.add( name );
                vo.setFile_name( name );
            }
            path_list.add( realPath );
            vo.setFile_path( realPath );
            file_list.add(vo);
        }else{
            //????????? ????????? clipData ??????
            for (int i = 0; i < data.getClipData().getItemCount(); i++){
                NoticeFileVO vo = new NoticeFileVO();
                if(type==Common.GALLERY_CODE){
                    //????????????
                    String name = getImageNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                    realPath = CommonMethod.getRealPath(data.getClipData().getItemAt(i).getUri(), this, type);
                }else if(type==Common.FILE_CODE){
                    //????????????
                    realPath = getFilePath(data.getClipData().getItemAt(i).getUri());
                    String name = getFileNameToUri(data.getClipData().getItemAt(i).getUri());
                    name_list.add( name );
                    vo.setFile_name( name );
                }
                path_list.add( realPath );
                vo.setFile_path( realPath );

                file_list.add(vo);
            }
        }
    }

    //?????? ?????? ??????
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

    // ????????? ?????? ?????? ????????????
    public String getFileNameToUri(Uri data){
        Cursor returnCursor = getContentResolver().query(data, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameIndex);
        return fileName;
    }

    // ????????? ????????? ????????? ????????????
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