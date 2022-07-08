package io.zeed.s3.handler

import org.springframework.http.codec.multipart.FilePart

/**
 *packageName    : io.zeed.s3.handler
 * fileName       : FileUploadHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/07/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/08        kimdonggyuuuuu       최초 생성
 */
interface FileUploadHandler<T> {

    fun upload(file: FilePart): T
}