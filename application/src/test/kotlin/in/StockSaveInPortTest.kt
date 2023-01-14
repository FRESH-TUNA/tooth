package `in`

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.mockito.Mockito.*

class StockSaveInPortTest {
    @Test
    @DisplayName("단일 재고 저장 테스트")
    fun createStockTest() {
        val stockSaveInPort = mock(StockSaveInPort::class.java)
    }
}