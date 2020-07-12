package com.example

import com.typesafe.config.ConfigFactory

object ClusteringBasics extends App {
  def startCluster(ports: List[Int]): Unit = {
    ports.foreach {
      port =>
        val config = ConfigFactory.parseString(
          s"""
             |akka.remote.artery.canonical.port = $port
             |""".stripMargin
        ).withFallback(ConfigFactory.load("application.conf"))
    }
  }
}