package com.freshtuna.openshop.responses.base;

import java.util.List;

/**
 * for page response
 */
class PageResponse<T> (
    val elements: List<T>,
    val page: PageInfo
)
