package com.zr.wanandroid.test;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.AlignmentSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.DrawableMarginSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TabStopSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.test.span.CenteredImageSpan;
import com.zr.wanandroid.test.span.NoUnderlineClickSpan;
import com.zr.wanandroid.test.span.RoundBgColorSpan;
import com.zr.wanandroid.test.toolkit.TextSpanBuilder;
import com.zr.wanandroid.utils.ToastUtils;

import static android.graphics.Typeface.BOLD;

public class TestSpanActivity extends BaseActivity {
    TextView tvTestSpan;
    @Override
    public int getContentView() {
        return R.layout.test_span_act;
    }

    @Override
    public void initView() {
        tvTestSpan = findViewById(R.id.tvTestSpan);


        SpannableString spannable = new SpannableString("Text is spantastic!");
       /* spannable.setSpan(
                new ForegroundColorSpan(Color.RED),
                8, 12,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );*/
      /*  spannable.setSpan(
                new StyleSpan(BOLD),
                8, 12,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );*/
        SpannableString string = new SpannableString("Text with quote span on a long line");


          string = new SpannableString("Paragraph text beginnin\tg with tab.");
        string.setSpan(new TabStopSpan.Standard(122),18, string.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

          string = new SpannableString("Text with a url span");
        URLSpan urlSpan = new URLSpan("http://www.baidu.com");
        string.setSpan(urlSpan, 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTestSpan.setMovementMethod(LinkMovementMethod.getInstance());




          string = new SpannableString("Text with opposite alignment");
        string.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 2,
                string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        CharSequence label = TextSpanBuilder.create("缩进")
                .leadingMargin(111, 50)
                .append("")
                .span(new RoundBgColorSpan(0xFFDD424D, 0xFFFFFFFF, 20))
                .append("")
                .span(new CenteredImageSpan(this, R.mipmap.android))
                .append("点击")
                .foregroundColor(Color.RED)
                .span(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getApplicationContext(), "点击回调", Toast.LENGTH_SHORT).show();
                        ((TextView)widget).setHighlightColor(getResources().getColor(android.R.color.darker_gray));
                    }
                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.RED);
                    }
                })
                .append("背景色")
                .backgroundColor(Color.BLUE)
                .append("前景色")
                .leadingMargin(10,20)
                .foregroundColor(0xFFFFFF00)
                .append("粗体")
                .bold()
                .italic()
                .append("斜体")
                .italic()
                .append("粗斜体")
                .boldItalic()
                .append("删除线")
                .strikeThrough()
                .append("下标")
                .subscript()
                .append("上标")
                .superscript()
                .sizeInPx(10)
                .append("下划线")
                .underline()
                .foregroundColor(Color.BLUE)
                .strikeThrough()
                .append("文字缩放")
                .xProportion(2F)
                .append("文字大小")
                .sizeInPx(18)
                .align(Layout.Alignment.ALIGN_OPPOSITE)
                .append("URL")
                .url("http://www.baidu.com")
                .foregroundColor(Color.BLACK)
//                .append("文\n字标记")
//                .bullet( 30, Color.RED)
                .build();

        SpannableStringBuilder   string2 = new SpannableStringBuilder("Text");
        string2.append("相对大小").setSpan(new RelativeSizeSpan(1),4,8,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        MaskFilter blurMask = new BlurMaskFilter(15f, BlurMaskFilter.Blur.INNER);
          string = new SpannableString("Text with blur mask");
        string.setSpan(new MaskFilterSpan(blurMask), 10, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


          string = new SpannableString("Text with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long lineText with quote span on a long line");
        string.setSpan(new QuoteSpan(Color.GREEN,22,22), 0, string.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        SpannableStringBuilder build = SpanBuild.get("例子").append("粗体").setTextIsBold()
                .append("斜体\n").setTextIsItalic()
                .append("URL链接\n").setUrl("http://www.baidu.com").setUnderLine()
                .append("粗斜体\n").setTextIsBoldItalic()
                .append("字体大小25px\n").setTextSize(25)
                .append("URL链接(黑色)\n").setUnderLine().setUrl("http://www.baidu.com").setTextColor(Color.BLACK)
                .append("字体大小15dp\n").setTextSize(15, true)
                .append("URL链接(红色)无下划线\n").setUrl("http://www.baidu.com").setTextColor(Color.RED).setUnderLine(false)
                .append("字体颜色蓝色\n").setTextColor(Color.BLUE)
                .append("背景黄色\n").setBgColor(Color.YELLOW)
                .append("下划线").setUnderLine()
                .setNewlinePoint(2,Color.GRAY,10)
                .setSpan(new BulletSpan())
                .setSpan(new BulletSpan())
                .setSpan(new BulletSpan())
                .append("相对前面字体大小的0.5f\n").setRelativeTextSizeScale(0.5f)
                .append("中间删除线\n").setCenterLine()
                .append("相对前面字体大小的1.5f\n").setRelativeTextSizeScale(1.5f)
                .append("中间线+下划线\n").setCenterLine().setUnderLine()
                .append("文字偏上显示\n").setTextShowTop()
                .setQuoteColor(Color.RED)
                .setQuoteGapWidth(10)
                .setQuoteStripeWidth(20)
                .setLineMarginOther(60)
                .setLineMarginCurrent(30)
                .append("设置点击\n").setTextIsBold().setTextColor(Color.BLUE).setUnderLine(false).setClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(mActivity,"点击生效");
                    }
                }).setTextColor(Color.RED).setUnderLine(true).setTextIsItalic()
                .append("文字偏下显示\n").setTextShowBottom()
                .append("BlurOuter\n").setBlurOuter()
                .append("BlurInner\n").setBlurInner()
                .append("BlurSolid\n").setBlurSolid()
                .append("X伸缩1.5f\n").setScaleXSize(1.5f)
                .append("X伸缩0.5f\n").setScaleXSize(0.5f)
                .append("serif字体").setTextFamily("serif")
                .append("原始大小图片")
                .appendImage(new SpanBuild.MyImageSpan(mActivity,R.mipmap.android))
                .append("\n50px宽度的图片")
                .appendImage(new SpanBuild.MyImageSpan(mActivity,R.mipmap.android).setWidth(50))
                .append("\n250px宽度的图片且设置成红色")
                .setTextAlignRight()
                .appendImage(new SpanBuild.MyImageSpan(mActivity,R.mipmap.android).setHeight(250).setColor(Color.RED))
                .build(tvTestSpan);
        tvTestSpan.setText(build);


    }

    @Override
    public void initData() {
        SpannableStringBuilder builder=new SpannableStringBuilder();
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
