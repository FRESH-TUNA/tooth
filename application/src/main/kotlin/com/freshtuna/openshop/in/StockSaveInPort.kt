package com.freshtuna.openshop.`in`

import com.freshtuna.openshop.Stock

interface StockSaveInPort {
    /**
     * 새로운 한개의 재고를 생성한다.
     */
    fun saveStock(newStock: Stock)
}
