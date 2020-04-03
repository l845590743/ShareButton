package com.zhimin.share;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ShareImageView shareImageView;
    private boolean isRecycleViewScroll;
    private boolean isTop;
    private boolean isBottom;
    private boolean isAnimatorEnd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        shareImageView = view.findViewById(R.id.share_image);
        shareImageView.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new HomeAdapter());
        recyclerView.addOnScrollListener(new MyScrollListener());
        return view;
    }

    private class MyScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                case RecyclerView.SCROLL_STATE_IDLE:
                    isRecycleViewScroll = false;
                    showShareImage();
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    if (!isRecycleViewScroll && !isBottom && !isTop) {
                        isRecycleViewScroll = true;
                        hideShareImage();
                    }
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (!isRecycleViewScroll && !isBottom  && !isTop) {
                        isRecycleViewScroll = true;
                        hideShareImage();
                    }
                    break;
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            boolean bottom = recyclerView.canScrollVertically(1);
            boolean top = recyclerView.canScrollVertically(-1);
            if (!bottom) {
                isBottom = true;
            } else {
                isBottom = false;
            }
            if (!top) {
                isTop = true;
            } else {
                isTop = false;
            }
        }
    }

    private void showShareImage() {
        float translationX = shareImageView.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(shareImageView, "translationX", 0);
        animator.setDuration(600);
        if (!isAnimatorEnd) {
            animator.setStartDelay(1200);
        }
        animator.start();
    }

    private void hideShareImage() {
        isAnimatorEnd = false;
        float translationX = shareImageView.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(shareImageView, "translationX", shareImageView.dp2px(getActivity(),70));
        animator.setDuration(600);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimatorEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.share_image) {
            Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
        }
    }
}
