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
import lombok.NoArgsConstructor

@NoArgsConstructor
@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false, unique = true)
    var login: String,
    
    @Column(nullable = false)
    var password: String,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyid")
    var company: Company? = null,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    var employee: Employee? = null
){
    constructor() : this(0, "", "")
}