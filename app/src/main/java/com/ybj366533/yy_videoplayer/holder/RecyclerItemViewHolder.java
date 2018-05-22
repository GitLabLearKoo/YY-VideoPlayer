package com.ybj366533.yy_videoplayer.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ybj366533.videoplayer.utils.VideoHelper;
import com.ybj366533.yy_videoplayer.R;
import com.ybj366533.yy_videoplayer.model.VideoModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class RecyclerItemViewHolder extends RecyclerItemBaseHolder {

    public final static String TAG = "RecyclerView2List";

    protected Context context = null;

    @BindView(R.id.list_item_container)
    FrameLayout listItemContainer;

    @BindView(R.id.list_item_btn)
    ImageView listItemBtn;

    ImageView imageView;

    private VideoHelper smallVideoHelper;

    private VideoHelper.VideoHelperBuilder gsySmallVideoHelperBuilder;

    public RecyclerItemViewHolder(Context context, View v) {
        super(v);
        this.context = context;
        ButterKnife.bind(this, v);
        imageView = new ImageView(context);
    }

    public void onBind(final int position, VideoModel videoModel) {

        //增加封面
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);

        smallVideoHelper.addVideoPlayer(position, imageView, TAG, listItemContainer, listItemBtn);

        listItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecyclerBaseAdapter().notifyDataSetChanged();
                //listVideoUtil.setLoop(true);
                smallVideoHelper.setPlayPositionAndTag(position, TAG);
                String url;
                if (position % 2 == 0) {
                    url = "https://res.exexm.com/cw_145225549855002";
                } else {
                    url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
                }
                //listVideoUtil.setCachePath(new File(FileUtils.getPath()));

                gsySmallVideoHelperBuilder.setVideoTitle("title " + position).setUrl(url);

                smallVideoHelper.startPlay();

                //必须在startPlay之后设置才能生效
                //listVideoUtil.getGsyVideoPlayer().getTitleTextView().setVisibility(View.VISIBLE);
            }
        });
    }


    public void setVideoHelper(VideoHelper smallVideoHelper, VideoHelper.VideoHelperBuilder gsySmallVideoHelperBuilder) {
        this.smallVideoHelper = smallVideoHelper;
        this.gsySmallVideoHelperBuilder = gsySmallVideoHelperBuilder;
    }
}





