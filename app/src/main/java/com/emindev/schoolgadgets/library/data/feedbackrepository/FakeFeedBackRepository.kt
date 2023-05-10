package com.emindev.schoolgadgets.library.data.feedbackrepository

import com.emindev.schoolgadgets.library.common.model.Comment
import com.emindev.schoolgadgets.library.common.model.Error
import com.emindev.schoolgadgets.library.common.model.Resource

class FakeFeedBackRepository() : FeedBackRepositoryInterface {
    override fun setError(error: Error, result: (Resource<Error>) -> Unit) {
        // TODO: Activate real feedback repository
    }

    override fun setComment(comment: Comment, result: (Resource<Comment>) -> Unit) {
        // TODO: Activate real feedback repository
    }
}