package bizi.bizi.service

import bizi.bizi.DTO.JobOpportunityDTO
import bizi.bizi.DTO.JobOpportunityWithCompanyDTO
import bizi.bizi.model.JobOpportunity
import bizi.bizi.model.User
import bizi.bizi.repository.CompanyRepository
import bizi.bizi.repository.JobOpportunityRepository
import bizi.bizi.websocket.CustomWebSocketHandler
import org.springframework.stereotype.Service
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Service
class JobOpportunityService(
        private val jobOpportunityRepository: JobOpportunityRepository,
        private val companyRepository: CompanyRepository,
        private val webSocketService:CustomWebSocketHandler
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
                val savedJobOpportunity = jobOpportunityRepository.save(jobOpportunity)

                val objectMapper = jacksonObjectMapper()
                val message = objectMapper.writeValueAsString(savedJobOpportunity)

                val randomClient = webSocketService.getRandomClient()
                randomClient?.let {
                        webSocketService.sendMessageToClient(it, message)
                }

        return savedJobOpportunity
    }

    fun findJobOpportunitiesByCompany(companyId: Long): JobOpportunity {
                val job = jobOpportunityRepository.findByCompanyId(companyId).firstOrNull() ?: throw RuntimeException("No job opportunities found for this company")
                return job
    }
}
