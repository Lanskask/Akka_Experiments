package example

import java.util.Date

import akka.actor.{Actor, ActorLogging, ActorRef, FSM}
import example.TrafficLightFSM._

import scala.concurrent.duration._

class FSMChangeSubscriber extends Actor with ActorLogging {
  override def receive: Receive = {
    case ReportChange(s, d) => log.info(s"Change detected to [$s] at [$d]")
  }
}

object TrafficLightFSM {
  sealed trait TrafficLightState
  case object Green extends TrafficLightState
  case object Yellow extends TrafficLightState
  case object Red extends TrafficLightState

  sealed trait Data
  case class Countdown(i: Int) extends Data

  case object Tick
  case class ReportChange(to: TrafficLightState, date: Date)
}

class TrafficLightFSM(target: ActorRef) extends Actor with ActorLogging with FSM[TrafficLightState, Date] {

  import context.dispatcher

  trafficLightState(Green, Yellow, 2)
  trafficLightState(Yellow, Red, 4)
  trafficLightState(Red, Green, 8)
  startWith(Green, Countdown(8))

  initialize()
  scheduleTick()

  onTransition {
    case Green -> Yellow => target ! ReportChange(Yellow, new Date())
    case Yellow -> Red => target ! ReportChange(Red, new Date())
    case Green -> Yellow => target ! ReportChange(Yellow, new Date())
  }

  private def scheduleTick() =
    context.system.scheduler.scheduleOnce(1 second, self, Tick)

  private def trafficLightState(
     trafficLightState: TrafficLightState,
     nextTrafficLightState: TrafficLightState,
     totalSecondsNextState: Int
  ) = {
    when(trafficLightState) {
      case Event(Tick, Countdown(i)) if i != 0 =>
        scheduleTick()
      log.info(s"Current state [$trafficLightState]. Countdown: [$i]")
        stay using Countdown(i -1)
      case Event(Tick, Countdown(i)) if i == 0 =>
        scheduleTick()

        log.info(s"Changing from $trafficLightState to $nextTrafficLightState")
        goto(nextTrafficLightState) using Countdown(totalSecondsNextState)

    }
  }
}
