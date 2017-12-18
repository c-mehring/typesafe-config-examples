package com.github.c_mehring.typesafe.config.examples

import com.typesafe.config.ConfigFactory
import org.scalatest.FlatSpec


class ConfigMergeTest extends FlatSpec {

  "A simple config" should "resolve internal properties" in {
    val cfg = ConfigFactory.parseString(
      """
        root.value: value of root
        ref.value: "HELLO: "${root.value}
      """).resolve

    assert(cfg.getString("ref.value").contains("$") == false)

  }

  "Two configs" should "be mergeable to one object" in {
    val cfgbase = ConfigFactory.parseString(
      """
        cfg-base.value = cf1.val
      """.stripMargin)

    val cfgapp = ConfigFactory.parseString(
      """
        cfg-app.value = app.val
      """.stripMargin)


    val toTest = cfgapp.withFallback(cfgbase).resolve


    assert(toTest.hasPath("cfg-base.value"))
    assert(toTest.hasPath("cfg-app.value"))

    assert(toTest.getString("cfg-base.value") == "cf1.val")
    assert(toTest.getString("cfg-app.value") == "app.val")
  }

  "A app-config" should "be resolvable with a base-config" in {
    val cfgbase = ConfigFactory.parseString(
      """
        db.user = "db-user"
      """.stripMargin)

    val cfgapp = ConfigFactory.parseString(
      """
        my.ds.user = ${db.user}
      """.stripMargin)

    val toTest = cfgapp.withFallback(cfgbase).resolve()

    println(toTest.root().render())

    assert(toTest.getString("my.ds.user") == "db-user")

  }

}
