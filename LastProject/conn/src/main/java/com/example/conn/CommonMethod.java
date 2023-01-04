package com.example.conn;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonMethod {

    HashMap<String, Object> params = new HashMap<>();

    // 갤러리에서 가져온 이미지 경로가 uri 형태로 실제 물리적인 주소가 X -> File로 만들 수 없음
    //해당하는 메소드는 URI를 통해 실제 이미지 물리적 주소를 얻어오는 메소드
    //2022.12.26 kym만듦
    //2022.12.29 csm 오류발견 : uri => aaa로 수정
    public String getRealPath(Uri uri, Context context){//select 쿼리를 날렸다고 생각.
        String rtn = null; // 리턴용
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri,proj,null,null,null);
        if (cursor.moveToFirst()){//회사마다 다른 코드로 사용할 수 있음.
            int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            rtn = cursor.getString(column_index);
        }
        cursor.close();

        return rtn;
    }

    //카메라로 찍은 사진을 우리가 만든 임시파일로 가져오기 위한 처리
    public File createFile(Context context){
        String fileName = "LastProject" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //사진파일을 저장하기 위한 경로(기억할필요X 계속바뀌는 코드)
        //버전이 업되면 계속 바뀜
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File rtnFile=null; // IO입출력 Exception이 발생하기 때문에 try catch 블럭이 생김


        try {
            rtnFile= File.createTempFile(fileName,".jpg",storageDir);
        } catch (IOException e) {
        }


        return rtnFile;
    }


    public CommonMethod setParams(String key, Object value){
        params.put(key,value);
        return this;
    }

    public void sendPost(String url, CallbackResult callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest = apiInterface.connPost(url,params);

        apiTest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.result(true, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.result(false, "");
                t.printStackTrace();//어떤 오류인지 로그에 찍히게 처리
            }
        });
    }

    public void sendGet(String url, CallbackResult callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest = apiInterface.connGet(url,params);

        apiTest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.result(true, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.result(false, "");
                t.printStackTrace();//어떤 오류인지 로그에 찍히게 처리
            }
        });
    }

    //1. 정의
    public interface CallbackResult{
        // 메인에서 CommonMethod를 통해서 Callback<String> 인터페이스를 넘겨서 실행할때마다
        // 두개의 메소드가 오버라이드 됨.( onResponse, onFailure ) ==> 하나로 합치고 싶음.
        public void result( boolean isResult, String data );

    }

    public RequestBody stringToRequest(){
        RequestBody data = null;
        if( !params.isEmpty() ){
            data = RequestBody.create(MediaType.parse("mutipart/form-data"),new Gson().toJson(params.get("param"))      );
        }
        return data;
    }

    public MultipartBody.Part pathToPartFile(String path){
        if(path!=null){
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(path));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file","img.png",fileBody);
            //나중에는 파일명을 바꿔줘야함 일단은 고정으로
            return  filePart;
        }
        return null;
    }

//인터페이스에서 만든 멀티파트 사용할 메소드
    public void sendPostFile(String url, String filePath,CallbackResult callback){
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<String> apiTest = apiInterface.connFilePost(url,stringToRequest(),pathToPartFile(filePath));


        apiTest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.result(true, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.result(false, "");
                t.printStackTrace();//어떤 오류인지 로그에 찍히게 처리
            }
        });
    }

//setLayoutMananger 사용할때 안에 값

    public static RecyclerView.LayoutManager getVManager(Context con){  //Context를 화면에 보이는 객체로부터 받아온다.
        LinearLayoutManager manager = new LinearLayoutManager(con, RecyclerView.VERTICAL,false);
        return manager;
    }
    public static RecyclerView.LayoutManager getHManager(Context con){  //Context를 화면에 보이는 객체로부터 받아온다.
        LinearLayoutManager manager = new LinearLayoutManager(con, RecyclerView.HORIZONTAL,false);
        return manager;
    }

}
