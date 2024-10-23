package com.example.spotifycopy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.LinearGradient
import android.graphics.Path

class ColorCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var colors: IntArray = intArrayOf(
        0xFF6200EE.toInt(),
        0xFF03DAC5.toInt(),
        0xFFFF5722.toInt(),
        0xFF6200EE.toInt()
    )
    private var animationValue: Float = 0f

    init {
        startColorAnimation()
    }

    private fun startColorAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 5000
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                animationValue = animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        // Определяем градиент
        val gradient = LinearGradient(
            0f, 0f, width, height,
            getCurrentColors(),
            null,
            Shader.TileMode.MIRROR
        )

        paint.shader = gradient
        canvas.drawRect(0f, 0f, width, height, paint)
    }

    private fun getCurrentColors(): IntArray {
        val startColorIndex = (animationValue * (colors.size - 1)).toInt()
        val endColorIndex = (startColorIndex + 1) % colors.size

        return intArrayOf(colors[startColorIndex], colors[endColorIndex])
    }
}
