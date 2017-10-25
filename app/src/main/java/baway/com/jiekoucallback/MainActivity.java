package baway.com.jiekoucallback;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private int[] strimage = new int[]{R.drawable.hh, R.drawable.jj,
            R.drawable.sn, R.drawable.xn, R.drawable.zz};
    private LinearLayout ll;
    private ArrayList<ImageView> listImage;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    int currentItem = vp.getCurrentItem();
                    currentItem++;
                    vp.setCurrentItem(currentItem);
                    handler.sendEmptyMessageDelayed(0, 1000);
                    break;

                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);

        info();
        vp.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(MainActivity.this);

                iv.setImageResource(strimage[position % strimage.length]);

                container.addView(iv);

                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView((View) object);
            }
        });
        vp.setCurrentItem(strimage.length * 5000);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                for (int i = 0; i < listImage.size(); i++) {
                    if (i == arg0 % strimage.length) {
                        listImage.get(i).setImageResource(R.drawable.mallcricle);
                    } else {
                        listImage.get(i).setImageResource(R.drawable.smallcircle);
                    }

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private void info() {
        listImage = new ArrayList<ImageView>();
        listImage.clear();
        for (int i = 0; i < strimage.length; i++) {
            ImageView iv = new ImageView(MainActivity.this);

            if (i == 0) {
                iv.setImageResource(R.drawable.mallcricle);
            } else {
                iv.setImageResource(R.drawable.smallcircle);
            }
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(10, 10);
            param.setMargins(5, 0, 5, 5);
            listImage.add(iv);
            ll.addView(iv, param);

        }
    }

}
