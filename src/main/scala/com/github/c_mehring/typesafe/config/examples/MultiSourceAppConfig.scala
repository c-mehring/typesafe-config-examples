package com.github.c_mehring.typesafe.config.examples

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}

object MultiSourceAppConfig {

  lazy val resolvedConfig: Config = {
    val envProps = ConfigFactory.parseFile(new File("env.properties"))
    val reference = ConfigFactory.parseResourcesAnySyntax("reference")
    val application = ConfigFactory.parseResourcesAnySyntax("application")

    ConfigFactory.systemProperties()
      .withFallback(application)
      .withFallback(reference)
      .withFallback(envProps)
      .resolve()

  }

}
