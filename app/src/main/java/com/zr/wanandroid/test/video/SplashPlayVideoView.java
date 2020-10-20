package com.zr.wanandroid.test.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zr.wanandroid.R;


public class SplashPlayVideoView extends TextureView {
    private MediaPlayer mediaPlayer;
    private int mVideoWidth;
    private int mVideoHeight;

    public static void test(Activity activity, ViewGroup viewGroup) {

        SplashPlayVideoView splashPlayVideoView = new SplashPlayVideoView(activity);
        splashPlayVideoView.start();
        viewGroup.addView(splashPlayVideoView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public SplashPlayVideoView(Context context) {
        super(context);
        init();
    }

    public SplashPlayVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SplashPlayVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SplashPlayVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        if (mVideoWidth > 0 && mVideoHeight > 0) {

            int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

            if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize;
                height = heightSpecSize;

                if (mVideoWidth * height < width * mVideoHeight) {
                    width = height * mVideoWidth / mVideoHeight;
                } else if (mVideoWidth * height > width * mVideoHeight) {
                    height = width * mVideoHeight / mVideoWidth;
                }
            } else if (widthSpecMode == MeasureSpec.EXACTLY) {
                width = widthSpecSize;
                height = width * mVideoHeight / mVideoWidth;
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize;
                }
            } else if (heightSpecMode == MeasureSpec.EXACTLY) {
                height = heightSpecSize;
                width = height * mVideoWidth / mVideoHeight;
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize;
                }
            } else {
                width = mVideoWidth;
                height = mVideoHeight;
                if (heightSpecMode == MeasureSpec.AT_MOST && height > heightSpecSize) {
                    height = heightSpecSize;
                    width = height * mVideoWidth / mVideoHeight;
                }
                if (widthSpecMode == MeasureSpec.AT_MOST && width > widthSpecSize) {
                    width = widthSpecSize;
                    height = width * mVideoHeight / mVideoWidth;
                }
            }
        } else {
        }
        setMeasuredDimension(width, height);
    }

    SurfaceTexture surfaceTexture;

    private void init() {
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                surfaceTexture = surface;
                try {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.splash_video);
                    mediaPlayer.setSurface(new Surface(surfaceTexture));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener =
                        new MediaPlayer.OnVideoSizeChangedListener() {
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                mVideoWidth = mp.getVideoWidth();
                                mVideoHeight = mp.getVideoHeight();
                                if (mVideoWidth != 0 && mVideoHeight != 0) {
                                    Surface face = new Surface(surfaceTexture);
                                    Log.i("=====", "=====OnVideoSizeChangedListener");
                                    mediaPlayer.setSurface(face);
                                    requestLayout();
                                }
                            }
                        };
                if (mediaPlayer != null) {
                    mediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
               /* mVideoWidth = width;
                mVideoHeight = height;
                if (mVideoWidth != 0 && mVideoHeight != 0) {
                    Surface face = new Surface(surfaceTexture);
                    Log.i("=====", "=====OnVideoSizeChangedListener");
                    mediaPlayer.setSurface(face);
                    requestLayout();
                }*/
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
      /*  try {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.splash_video);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


       /* SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(mediaPlayer!=null){
                    mediaPlayer.setDisplay(holder);
                }
            }
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {;
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });*/
    }

    public void start() {
        post(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
            }
        });
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(listener);
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return true;
                }
            });
        }
    }

    public void destroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
