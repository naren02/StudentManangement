/**
 * 
 */
package com.upskillcareer.StudentManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.upskillcareer.StudentManagement.model.Student;

/**
 * @author JAISHANKAR
 *
 */
@Repository
public interface StudentRepository extends MongoRepository<Student, Long>  {

}
