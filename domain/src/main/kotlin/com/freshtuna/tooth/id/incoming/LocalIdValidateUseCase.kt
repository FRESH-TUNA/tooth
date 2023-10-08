package com.freshtuna.tooth.id.incoming

import com.freshtuna.tooth.id.ID

interface LocalIdValidateUseCase {

    fun validate(localID: ID)
}
