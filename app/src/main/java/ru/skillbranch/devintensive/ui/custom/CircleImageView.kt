package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import ru.skillbranch.devintensive.R


class CircleImageView(
    context:Context,
    attrs:AttributeSet,
    defStyleAttr:Int
):ImageView(context,attrs,defStyleAttr)  {
    companion object{
        private const val DEFAULT_BOARDER = Color.WHITE
    }

    private var borderColor = DEFAULT_BOARDER
    private var borderWidth = getSizeInPx(2)
    private var bitmap: Bitmap? = null

    init {
        if(attrs != null){
            val attribute = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = attribute.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BOARDER)
            borderWidth = attribute.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth,borderWidth)
            attribute.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val newHeight = (measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {

        var bitmap = getBitmapFromDrawable() ?: false
        if (width == 0 || height == 0) return

    }



    fun getBorderWidth():Int = getSizeInDp(borderWidth)

    fun setBorderWidth(dp:Int){
        borderWidth = getSizeInPx(dp)
        this.invalidate()
    }

    fun getBorderColor():Int = borderColor
    fun setBorderColor(hex:String) {borderColor = Color.parseColor(hex)}

    fun setBorderColor(@ColorRes colorId: Int){
        borderColor = colorId
        this.invalidate()
    }

    private fun getSizeInPx(dp: Int): Int {
        return  (dp * resources.displayMetrics.density + 0.5f).toInt()
    }

    private fun getSizeInDp(px:Int):Int{
        return  (px / resources.displayMetrics.density + 0.5f).toInt()
    }

    private fun getBitmapFromDrawable():Bitmap?{
        if (drawable == null) return null
        if (drawable is BitmapDrawable) return (drawable as BitmapDrawable).bitmap

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0,0,canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }



}