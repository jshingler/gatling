package webservice.gatling.simulation
 
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
 
class WebServiceCallSimulation extends Simulation {

    val rampUpTimeSecs = 5
    val testTimeSecs = 20
    val noOfUsers = 10
    val minWaitMs = 1000 milliseconds
    val maxWaitMs = 3000 milliseconds  


    val httpProtocol = http
        .baseUrl("http://computer-database.gatling.io")
        .inferHtmlResources(BlackList(""".*\.css""", """.*\.js""", """.*\.ico"""), WhiteList())
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0")

    val scn = scenario("RecordedSimulation")
        .exec(http("request_0")
            .get("/"))
        .pause(5)
        .exec(http("request_1")
            .get("/computers?f=amstrad"))
        .pause(4)
        .exec(http("request_2")
            .get("/computers/412"))
        .pause(2)
        .exec(http("request_3")
            .get("/"))
        .pause(2)
        .exec(http("request_4")
            .get("/computers?p=1"))
        .pause(1)
        .exec(http("request_5")
            .get("/computers?p=2"))
        .pause(2)
        .exec(http("request_6")
            .get("/computers?p=3"))

    setUp(
        scn.inject(rampUsers(noOfUsers) during (rampUpTimeSecs)))
        // scn.inject(atOnceUsers(1)))
        .protocols(httpProtocol)

//   val httpConf = http.baseURL("http://api.football-data.org/")
//   val scn = scenario("Basic Simulation")
//     .exec(http("request_1")
//     .get("v1/teams/73"))
//     .pause(5)
//   setUp(
//     scn.inject(atOnceUsers(1))
//   ).protocols(httpConf)
 
//  val rampUpTimeSecs = 5
//  val testTimeSecs = 20
//  val noOfUsers = 10
//  val minWaitMs = 1000 milliseconds
//  val maxWaitMs = 3000 milliseconds
 
// //  val baseURL = "http://localhost:8080"
//  val baseURL = "http://api.football-data.org/"
//  val baseName = ""
// //  val baseName = "webservice-call-greeting"
//  val requestName = baseName + "-request"
//  val scenarioName = baseName + "-scenario"
//  val URI = "/greeting"
 
//  val httpConf = http
//   .baseURL("http://api.football-data.org/")
//   .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
//   .doNotTrackHeader("1")
//   .acceptLanguageHeader("en-US,en;q=0.5")
//   .acceptEncodingHeader("gzip, deflate")
//   .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
 
//  val scn = scenario(scenarioName)
//   .during(testTimeSecs) {
//    exec(
//     http(requestName)
//      .get(URI)
//      .check(status.is(200))
//     ).pause(minWaitMs, maxWaitMs) 
//   }
  
//  setUp(
//   scn.inject(rampUsers(noOfUsers) over (rampUpTimeSecs))
//   ).protocols(httpConf)
 }