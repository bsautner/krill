@file:OptIn(ExperimentalSerializationApi::class)

package client


import kotlinx.serialization.ExperimentalSerializationApi


//val serializerModule = SerializersModule {
//
//    polymorphic(QueryResults::class) {
//        subclass(QueryResults.AppQueryResult::class, QueryResults.AppQueryResult.serializer())
//        subclass(QueryResults.ApiQueryResult ::class, QueryResults.ApiQueryResult.serializer())
//    }
//    polymorphic(Api::class) {
//        subclass(Api.BasePagingApiQuery::class, Api.BasePagingApiQuery.serializer())
//        subclass(Api.ApiQuery::class, Api.ApiQuery.serializer())
//    }
//}



