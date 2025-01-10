package bizi.bizi.service

import bizi.bizi.DTO.JobOpportunityDTO
import bizi.bizi.DTO.JobOpportunityWithCompanyDTO
import bizi.bizi.model.JobOpportunity
import bizi.bizi.model.User
import bizi.bizi.repository.CompanyRepository
import bizi.bizi.repository.JobOpportunityRepository
import org.springframework.stereotype.Service

@Service
class JobOpportunityService(
        private val jobOpportunityRepository: JobOpportunityRepository,
        private val companyRepository: CompanyRepository
) {
    fun saveJobOpportunity(job: JobOpportunityDTO, user: User): JobOpportunity {
        val company = user.company ?: throw RuntimeException("User has no company")

        val jobOpportunity =
                JobOpportunity(
                        title = job.title,
                        description = job.description,
                        category = job.category,
                        address = job.address,
                        latitude = job.latitude,
                        longitude = job.longitude,
                        startDateTime = job.startDateTime,
                        durationInHours = job.durationInHours,
                        payRate = job.payRate,
                        status = job.status,
                        company = company
                )

        return jobOpportunityRepository.save(jobOpportunity)
    }

    fun findJobOpportunitiesByCompany(companyId: Long): List<JobOpportunityWithCompanyDTO> {
        val company =
                companyRepository.findById(companyId).orElseThrow {
                    RuntimeException("Company not found")
                }

        return jobOpportunityRepository.findByCompanyId(companyId).map { job ->
            JobOpportunityWithCompanyDTO(
                    id = job.id,
                    title = job.title,
                    description = job.description,
                    category = job.category,
                    address = job.address,
                    latitude = job.latitude,
                    longitude = job.longitude,
                    startDateTime = job.startDateTime,
                    durationInHours = job.durationInHours,
                    payRate = job.payRate,
                    status = job.status,
                    companyName = company.name,
                    companyLogoUrl = company.logoUrl
            )
        }
    }
}
