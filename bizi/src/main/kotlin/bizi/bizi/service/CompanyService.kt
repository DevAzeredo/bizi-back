package bizi.bizi.service

import bizi.bizi.DTO.CompanyDTO
import bizi.bizi.model.Company
import bizi.bizi.model.User
import bizi.bizi.repository.CompanyRepository
import bizi.bizi.repository.UserRepository
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import jakarta.servlet.http.HttpServletResponse
import java.io.File

@Service
class CompanyService(
        private val companyRepository: CompanyRepository,
        private val userRepository: UserRepository,
        @Value("\${app.upload.dir:uploads}") private val uploadDir: String
) {
    fun saveCompany(company: CompanyDTO, user: User): Company {
        val companyEntity =
                user.company?.let { existingCompany ->
                    existingCompany.apply {
                        name = company.name
                        description = company.description
                        address = company.address
                        logoUrl = company.logoUrl ?: ""
                    }
                }
                        ?: Company(
                                name = company.name,
                                description = company.description,
                                address = company.address,
                                logoUrl = company.logoUrl ?: ""
                        )

        val savedCompany = companyRepository.save(companyEntity)

        if (user.company == null) {
            user.company = savedCompany
            userRepository.save(user)
        }

        return savedCompany
    }

    fun updateCompanyLogo(companyId: Long, file: MultipartFile): Company {
        val company =
                companyRepository.findById(companyId).orElseThrow {
                    RuntimeException("Company not found")
                }
        val fileName = "${companyId}.png"
        val path = Paths.get("$uploadDir/logos/$fileName")
        Files.createDirectories(path.parent)
        Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)

        company.logoUrl = fileName
        return companyRepository.save(company)
    }
    fun getLogo(fileName: String, response: HttpServletResponse) {
        val file = File("$uploadDir/logos/$fileName")
        if (file.exists()) {
            response.contentType = "image/png"  
            Files.copy(file.toPath(), response.outputStream)
            response.flushBuffer()
        } else {
            throw RuntimeException("Logo not found")
        }
    }

    fun findCompany(id: Long): Company {
        return companyRepository.findById(id).orElseThrow { RuntimeException("Company not found") }
    }
}
