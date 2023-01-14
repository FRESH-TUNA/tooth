package com.freshtuna.openshop.out

import com.freshtuna.openshop.Stock

interface StockSaveOutPort {
    fun saveStock(stock: Stock)
}
