package io.zeed.s3.handler

import reactor.core.publisher.Mono

/**
 *packageName    : io.zeed.s3.handler
 * fileName       : ReactiveS3FileUploadHandler
 * author         : kimdonggyuuuuu
 * date           : 2022/07/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/08        kimdonggyuuuuu       최초 생성
 */
interface ReactiveS3FileUploadHandler: FileUploadHandler<Mono<String>> {
}