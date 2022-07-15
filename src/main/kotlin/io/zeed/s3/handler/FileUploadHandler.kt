package io.zeed.s3.handler

import io.zeed.s3.dto.*
import reactor.core.publisher.Mono

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
interface FileUploadHandler {

    fun upload(fileUploadRequest: FileUploadRequest): Mono<FileUploadResponse>

    fun download(fileDownloadRequest: FileDownloadRequest): Mono<FileDownloadResponse>

    fun remove(fileRemoveRequest: FileRemoveRequest): Mono<FileRemoveResponse>
}