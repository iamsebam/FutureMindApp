package com.sebastianmatyjaszczyk.commonlib

import android.view.View

fun View.setVisible(visible: Boolean, hiddenState: Int = View.GONE) {
    visibility = if (visible) View.VISIBLE else hiddenState
}
