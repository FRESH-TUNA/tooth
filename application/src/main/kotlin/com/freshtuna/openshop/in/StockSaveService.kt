package com.freshtuna.openshop.`in`

import com.freshtuna.openshop.Stock
import com.freshtuna.openshop.out.StockSaveOutPort

/**
 * 재고 저장과 관련된 어댑터
 */
class StockSaveService(
    private val stockSaveOutPort: StockSaveOutPort
) : StockSaveInPort {

    override fun saveStock(newStock: Stock) {
        stockSaveOutPort.saveStock(newStock)
    }
}
