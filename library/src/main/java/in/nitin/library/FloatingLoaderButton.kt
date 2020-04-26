package `in`.nitin.library

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat


/**
 * [MustBeDocumented]
 * @author Nitin Prakash
 *
 * @since 25 April 2020
 *
 * @property:- * it can use to show loading status for an API, background task or  anything
 *
 * */

@BindingMethods(
    BindingMethod(
        type = FloatingLoaderButton::class,
        attribute = "app:loadingStatus",
        method = "setLoadingStatus"
    ),
    BindingMethod(
        type = FloatingLoaderButton::class,
        attribute = "app:loadingIconColor",
        method = "setLoadingIconColor"
    ), BindingMethod(
        type = FloatingLoaderButton::class,
        attribute = "app:loaderBackgroundColor",
        method = "setLoaderBgColor"
    )

)

class FloatingLoaderButton : AppCompatImageView {
    enum class LoaderStatus {
        LOADING,
        FINISH,
        NONE
    }

    private enum class LoaderFabSize {
        SMALL,
        MEDIUM,
        LARGE
    }


    private var iconColor: Int = 0
    private var loaderStatus = LoaderStatus.NONE
    private var loaderSize = LoaderFabSize.MEDIUM
    private var loaderbgColor: Int = 0
    var isLoading: Boolean = false


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initViewRes(context, attrs, defStyleAttr)
    }

    @SuppressLint("CustomViewStyleable")
    private fun initViewRes(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        val styleAttributesArray =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.FabLoader,
                defStyleAttr,
                0
            )

        iconColor =
            styleAttributesArray.getColor(R.styleable.FabLoader_loadingIconColor, Color.WHITE)
        loaderbgColor =
            styleAttributesArray.getColor(R.styleable.FabLoader_loaderBackgroundColor, Color.BLACK)
        loaderStatus =
            styleAttributesArray.getEnum(R.styleable.FabLoader_loadingStatus, LoaderStatus.NONE)


        loaderSize =
            styleAttributesArray.getEnum(R.styleable.FabLoader_loaderFabSize, LoaderFabSize.MEDIUM)

        setLoadingStatus(loaderStatus)
        setLoaderBgColor(loaderbgColor)
        setElevation()
        setLoaderPadding()
        styleAttributesArray.recycle()

    }

    private fun setElevation() {
        val mElevation = getDimens(resources, R.dimen.loader_elevation).toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.elevation = mElevation
        } else {
            ViewCompat.setElevation(this, mElevation)
        }
    }

    private fun setLoaderBgColor(@ColorInt bgColor: Int) {
        when (loaderSize) {
            LoaderFabSize.SMALL -> {
                this.background = getRoundCornerShapeRect(
                    getDimens(
                        resources,
                        R.dimen.small_fab_loader
                    ).toFloat(), bgColor
                )
            }
            LoaderFabSize.MEDIUM -> {
                this.background = getRoundCornerShapeRect(
                    getDimens(
                        resources,
                        R.dimen.medium_fab_loader
                    ).toFloat(), bgColor
                )
            }
            LoaderFabSize.LARGE -> {
                this.background = getRoundCornerShapeRect(
                    getDimens(
                        resources,
                        R.dimen.large_fab_loader
                    ).toFloat(), bgColor
                )
            }
        }
    }

    private fun setLoaderPadding() {
        val pad = getDimens(
            resources,
            R.dimen.loader_padding
        )
        this.setPadding(pad, pad, pad, pad)

    }

    @SuppressLint("ResourceType")
    private fun setLoadingIconColor(@ColorInt iconColor: Int) {
        setColorFilter(this.drawable, iconColor)
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        when (loaderSize) {
            LoaderFabSize.SMALL -> {
                setFabButtonSize(getDimens(resources, R.dimen.small_fab_loader))
            }
            LoaderFabSize.MEDIUM -> {
                setFabButtonSize(getDimens(resources, R.dimen.medium_fab_loader))
            }
            LoaderFabSize.LARGE -> {
                setFabButtonSize(getDimens(resources, R.dimen.large_fab_loader))
            }
        }
    }

    private fun setFabButtonSize(size: Int) {
        setMeasuredDimension(size, size)
    }

    /**
     * set Loader status
     * */
    fun setLoadingStatus(value: LoaderStatus) {
        when (value) {

            LoaderStatus.LOADING -> {

                startLoadingAnim()
            }
            LoaderStatus.FINISH -> {
                isLoading = false
            }
            LoaderStatus.NONE -> {
                getAnimatedDrawable(R.drawable.start_loading_56dp)

            }

        }
    }

    /**
     * Use for start loading animation
     * */
    private fun startLoadingAnim() {

        isLoading = true

        this.isClickable = false

        val anim = getAnimatedDrawable(R.drawable.start_loading_56dp)
        anim.start()
        anim.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {

            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)

                /**
                 * [infinite] Loader
                 * */
                startCircularLoaderAnim()

            }
        })
    }


    /**
     * Execute immediately when startLoading anim finish
     * */
    private fun startCircularLoaderAnim() {
        val loading = getAnimatedDrawable(R.drawable.progress_56dp)
        loading.start()
        loading.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                if (!isLoading) {
                    finishLoadingAnim()
                } else {
                    loading.start()
                }

            }
        })
    }


    /**
     * Execute when user end the loading animation
     * */
    private fun finishLoadingAnim() {
        getAnimatedDrawable(R.drawable.end_loading_56dp).start()
        this.isClickable = true
    }


    /**
     * convert background as AnimatedVectorDrawable and return it
     * */
    private fun getAnimatedDrawable(animatedVectorDrawable: Int): Animatable2Compat {
        val drawable = AnimatedVectorDrawableCompat.create(context, animatedVectorDrawable)
        this.setImageDrawable(drawable)
        setLoadingIconColor(iconColor)
        return this.drawable as Animatable2Compat

    }

    /*get paint object with color*/
    private fun getPaint(@ColorInt bgColor: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = bgColor
        return paint
    }

    /*get rounded corner rectangle with given corner radius*/
    private fun getRoundCornerShapeRect(
        @FloatRange cornerRadius: Float,
        @ColorInt bgColor: Int
    ): ShapeDrawable {
        val shape = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius,
                    cornerRadius
                ),
                null, null
            )
        )

        shape.paint.set(getPaint(bgColor))
        return shape
    }

    private fun getDimens(resources: Resources, id: Int): Int {
        return resources.getDimension(id).toInt()
    }

    /**
     * get Enum for loader
     * */
    private inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
        getInt(index, -1).let {
            if (it >= 0) enumValues<T>()[it] else default
        }

}