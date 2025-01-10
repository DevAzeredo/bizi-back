package bizi.bizi.model
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import bizi.bizi.model.Company
import lombok.NoArgsConstructor
@Entity
@Table(name = "job_opportunities")
@NoArgsConstructor
data class JobOpportunity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    var title: String,
    
    @Column(nullable = false)
    var description: String,
    
    @Column(nullable = false)
    var category: String,
    
    @Column(nullable = false)
    var address: String,
    
    @Column(nullable = false)
    var latitude: Double,
    
    @Column(nullable = false)
    var longitude: Double,
    
    @Column(name = "start_date_time", nullable = false)
    var startDateTime: String,
    
    @Column(name = "duration_in_hours", nullable = false)
    var durationInHours: Int,
    
    @Column(name = "pay_rate", nullable = false)
    var payRate: Double,
    
    @Column(nullable = false)
    var status: String,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company? = null
){
    constructor() : this(0, "", "", "", "", 0.0, 0.0, "", 0, 0.0, "", null)
}