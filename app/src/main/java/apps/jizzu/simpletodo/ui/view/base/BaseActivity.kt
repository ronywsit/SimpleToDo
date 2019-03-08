package apps.jizzu.simpletodo.ui.view.base

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import apps.jizzu.simpletodo.R
import daio.io.dresscode.dressCodeStyleId
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    fun initToolbar(titleText: String, drawable: Int? = R.drawable.round_close_black_24, view: Toolbar? = toolbar) {
        title = ""
        toolbarTitle.text = titleText

        if (toolbar != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                val color = when (dressCodeStyleId) {
                    R.style.AppTheme_Light -> R.color.greyWhite
                    R.style.AppTheme_Dark -> R.color.deepBlueGrey
                    R.style.AppTheme_Black -> R.color.black
                    else -> R.color.greyWhite
                }
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, color)
            }
            setSupportActionBar(view)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            if (drawable != null) {
                supportActionBar?.setHomeAsUpIndicator(drawable)
            }
        }
    }

    fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when(dressCodeStyleId) {
                R.style.AppTheme_Light -> {
                    var flags = toolbar.systemUiVisibility
                    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    toolbar.systemUiVisibility = flags
                    this.window.statusBarColor = Color.WHITE
                }
                R.style.AppTheme_Dark -> ContextCompat.getColor(this, R.color.deepBlueGrey)
                R.style.AppTheme_Black -> ContextCompat.getColor(this, R.color.black)
            }
        }
    }
}