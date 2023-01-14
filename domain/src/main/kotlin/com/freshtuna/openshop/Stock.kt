package com.freshtuna.openshop

/**
 * 재고 도메인
 */
class Stock private constructor(// 재고의 이름
    var name: String
) {
    companion object {
        fun createStock(name: String): Stock {
            return Stock(name)
        }
    }
}
