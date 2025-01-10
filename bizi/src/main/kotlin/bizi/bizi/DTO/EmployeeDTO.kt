package bizi.bizi.DTO
data class EmployeeDTO(
    val fullName: String,
    val dateOfBirth: String,
    val gender: String? = null,
    val email: String,
    val phone: String,
    val residentialAddress: String,
    val isAvailable: Boolean,
    val latitude: Double? = null,
    val longitude: Double? = null
)