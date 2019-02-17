package com.example.lianxi_zhoukao1_216.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi_zhoukao1_216.R;
import com.example.lianxi_zhoukao1_216.entity.XiangqingEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class XiangqingAdapter extends RecyclerView.Adapter<XiangqingAdapter.ViewHolder> {

    private SimpleDraweeView img;

    public interface OnXiangqingClickListener {
        void onXiangqingClick(String pic);
    }

    private OnXiangqingClickListener xiangqingClickListener;

    public void setXiangqingClickListener(OnXiangqingClickListener listener) {
        this.xiangqingClickListener = listener;
    }

    private Context context;
    private XiangqingEntity.ResultBean list;

    public XiangqingAdapter(Context context, XiangqingEntity.ResultBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_xiangqing, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final String picture = list.getPicture();
        final String[] split = picture.split("\\,");
        final ArrayList<String> bannerlist=new ArrayList<>();
        bannerlist.add(split[0]);
        bannerlist.add(split[1]);
        bannerlist.add(split[2]);
        bannerlist.add(split[3]);
        bannerlist.add(split[4]);
        holder.vpImg.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return bannerlist.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                img = new SimpleDraweeView(context);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.setImageURI(bannerlist.get(position));
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });


        holder.categoryName.setText(list.getCategoryName());
        holder.commentNum.setText("已售"+list.getCommentNum()+"件");
        holder.commodityName.setText(""+list.getCommodityName());
        holder.price.setText("￥:"+list.getPrice());
        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String picture = list.getPicture();
                xiangqingClickListener.onXiangqingClick(picture);
            }
        });

    }



    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ViewPager  vpImg;
        private TextView categoryName;
        private TextView commentNum;
        private TextView commodityName;
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            vpImg = itemView.findViewById(R.id.vp_img);
            categoryName=itemView.findViewById(R.id.categoryName);
            commentNum=itemView.findViewById(R.id.commentNum);
            commodityName=itemView.findViewById(R.id.commodityName);
            price=itemView.findViewById(R.id.price);
        }
    }
}
