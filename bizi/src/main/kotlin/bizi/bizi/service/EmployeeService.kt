package bizi.bizi.service

import bizi.bizi.DTO.EmployeeDTO
import bizi.bizi.model.Employee
import bizi.bizi.model.User
import bizi.bizi.repository.EmployeeRepository
import bizi.bizi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
        private val employeeRepository: EmployeeRepository,
        private val userRepository: UserRepository
) {
    fun saveEmployee(employee: EmployeeDTO, user: User): Employee {
        val employeeEntity =
                user.employee?.let { existingEmployee ->
                    existingEmployee.apply {
                        fullName = employee.fullName
                        gender = employee.gender ?: "undefined"
                        email = employee.email
                        phone = employee.phone
                        isAvailable = employee.isAvailable
                        residentialAddress = employee.residentialAddress
                        latitude = employee.latitude ?: 0.0
                        longitude = employee.longitude ?: 0.0
                        dateOfBirth = employee.dateOfBirth
                    }
                }
                        ?: Employee(
                                fullName = employee.fullName,
                                gender = employee.gender ?: "undefined",
                                email = employee.email,
                                phone = employee.phone,
                                isAvailable = employee.isAvailable,
                                residentialAddress = employee.residentialAddress,
                                latitude = employee.latitude ?: 0.0,
                                longitude = employee.longitude ?: 0.0,
                                dateOfBirth = employee.dateOfBirth,
                                rating = 0.0
                        )

        val savedEmployee = employeeRepository.save(employeeEntity)

        if (user.employee == null) {
            user.employee = savedEmployee
            userRepository.save(user)
        }

        return savedEmployee
    }

    fun findEmployee(id: Long): Employee {
        return employeeRepository.findById(id).orElseThrow {
            RuntimeException("Employee not found")
        }
    }
}
