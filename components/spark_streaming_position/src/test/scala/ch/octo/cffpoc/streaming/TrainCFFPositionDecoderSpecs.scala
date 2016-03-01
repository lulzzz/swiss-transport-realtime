package ch.octo.cffpoc.streaming

import ch.octo.cffpoc.models.{ GeoLoc, TimedPosition }
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by Alexandre Masselot on 17/02/16.
 * © OCTO Technology
 */
class TrainCFFPositionDecoderSpecs extends FlatSpec with Matchers {
  behavior of "TrainCFFPositionDecoder"

  it should "decode" in {
    val bs: Array[Byte] =
      """{"x":"7589565",
        "y":"47547399",
        "name":"ICE 71  ",
        "trainrefdate":"12.02.16",
        "category":"ICE",
        "trainid":"84/27911/18/19/95",
        "direction":"30","prodclass":"1",
        "passproc":"",
        "lstopname":"Chur",
        "poly":[{"x":"7589565","y":"47547399","passproc":"","msec":"0","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"2000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"4000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"6000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"8000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"10000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"12000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"14000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"16000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"18000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"20000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"22000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"24000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"26000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"28000","direction":""},{"x":"7589565","y":"47547399","passproc":"","msec":"30000","direction":""}],"timeStamp":1455742294099,"@version":"1","@timestamp":"2016-02-12T12:05:45.302Z"}""".getBytes
    val tp = new TrainCFFPositionDecoder().fromBytes(bs)
    val current = tp.current
    current.trainid should equal("84/27911/18/19/95")
    current.name should equal("ICE 71")
    current.lastStopName should equal("Chur")
    current.category should equal("ICE")
    current.timedPosition.position should equal(GeoLoc(47.547399, 7.589565))
    current.timedPosition.timestamp should equal(1455742294099L)

    tp.futurePositions should have length 16
    tp.futurePositions(2) should equal(TimedPosition(1455742294099L + 4000, GeoLoc(47.547399, 7.589565)))
  }
}