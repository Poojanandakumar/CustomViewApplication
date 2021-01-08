package com.codelab.customviewapplication

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.Toast

class BarGraph(context: Context?, attrs: AttributeSet?) :
        View(context, attrs),View.OnClickListener {
    private var barLeft: Float = 0f
    private var barRight: Float = 0f
    private var gridBottom: Float = 0f
    private var gridRight: Float = 0f
    private var gridLeft: Float = 0f
    private var gridTop: Float = 0f
    private var p = arrayOf(0.6, 0.3, 0.5, 0.7)
    private var barGap = 100
    private var barTop = 0.0
    private var mFraction = 0f

    private val barPaint: Paint = Paint().apply {
        color = Color.BLUE
    }

    private val gridPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 8f
    }

    private val guidLinePaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.GRAY
        strokeWidth = 9f
    }

    private val mAnimator = ValueAnimator().apply {
        duration = 500
        interpolator = BounceInterpolator()
        addUpdateListener{
            mFraction = it.animatedFraction
            invalidate()

        }

    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            canvas.drawLine(gridLeft, gridBottom, gridLeft, gridTop, gridPaint)
            canvas.drawLine(gridLeft, gridBottom, gridRight, gridBottom, gridPaint)

            val guidLineSpacing = (gridBottom - gridTop) / 10f
            var y: Float
            for (i in 0..9) {
                y = gridTop + i * guidLineSpacing
                canvas.drawLine(gridLeft, y, gridRight, y, guidLinePaint)
            }
            Log.d("poocha", "${multiplier.toDouble()}")

            for (j in 0..3) {
//            val j = 0
                barTop = gridTop + (gridBottom - gridTop) * (1 - p[j]*multiplier)
                canvas.drawRect(barLeft, barTop.toFloat(), barRight, gridBottom, barPaint)

                barLeft = barRight + barGap
                barRight = barLeft + barGap

            }
            barLeft = gridLeft + barGap
            barRight = barLeft + barGap

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
        val mPadding = 50
        gridLeft = mPadding.toFloat()
        gridRight = width - mPadding.toFloat()
        gridTop = mPadding.toFloat()
        gridBottom = height - mPadding.toFloat()
        barLeft = gridLeft + barGap
        barRight = barLeft + barGap

    }


//    override fun onClick(v: View?) {
//        mAnimator.setFloatValues(0f, 1f)
//        Toast.makeText(context, " worked", Toast.LENGTH_SHORT).show()
//    }
    var multiplier=0f
    set(value) {
        Log.d("pooja", "$value: ")
        field = value
        invalidate()
    }

    fun animateGraph(){
        val animator=ObjectAnimator.ofFloat(this,
                "multiplier",0f,1f)
        animator.duration = 500
        animator.interpolator=AccelerateInterpolator()
        animator.start()

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}

