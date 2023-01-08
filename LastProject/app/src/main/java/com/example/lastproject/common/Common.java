package com.example.lastproject.common;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lastproject.login.LoginVO;

public class Common {
    public static LoginVO loginInfo;
    public static final int SEARCH_ADDRESS_ACTIVITY = 1003;
    public static final int CAMERA_CODE = 1000;
    public static final int GALLERY_CODE = 1001;
    //권한체크
    public void checkDangerousPermissions(Activity activity) {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(activity, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(activity, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {
                Toast.makeText(activity, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(activity, permissions, 1);
            }
        }
    }


}
