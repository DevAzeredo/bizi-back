package bizi.bizi.model
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import lombok.NoArgsConstructor

@Entity
@Table(name = "employees")
@NoArgsConstructor
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(name = "full_name", nullable = false)
    var fullName: String,
    
    @Column(name = "date_of_birth", nullable = false)
    var dateOfBirth: String,
    
    @Column(nullable = false)
    var gender: String,
    
    @Column(nullable = false)
    var email: String,
    
    @Column(nullable = false)
    var phone: String,
    
    @Column(name = "residential_address", nullable = false)
    var residentialAddress: String,
    
    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean,
    
    @Column(nullable = false)
    var latitude: Double,
    
    @Column(nullable = false)
    var longitude: Double,
    
    @Column(nullable = false)
    var rating: Double
) {
    constructor() : this(0, "", "", "", "", "", "", false, 0.0, 0.0, 0.0)
}