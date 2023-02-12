package com.freshtuna.openshop.config

/**
 * 컨트롤러와 mapping 되는 url 설정
 */
class UrlConfig {

    companion object {

        const val ROOT = "/openauth"
    }

    /**
     * external api 설정
     */
    class EXTERNAL {

        companion object {

            /**
             * jwt local member signin
             */
            const val JWT_LOCAL_SIGNIN = ROOT + "/sign-in/jwt/local"
        }
    }
}
