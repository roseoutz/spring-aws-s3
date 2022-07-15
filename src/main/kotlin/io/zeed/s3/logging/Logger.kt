package io.zeed.s3.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *packageName    : io.zeed.s3.loggin
 * fileName       : Logger
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}