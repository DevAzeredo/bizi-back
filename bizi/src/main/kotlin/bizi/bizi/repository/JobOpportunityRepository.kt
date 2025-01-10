package bizi.bizi.repository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import bizi.bizi.model.JobOpportunity
@Repository
interface JobOpportunityRepository : JpaRepository<JobOpportunity, Long> {
    fun findByCompanyId(companyId: Long): List<JobOpportunity>
}