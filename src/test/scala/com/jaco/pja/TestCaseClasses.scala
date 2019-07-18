package com.jaco.pja

case class Configuration(logoPath: String,
                         featureConfiguration: FeatureConfiguration,
                         errorNotificationRate: Double)

case class FeatureConfiguration(enableFeatureA: Boolean,
                                enableFeatureB: Boolean,
                                experimentalFeatureConfiguration: ExperimentalConfiguration)

case class ExperimentalConfiguration(enableExperimentOne: Boolean,
                                     enableExperimentTwo: Boolean)
