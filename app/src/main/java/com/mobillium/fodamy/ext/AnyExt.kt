package com.mobillium.fodamy.ext

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


inline fun <reified T> Any.findGenericSuperclass(): ParameterizedType? {
    return javaClass.findGenericSuperclass(T::class.java)
}

tailrec fun <T> Type.findGenericSuperclass(targetType: Class<T>): ParameterizedType? {
    val genericSuperClass = ((this as? Class<*>)?.genericSuperclass) ?: return null

    if ((genericSuperClass as? ParameterizedType)?.rawType == targetType) {
        return genericSuperClass
    }

    return genericSuperClass.findGenericSuperclass(targetType)
}
