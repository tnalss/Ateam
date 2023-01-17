package com.example.lastproject.ea;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.MainActivity;
import com.example.lastproject.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EaFileAdapter extends RecyclerView.Adapter<EaFileAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<EaFileVO> file_list;
    int cnt;
    Context context;
    Intent intent;
    DownloadManager mDownloadManager;
    Long mDownloadQueueId;
    String outputFilePath;

    public EaFileAdapter(LayoutInflater inflater, ArrayList<EaFileVO> file_list, int cnt, Context context) {
        this.inflater = inflater;
        this.file_list = file_list;
        this.cnt = cnt;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recv_ea_file,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        h.fileName.setText(file_list.get(i).getFile_name());
        final int temp = i;
        if(cnt == 1){
            h.fileName.setOnClickListener(v -> {
                outputFilePath = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS + "/[JK]" ) + file_list.get(i).getFile_name();
                URLDownloading(Uri.parse(file_list.get(i).getFile_path()));
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                            Toast.makeText(context, "Complete.", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent();
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent1.setAction(android.content.Intent.ACTION_VIEW);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            String localUrl = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/[JK COMPANY]" )
                                    + file_list.get(temp).getFile_name(); //저장했던 경로..
                            String extension = MimeTypeMap.getFileExtensionFromUrl(localUrl);
                            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

                            File file = new File(localUrl);
                            intent1.setDataAndType(Uri.fromFile(file), mimeType);
                            try {
                                context.startActivity(intent1);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(context, "Not found. Cannot open file.", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                };
            });
        }


    }

    @Override
    public int getItemCount() {
        return file_list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        public ViewHolder(@NonNull View v) {
            super(v);
            fileName = v.findViewById(R.id.tv_file_name);
        }
    }
    private void URLDownloading(Uri url) {
        if (mDownloadManager == null) {
            mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        }
        File outputFile = new File(outputFilePath);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        Uri downloadUri = url;
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        List<String> pathSegmentList = downloadUri.getPathSegments();
        request.setTitle("다운로드 항목");
        request.setDestinationUri(Uri.fromFile(outputFile));
        request.setAllowedOverMetered(true);

        mDownloadQueueId = mDownloadManager.enqueue(request);
    }



    };


