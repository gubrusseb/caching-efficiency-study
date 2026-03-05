package ru.russeb.graduationwork.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.russeb.graduationwork.dto.AddressCreateRequestDto
import ru.russeb.graduationwork.dto.AddressDto
import ru.russeb.graduationwork.entity.Address
import ru.russeb.graduationwork.repository.AddressRepository
import ru.russeb.graduationwork.repository.UserRepository
import java.util.Optional

@Service
class AddressService(
    private val addressRepository: AddressRepository,
    private val userRepository: UserRepository
) {

    fun getUserAddressesByEmail(email: String): List<AddressDto> {
        return addressRepository.findByUserIdOrderByIsDefaultDescIdDesc(userRepository.findByEmail(email).get().id!!)
            .map { it.toDto() }
    }

    @Transactional
    fun addAddress(userId: Long, request: AddressCreateRequestDto): AddressDto {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("Пользователь не найден") }

        // Если это первый адрес или помечен как основной
        val existingAddresses = addressRepository.findByUserId(userId)
        val isDefault = if (existingAddresses.isEmpty()) true else request.isDefault

        // Если новый адрес основной, сбросить основной флаг у других
        if (isDefault) {
            addressRepository.resetDefaultForUser(userId)
        }

        val address = Address(
            user = user,
            line1 = request.line1,
            line2 = request.line2,
            city = request.city,
            postalCode = request.postalCode,
            country = request.country,
            isDefault = isDefault
        )

        val savedAddress = addressRepository.save(address)
        return savedAddress.toDto()
    }

    @Transactional
    fun updateAddress(userId: Long, addressId: Long, request: AddressCreateRequestDto): AddressDto {
        val address = addressRepository.findByIdAndUserId(addressId, userId)
            ?: throw IllegalArgumentException("Адрес не найден")

        // Если обновляемый адрес становится основным
        if (request.isDefault && !address.isDefault) {
            addressRepository.resetDefaultForUser(userId)
        }

        address.line1 = request.line1
        address.line2 = request.line2
        address.city = request.city
        address.postalCode = request.postalCode
        address.country = request.country
        address.isDefault = request.isDefault

        val updatedAddress = addressRepository.save(address)
        return updatedAddress.toDto()
    }

    @Transactional
    fun deleteAddress(userId: Long, addressId: Long) {
        val address = addressRepository.findByIdAndUserId(addressId, userId)
            ?: throw IllegalArgumentException("Адрес не найден")

        addressRepository.delete(address)

        // Если удалили основной адрес, сделать первый оставшийся основным
        if (address.isDefault) {
            val remainingAddresses = addressRepository.findByUserId(userId)
            if (remainingAddresses.isNotEmpty()) {
                val newDefault = remainingAddresses[0]
                newDefault.isDefault = true
                addressRepository.save(newDefault)
            }
        }
    }

    @Transactional
    fun setDefaultAddress(userId: Long, addressId: Long) {
        val address = addressRepository.findByIdAndUserId(addressId, userId)
            ?: throw IllegalArgumentException("Адрес не найден")

        addressRepository.resetDefaultForUser(userId)
        address.isDefault = true
        addressRepository.save(address)
    }

    private fun Address.toDto(): AddressDto {
        return AddressDto(
            id = this.id,
            line1 = this.line1,
            line2 = this.line2,
            city = this.city,
            postalCode = this.postalCode,
            country = this.country,
            isDefault = this.isDefault
        )
    }

    fun getAddressById(id: Long): Optional<Address> {
        return addressRepository.findById(id)
    }
}