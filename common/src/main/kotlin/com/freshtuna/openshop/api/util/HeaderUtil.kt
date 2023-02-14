package com.freshtuna.openshop.api.util

import jakarta.servlet.http.HttpServletResponse

class HeaderUtil {

    companion object {
        /**
         * 응담에 header 값을 설정한다.
         * 같은 키로 설정되있는 값이 있다면 덮어쒸운다.
         */
        fun addHeader(name: String, value: String,
                      response: HttpServletResponse
        ) {
            response.addHeader(name, value)
        }
    }
}
