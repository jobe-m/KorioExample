import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.io.async.*
import korlibs.io.file.std.*
import korlibs.math.geom.*
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

suspend fun main() = Korge(windowSize = Size(512, 512), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
	override suspend fun SContainer.sceneMain() {

// loading here works
//        val image = resourcesVfs["korge.png"].readBitmap()

        var runOnce = false
        addUpdater {
            if (!runOnce) {
                CoroutineScope(Dispatchers.CIO).launch {
// loading here does not work
                    val image = resourcesVfs["korge.png"].readBitmap()
                }
                runOnce = true
            }
        }
	}
}
