package `in`

import com.freshtuna.openshop.`in`.StockSaveInPort
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class StockSaveInPortTest {
    @Test
    @DisplayName("단일 재고 저장 테스트")
    fun createStockTest() {
        val stockSaveInPort : StockSaveInPort = mockk()
    }
}