package com.uiza.api

enum class UZEnvironment {

    DEVELOPMENT {
        override fun getHost() = "https://development-api.uizadev.io"
    },
    STAGING {
        override fun getHost() = "https://development-api.uizadev.io"
    },
    PRODUCTION {
        override fun getHost() = "https://api.uiza.sh"
    };

    abstract fun getHost(): String
}