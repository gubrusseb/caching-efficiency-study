package ru.russeb.graduationwork.repository

import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.russeb.graduationwork.entity.Address


@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    fun findByUserId(userId: Long): List<Address>

    fun findByUserIdOrderByIsDefaultDescIdDesc(userId: Long): List<Address>

    fun findByIdAndUserId(id: Long, userId: Long): Address?

    @Modifying
    @Query("UPDATE Address a SET a.isDefault = false WHERE a.user.id = :userId")
    fun resetDefaultForUser(@Param("userId") userId: Long)
}