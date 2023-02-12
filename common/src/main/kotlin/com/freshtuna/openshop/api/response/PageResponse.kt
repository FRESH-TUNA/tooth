package com.freshtuna.openshop.api.response;

import java.util.List;

/**
 * for page response
 */
class PageResponse<T> (
    val elements: List<T>,
    val page: PageInfo
)
