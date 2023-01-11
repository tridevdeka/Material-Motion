package com.tridev.materialcontainertransform

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

object MaterialTransitionUtils {

    fun AppCompatActivity.applyEnterMaterialTransform(transitionName: String?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        ViewCompat.setTransitionName(findViewById(android.R.id.content), transitionName)

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        window.sharedElementEnterTransition = getContentTransform()
        window.sharedElementReturnTransition = getReturnContentTransform()
    }

    fun AppCompatActivity.applyExitMaterialTransform() {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
    }

    private fun getReturnContentTransform(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 400L
            pathMotion = MaterialArcMotion()
            isElevationShadowEnabled = true
            startElevation = 9f
            endElevation = 9f
            scrimColor= Color.TRANSPARENT
        }
    }

    private fun getContentTransform(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 700L
            pathMotion = MaterialArcMotion()
            isElevationShadowEnabled = true
            startElevation = 9f
            endElevation = 9f
            scrimColor= Color.TRANSPARENT
        }
    }

    internal fun Resources.Theme.windowBackground(): Int {
        val a = TypedValue()
        resolveAttribute(android.R.attr.windowBackground, a, true)
        return a.resourceId
    }
}