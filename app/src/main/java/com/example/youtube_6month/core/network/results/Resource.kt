package com.example.youtube_6month.core.network.results

data class Resource <T> (val status:Status, val  data:T?, val message:String?){


    companion object{

        fun <T> success (data:T? ): Resource <T>{
            return Resource(Status.SUCCESS,data,null)
        }


        fun <T> loading(): Resource<T>{
            return Resource(Status.LOADING,null,null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data ,msg)
        }
    }
}

