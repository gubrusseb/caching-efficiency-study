package ru.russeb.graduationwork.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.russeb.graduationwork.dto.AddressCreateRequestDto
import ru.russeb.graduationwork.dto.AddressDto
import ru.russeb.graduationwork.service.AddressService
import ru.russeb.graduationwork.service.UserService

@RestController
@RequestMapping("/addresses")
class AddressController(
    private val addressService: AddressService,
    private val userService: UserService
) {

    @GetMapping
    fun getUserAddresses(
        authentication: Authentication
    ): ResponseEntity<List<AddressDto>> {
        val addresses = addressService.getUserAddressesByEmail(authentication.name)
        return ResponseEntity.ok(addresses)
    }

    @PostMapping
    fun addAddress(
        authentication: Authentication,
        @Valid @RequestBody request: AddressCreateRequestDto
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val address = addressService.addAddress(userService.findByEmail(authentication.name).get().id!!, request)
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Адрес успешно добавлен",
                "address" to address
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }

    @GetMapping("/{addressId}")
    fun getAddress(
        @PathVariable addressId: Long
    ): ResponseEntity<AddressDto> {
        val address = addressService.getAddressById(addressId)
        return ResponseEntity.ok(AddressDto.from(address.get()))
    }

    @PutMapping("/{addressId}")
    fun updateAddress(
        authentication: Authentication,
        @PathVariable addressId: Long,
        @Valid @RequestBody request: AddressCreateRequestDto
    ): ResponseEntity<Map<String, Any>> {
        return try {
            val address = addressService.updateAddress(userService.findByEmail(authentication.name).get().id!!, addressId, request)
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Адрес обновлен",
                "address" to address
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }

    @DeleteMapping("/{addressId}")
    fun deleteAddress(
        authentication: Authentication,
        @PathVariable addressId: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            addressService.deleteAddress(userService.findByEmail(authentication.name).get().id!!, addressId)
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Адрес удален"
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }

    @PostMapping("/{addressId}/default")
    fun setDefaultAddress(
        authentication: Authentication,
        @PathVariable addressId: Long
    ): ResponseEntity<Map<String, Any>> {
        return try {
            addressService.setDefaultAddress(userService.findByEmail(authentication.name).get().id!!, addressId)
            ResponseEntity.ok(mapOf(
                "success" to true,
                "message" to "Основной адрес изменен"
            ))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(mapOf(
                "success" to false,
                "message" to e.message.toString()
            ))
        }
    }
}