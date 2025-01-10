package bizi.bizi.model
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.NoArgsConstructor

@Entity
@Table(name = "companies")
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    var name: String,
    
    @Column(nullable = false)
    var description: String,
    
    @Column(nullable = false)
    var address: String,
    
    @Column(name = "logo_url")
    var logoUrl: String
){
    constructor() : this(0, "", "", "", "")
}