package com.example.customconstraintssample.constraints

import com.example.customconstraintssample.IndexRequest
import com.example.customconstraintssample.IndexType
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CustomConstraints.CustomConstraintValidator::class])
annotation class CustomConstraints(
    val message: String = "{com.example.customconstraintssample.constraints.CustomConstraints.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
) {
    class CustomConstraintValidator : ConstraintValidator<CustomConstraints, IndexRequest> {
        override fun isValid(value: IndexRequest, context: ConstraintValidatorContext): Boolean {
            return when (value.type) {
                IndexType.TYPE1 -> {
                    if (value.type1Value != null && value.type2Value == null) return true
                    context.disableDefaultConstraintViolation()
                    context.buildConstraintViolationWithTemplate("{com.example.customconstraintssample.constraints.CustomConstraints.type1.message}")
                        .addConstraintViolation()
                    false
                }
                IndexType.TYPE2 -> {
                    if (value.type1Value == null && value.type2Value != null) return true
                    context.disableDefaultConstraintViolation()
                    context.buildConstraintViolationWithTemplate("{com.example.customconstraintssample.constraints.CustomConstraints.type2.message}")
                        .addConstraintViolation()
                    false
                }
            }
        }
    }
}
