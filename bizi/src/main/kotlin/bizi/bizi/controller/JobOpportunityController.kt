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
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("/api/jobs")
class JobOpportunityController(private val jobOpportunityService: JobOpportunityService) {
    @PostMapping
    fun createJob(
        @AuthenticationPrincipal user: User,
        @RequestBody job: JobOpportunityDTO
    ): JobOpportunity {
        return jobOpportunityService.saveJobOpportunity(job, user)
    }

    @GetMapping("/{companyId}")
    fun listJobsByCompany(@PathVariable companyId: Long): List<JobOpportunityWithCompanyDTO> {
        return jobOpportunityService.findJobOpportunitiesByCompany(companyId)
    }
}