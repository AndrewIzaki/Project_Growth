package com.fatec.regrowth

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.fatec.regrowth.R

class DraggableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private var backgroundImage: Drawable? = null // Adicione um atributo para a imagem


    init {
        // Carregue a imagem de fundo
        backgroundImage = ContextCompat.getDrawable(context, R.mipmap.leafimg_foreground)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = x
                lastTouchY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = x - lastTouchX
                val dy = y - lastTouchY

                // Mova o objeto arrastável
                translationX += dx
                translationY += dy

                lastTouchX = x
                lastTouchY = y
            }
            else -> return false
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Verifique se a imagem de fundo não é nula e se o canvas não é nulo
        if (backgroundImage != null && canvas != null) {
            // Defina o tamanho da imagem com base no tamanho da view
            backgroundImage!!.setBounds(0, 0, width, height)
            // Desenhe a imagem de fundo
            backgroundImage!!.draw(canvas)
        }
    }
}
