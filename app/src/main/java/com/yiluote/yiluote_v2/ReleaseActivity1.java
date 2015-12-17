package com.yiluote.yiluote_v2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yiluote.fragment.ImageLoaderFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 类名称：ReleaseActivity1
 * 类描述：实现发布第一个页面，包括调用相机功能，本地相册预览功能
 *
 * @author lyp
 */

public class ReleaseActivity1 extends FragmentActivity implements OnClickListener {

    private Uri fileUri;
    private static final String LOG_TAG = "HelloCamera";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private ImageView releaseBack1;
    private TextView releaseNext1;
    private LinearLayout releaseTakePhoto;
    private RelativeLayout imageLoaderContent;
    private Intent intent;

    private ImageLoaderFragment imageLoaderFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release1);

        releaseTakePhoto = (LinearLayout) findViewById(R.id.releaseTakePhoto);
        releaseBack1 = (ImageView) findViewById(R.id.releaseBack1);
        releaseNext1 = (TextView) findViewById(R.id.releaseNext1);
        imageLoaderContent = (RelativeLayout) findViewById(R.id.imageLoaderContent);

        releaseTakePhoto.setOnClickListener(this);
        releaseBack1.setOnClickListener(this);
        releaseNext1.setOnClickListener(this);
        imageLoaderContent.setOnClickListener(this);

        imageLoaderFragment = new ImageLoaderFragment();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.imageLoaderContent, imageLoaderFragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.releaseTakePhoto:
                Toast.makeText(getApplicationContext(), "调用系统相机", Toast.LENGTH_SHORT).show();
                // 利用系统自带的相机拍照
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // create a file to save the image
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                // 此处这句intent的值设置关系到后面的onActivityResult中会进入那个分支，即关系到data是否为null，如果此处指定，则后来的data为null
                // set the image file name
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;

            case R.id.releaseBack1:
                intent = new Intent();
                intent.setClass(ReleaseActivity1.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;

            case R.id.releaseNext1:
                intent = new Intent();
                intent.setClass(ReleaseActivity1.this, ReleaseActivity2.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    //相机代码
    public static final int MEDIA_TYPE_IMAGE = 1;

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type)
    {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type)
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = null;
        try
        {
            // This location works best if you want the created images to be
            // shared between applications and persist after your app has been uninstalled.

            //mediaStorageDir = new File(new File(Environment.getExternalStorageDirectory(), "Yiluote"), "YiluotePhoto");
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                // 在SD卡上创建文件夹需要权限：<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        }
        else {
            return null;
        }
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // 如果是拍照
        if (CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE == requestCode)
        {
            Log.d(LOG_TAG, "CAPTURE_IMAGE");

            if (RESULT_OK == resultCode)
            {
                Log.d(LOG_TAG, "RESULT_OK");

                // Check if the result includes a thumbnail Bitmap
                if (data != null)
                {
                    // 没有指定特定存储路径的时候
                    Log.d(LOG_TAG,"data is NOT null, file on default position.");

                    // 指定了存储路径的时候（intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);）
                    // Image captured and saved to fileUri specified in the
                    // Intent
                    Toast.makeText(this, "Image saved to:\n" + data.getData(),
                            Toast.LENGTH_LONG).show();

//                    if (data.hasExtra("data"))
//                    {
//                        Bitmap thumbnail = data.getParcelableExtra("data");
//                        imageView.setImageBitmap(thumbnail);
//                    }
                }
                else
                {
                    // If there is no thumbnail image data, the image
                    // will have been stored in the target output URI.

                    // Resize the full image to fit in out image view.
//                    int width = imageView.getWidth();
//                    int height = imageView.getHeight();
//
//                    BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
//
//                   factoryOptions.inJustDecodeBounds = true;
//                    BitmapFactory.decodeFile(fileUri.getPath(), factoryOptions);
//
//                    int imageWidth = factoryOptions.outWidth;
//                    int imageHeight = factoryOptions.outHeight;

                    // Determine how much to scale down the image
//                    int scaleFactor = Math.min(imageWidth / width, imageHeight
//                            / height);

                    // Decode the image file into a Bitmap sized to fill the
                    // View
//                   factoryOptions.inJustDecodeBounds = false;
//                    factoryOptions.inSampleSize = scaleFactor;
//                    factoryOptions.inPurgeable = true;
//
//                   Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
//                           factoryOptions);
//
//                    imageView.setImageBitmap(bitmap);
                }
            }
            else if (resultCode == RESULT_CANCELED)
            {
                // User cancelled the image capture
            }
            else
            {
                // Image capture failed, advise user
            }
        }
    }
}
