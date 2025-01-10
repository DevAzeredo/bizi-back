package bizi.bizi.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import bizi.bizi.model.Employee

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
}
