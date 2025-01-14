package bizi.bizi.DTO
data class JobOpportunityDTO(
    val title: String,
    val description: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val startDateTime: String,
    val durationInHours: Int,
    val payRate: Double,
    val status: String
)

data class JobOpportunityWithCompanyDTO(
    val id: Long = 0,
    val title: String,
    val description: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val startDateTime: String,
    val durationInHours: Int,
    val payRate: Double,
    val status: String,
    val company: CompanyDTO 
)