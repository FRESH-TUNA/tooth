package com.freshtuna.tooth.config.constant

/**
 * 컨트롤러와 mapping 되는 url 설정
 */
class Url {

    companion object {
        const val ROOT = "/auth"
    }

    /**
     * external api 설정
     */
    class EXTERNAL {

        companion object {


            /**
             * jwt local member signin
             */
            const val JWT_LOCAL_SIGNIN = "$ROOT/sign-in/jwt/local"

            /**
             * jwt local member signup
             */
            const val JWT_LOCAL_SIGNUP = "$ROOT/sign-up/jwt/local"

            /**
             * jwt refresh
             */
            const val JWT_REFRESH = "$ROOT/refresh/jwt"

            /**
             * change password
             */
            const val CHANGE_PASSWORD = "$ROOT/change-password"
        }
    }
}
