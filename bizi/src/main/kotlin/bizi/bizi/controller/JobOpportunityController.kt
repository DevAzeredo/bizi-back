package bizi.bizi.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import bizi.bizi.service.JobOpportunityService
import bizi.bizi.DTO.JobOpportunityDTO
import bizi.bizi.DTO.JobOpportunityWithCompanyDTO
import bizi.bizi.model.User
import bizi.bizi.model.JobOpportunity
import bizi.bizi.websocket.CustomWebSocketHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("/api/jobs")
class JobOpportunityController(
    private val jobOpportunityService: JobOpportunityService,
    @Autowired private val webSocketService: CustomWebSocketHandler
) {
    private val logger = LoggerFactory.getLogger(CustomWebSocketHandler::class.java)

    @PostMapping
    fun createJob(
        @AuthenticationPrincipal user: User,
        @RequestBody job: JobOpportunityDTO
    ): JobOpportunity {
        val savedJobOpportunity = jobOpportunityService.saveJobOpportunity(job, user)
        val objectMapper = jacksonObjectMapper()
        val message = objectMapper.writeValueAsString(savedJobOpportunity)
        webSocketService.sendMessageToRandomClient(message)
        return savedJobOpportunity
    }

    @GetMapping("/{companyId}")
    fun listJobsByCompany(@PathVariable companyId: Long): JobOpportunity {
        return jobOpportunityService.findJobOpportunitiesByCompany(companyId)
    }
}