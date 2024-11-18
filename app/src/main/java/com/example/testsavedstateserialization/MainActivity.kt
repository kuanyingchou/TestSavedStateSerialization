package com.example.testsavedstateserialization

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.savedstate.savedState
import androidx.savedstate.serialization.saved
import kotlinx.serialization.Serializable

class MainActivity : AppCompatActivity() {
    private var s by saved {
        0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // =====================
        //testSavedState()
        testSavedStateProp()
    }

    private fun testSavedState() {
        val s = savedStateRegistry.consumeRestoredStateForKey("foo") ?: savedState {  }
        s.putInt("count", s.getInt("count", 0) + 1)
        Log.d("gyz", s.getInt("count").toString())
        savedStateRegistry.registerSavedStateProvider("foo", { s })
    }

    private fun testSavedStateProp() {
        s++
        Log.d("gyz", "s: $s")
    }
}

//class MyViewModel(val ssHandle: SavedStateHandle): ViewModel() {
//    //val s by ssHandle.saved { savedState {  } }
//    init {
//        if (!ssHandle.contains("foo")) {
//            ssHandle["foo"] = 123
//        }
//        println(ssHandle["foo"])
//    }
//    //val s by ssHandle.saved(serializer = serializer()) { MyClass(123) }
//}
//
//@Serializable
//data class MyClass(val s: Int)

@Serializable
data class MyClass(
    //@Serializable(with = SavedStateSerializer::class) val b: Bundle,
    //val s: SavedState,
    val c: CharSequence
)