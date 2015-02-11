package iijiaban.foodtherapy.textview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MTextView extends TextView {
	private String detail;
	private float textShowWidth;
	private float textSize;
	private float paddingLeft;
	private float paddingRight;

	private int textColor;
    private Paint paint = new Paint();
    private final String namespace = "http://schemas.text.com";
	@SuppressWarnings("deprecation")
	public MTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		detail = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
		textSize = attrs.getAttributeIntValue(namespace, "textSize", 25);
		textColor = attrs.getAttributeIntValue(namespace, "textColor", Color.BLACK);
		
		
		paddingLeft = attrs.getAttributeIntValue(namespace, "paddingLeft", 0);
		paddingRight = attrs.getAttributeIntValue(namespace, "paddingRight", 0);
		
		
		paint.setTextSize(textSize);
		paint.setColor(textColor);
		paint.setAntiAlias(true);
		textShowWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth()
				-paddingLeft-paddingRight;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int  cLine = 0;
		detail = this.getText().toString();
		if(detail == null){
			return;
		}
		char[] detailCharArray = detail.toCharArray();
		float drawedWidth = 0;
		float charWidth;
		for(int i = 0; i < detailCharArray.length; i++ ){
			charWidth = paint.measureText(detailCharArray, i, 1);
			if(detailCharArray[i] == '\n'){
				cLine ++;
				drawedWidth = 0;
				continue;
			}
			if(textShowWidth - drawedWidth < charWidth){
				cLine ++;
				drawedWidth = 0;
			}
			canvas.drawText(detailCharArray, i, 1, (paddingLeft + drawedWidth), 
					(cLine+1) * textSize, paint);
			drawedWidth += charWidth;
		}
		setHeight((cLine + 1) * (int)textSize + 5);
	}
}
