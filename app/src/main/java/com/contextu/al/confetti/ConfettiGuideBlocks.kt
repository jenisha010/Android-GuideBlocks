package com.contextu.al.confetti

import android.app.Activity
import android.view.ViewGroup
import com.contextu.al.R
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit


class ConfettiGuideBlocks(private val activity: Activity) {

    fun show(){
        val view = activity.layoutInflater.inflate(R.layout.konfetti, null, true)
        val viewGroup = (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
        )
        val viewKonfetti = view.findViewById<KonfettiView>(R.id.konfettiView)
        viewGroup.addView(viewKonfetti)
        viewKonfetti.start(party)
    }


}