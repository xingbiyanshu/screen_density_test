package com.kedacom.screendensitytest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.img);
        imageView2 = (ImageView) findViewById(R.id.img2);
        textView = (TextView) findViewById(R.id.tv);
        imageView.setImageResource(R.drawable.ic_launcher);

        imageView2.setImageResource(R.drawable.ic_launcher2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String screenInfo = metrics.toString();

        Size showSize = getDrawableShowSize(R.drawable.ic_launcher);
        Size realSize = getDrawableRealSize(R.drawable.ic_launcher);
        Size showSize2 = getDrawableShowSize(R.drawable.ic_launcher2);
        Size realSize2 = getDrawableRealSize(R.drawable.ic_launcher2);

        String imgInfo1 = "imgA:{"+"realSize:"+realSize.w+"*"+realSize.h+", showSize:"+showSize.w+"*"+showSize.h+"}\n";
        String imgInfo2 = "imgB:{"+"realSize:"+realSize2.w+"*"+realSize2.h+", showSize:"+showSize2.w+"*"+showSize2.h+"}\n";

        String sumInfo = imgInfo1+imgInfo2+screenInfo;
        textView.setText(sumInfo);

        System.out.println(sumInfo);
    }



    class Size{
        int w;
        int h;
        Size(int w, int h){
            this.w=w; this.h=h;
        }
    }

    private Size getDrawableRealSize(int resId){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);

        return new Size(options.outWidth, options.outHeight);
    }

    private Size getDrawableShowSize(int resId){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        return new Size(bitmap.getWidth(), bitmap.getHeight());
    }
}
