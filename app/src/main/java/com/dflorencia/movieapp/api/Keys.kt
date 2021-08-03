package com.dflorencia.movieapp.api

object Keys {
    init {
        try {
            System.loadLibrary("native-lib")
        } catch (e:UnsatisfiedLinkError) {

        }
    }

    external fun apiKey(): String
}