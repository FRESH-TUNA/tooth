package com.freshtuna.openshop

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StockTest {

    @Test
    @DisplayName("재고 생성 테스트")
    fun createStockTest() {
        /**
         * given
         * 재고 생성에 필요한 정보
         * 이름
         * (향후추가예정) 용량, 용량 단위
         * (향후추가예정) 제조사, 판매사
         */
        val name = "만들면서 배우는 클린 아키텍쳐"


        /**
         * then
         * 재고 생성
         */
        val newStock : Stock = Stock.createStock(name)


        /**
         * when
         */
        assertEquals(newStock.name, name)
    }
}
