package com.example.lastproject.notice;

import android.content.Intent;
import android.os.Bundle;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conn.ApiClient;
import com.example.conn.CommonMethod;
import com.example.lastproject.MainActivity;
import com.example.lastproject.R;
import com.example.lastproject.ea.EaCodeVO;
import com.example.lastproject.ea.FormListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NoticeFragment extends Fragment {
    String TAG = "로그";
    RecyclerView recv_notice, recv_secret;
    ArrayList<NoticeVO> notice;
    ArrayList<NoticeVO> secret;
    ArrayList<ReplyVO> reply;
    TextView tv_no_writing, tv_sec_writing, tv_no_title, tv_no_search, tv_no_result;
    EditText edt_no_search;
    ImageView img_no_search;


    @Override
    public void onResume() {
        super.onResume();
        notice(getLayoutInflater());
        secret(getLayoutInflater());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notice, container, false);

        recv_notice = v.findViewById(R.id.recv_notice);
        recv_secret = v.findViewById(R.id.recv_secret);
        tv_no_writing = v.findViewById(R.id.tv_no_writing);
        tv_sec_writing = v.findViewById(R.id.tv_sec_writing);
        edt_no_search = v.findViewById(R.id.edt_no_search);
        img_no_search = v.findViewById(R.id.img_no_search);
        tv_no_search = v.findViewById(R.id.tv_no_search);
        tv_no_title = v.findViewById(R.id.tv_no_title);


        /* 게시판 */
        notice(inflater);
        secret(inflater);


        // 익명게시판 검색
        img_no_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_no_title.setVisibility(View.GONE);
                tv_no_writing.setVisibility(View.GONE);
                img_no_search.setVisibility(View.GONE);
                edt_no_search.setVisibility(View.VISIBLE);
                tv_no_search.setVisibility(View.VISIBLE);
            }
        });
        // 익명게시판 검색
        edt_no_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new CommonMethod().setParams("search",edt_no_search.getText().toString().trim()).sendPost("search.no", (isResult, data) -> {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    secret = gson.fromJson(data, new TypeToken<ArrayList<NoticeVO>>(){}.getType());
                    recv_secret.setAdapter(new SecretAdapter(getLayoutInflater(), getContext(), secret));
                    recv_secret.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                });
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // 익명게시판 글쓰기
        tv_no_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Insert_noActivity.class);
                startActivity(intent);
            }
        });

        // 공지사항 글쓰기
        tv_sec_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Insert_secActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    // 공지사항
    public void notice(LayoutInflater inflater) {
        new CommonMethod().sendPost("notice.no", (isResult, data) -> {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            notice = gson.fromJson(data,
                    new TypeToken<ArrayList<NoticeVO>>() {
                    }.getType());
            recv_notice.setAdapter(new NoticeAdapter(inflater, getContext(), notice));
            recv_notice.setLayoutManager(CommonMethod.getVManager(getContext()));
        });
    }

    // 익명게시판
    public void secret(LayoutInflater inflater) {
        // 익명게시판
        new CommonMethod().sendPost("secret.no", (isResult, data) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            secret = gson.fromJson(data,
                    new TypeToken<ArrayList<NoticeVO>>() {
                    }.getType());
            recv_secret.setAdapter(new SecretAdapter(inflater, getContext(), secret));
            recv_secret.setLayoutManager(CommonMethod.getVManager(getContext()));
        });
    }

}