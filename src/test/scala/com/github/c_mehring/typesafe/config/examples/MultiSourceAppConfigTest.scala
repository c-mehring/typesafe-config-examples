package com.github.c_mehring.typesafe.config.examples

import org.scalatest.FlatSpec


class MultiSourceAppConfigTest extends FlatSpec {

  "A merged config" should "contain a overridden value " in {

    val cfg = MultiSourceAppConfig.resolvedConfig

    assert(cfg.getString("example.app.key") == "Hello Override")

  }

  it should "substitute the db-user from env.properties" in {
    val cfg = MultiSourceAppConfig.resolvedConfig

    assert(cfg.getString("example.db.user") == "db-user")
  }

  it should "provide all system properties" in {
    val cfg = MultiSourceAppConfig.resolvedConfig

    assert(cfg.hasPath("user.home"), cfg.root().render)
  }
}
