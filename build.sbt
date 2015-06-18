name := "firstplay"

version := "1.0"

lazy val `firstplay` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"



unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies ~= {
  _ map {
    case m if !m.organization.isEmpty => m.exclude("commons-logging", "commons-logging")
    case m => m
  }
}

//assemblyJarName in assembly:="mq.jar"

// Take the first ServerWithStop because it's packaged into two jars
assemblyMergeStrategy in assembly := {
  case PathList("play", "core", "server", "ServerWithStop.class") => MergeStrategy.first
  case x if x.endsWith("spring.tooling") => MergeStrategy.last
  case other => (assemblyMergeStrategy in assembly).value(other)
}