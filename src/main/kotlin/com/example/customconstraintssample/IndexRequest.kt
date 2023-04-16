package com.example.customconstraintssample

import com.example.customconstraintssample.constraints.CustomConstraints

enum class IndexType {
    TYPE1,
    TYPE2,
}

@CustomConstraints
data class IndexRequest(
    val type: IndexType,
    val type1Value: String?,
    val type2Value: Int?,
)
