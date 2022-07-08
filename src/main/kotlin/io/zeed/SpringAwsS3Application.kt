package io.zeed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringAwsS3Application

fun main(args: Array<String>) {
    runApplication<SpringAwsS3Application>(*args)
}
