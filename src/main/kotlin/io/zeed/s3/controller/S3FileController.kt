package io.zeed.s3.controller

import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.Part
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

/**
 *packageName    : io.zeed.s3.controller
 * fileName       : S3FileController
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
@RestController
class S3FileController {

    @PostMapping("/upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(
        @RequestHeader("Content-Type", required = true)
        contentType: String,
        @RequestHeader("Content-Length", required = true)
        contentLength: Long,
        @RequestBody
        data: Part
    ) {

    }
}