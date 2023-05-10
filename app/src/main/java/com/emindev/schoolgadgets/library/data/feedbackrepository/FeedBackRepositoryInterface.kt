package com.emindev.schoolgadgets.library.data.feedbackrepository

import com.emindev.schoolgadgets.library.common.model.Comment
import com.emindev.schoolgadgets.library.common.model.Error
import com.emindev.schoolgadgets.library.common.model.Resource

interface FeedBackRepositoryInterface {

    fun setError(error: Error, result: (Resource<Error>) -> Unit)

    fun setComment(comment: Comment, result: (Resource<Comment>) -> Unit)

}