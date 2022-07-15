package io.zeed.s3.service

/**
 *packageName    : io.zeed.s3.service
 * fileName       : FileService
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
interface FileService {

    fun upload()

    fun download()

    fun remove()
}