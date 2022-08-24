package com.robsonribeiroft.chuckjokes.domain.exception

class InvalidParamException(
    override val message: String? = "A UseCase Param is invalid"
): Exception()