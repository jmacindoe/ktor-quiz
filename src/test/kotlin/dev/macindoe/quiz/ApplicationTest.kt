import dev.macindoe.quiz.module
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object ApplicationTest : Spek({
    describe("Quiz Application") {
        it("provides the quiz data") {
            testApp {
                handleRequest(HttpMethod.Get, "/v1/quiz/123").apply {
                    assertEquals(
"""
                        {
                          "id" : "123",
                          "description" : "The first ever quiz",
                          "questions" : [ {
                            "id" : "1",
                            "text" : "First Question",
                            "hints" : [ ]
                          } ]
                        }
""".trimIndent(),
                        response.content)
                }
            }
        }
    }
})


private fun testApp(callback: TestApplicationEngine.() -> Unit): Unit {
    withTestApplication({
        module()
    }, callback)
}
