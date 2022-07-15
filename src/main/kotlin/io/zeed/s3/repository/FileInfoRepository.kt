package io.zeed.s3.repository

import io.zeed.s3.document.FileInfoDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

/**
 *packageName    : io.zeed.s3.repository
 * fileName       : FileInfoRepository
 * author         : kimdonggyuuuuu
 * date           : 2022/07/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/07/15        kimdonggyuuuuu       최초 생성
 */
@Repository
interface FileInfoRepository: ReactiveMongoRepository<FileInfoDocument, ObjectId> {
}