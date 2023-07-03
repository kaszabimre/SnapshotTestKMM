package tech.apter.snapshottestandroid

import android.app.Activity
import com.airbnb.android.showkase.models.Showkase

@Suppress("unused")
object ShowkaseNavigation {
    fun goToShowkase(activity: Activity) {
       activity.startActivity(Showkase.getBrowserIntent(activity))
    }
}
