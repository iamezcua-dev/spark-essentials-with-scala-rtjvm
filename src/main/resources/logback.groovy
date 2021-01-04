import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import static ch.qos.logback.classic.Level.DEBUG

scan( "15 seconds" )
appender( "STDOUT", ConsoleAppender ) {
  encoder( PatternLayoutEncoder ) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}

logger( "part2dataframes.DataFramesBasics", DEBUG )
root( WARN, [ "STDOUT" ] )
