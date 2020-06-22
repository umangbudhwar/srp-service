package com.srp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.entity.Student;

@Repository
public interface StudentRepository extends SrpRepository<Student, String> {

	public int countByFirstName(String firstName);

	public List<Student> findByStreamId(Long streamId);

	@Query(value = "Select * from student where LOWER(CONCAT(user_name,' ',first_name,' ',last_name,' ',email_id)) LIKE TRIM (REGEXP_REPLACE(LOWER(CONCAT('%', :searchValue,'%')),'\\\\s+','','g')) ORDER BY user_name,first_name,last_name,email_id",nativeQuery=true)
	public List<Student> findAllStudentsByUserNameOrFirstNameOrLastNameOrEmail(String searchValue);
	
	@Query(value = "select college_year, count(user_name) as count_user_name from student group by (college_year) order by college_year;",nativeQuery=true)
        public Object[][] countByYearAndUserName();

	public List<Student> findByCollegeYear(Long year);

	@Modifying(clearAutomatically = true)
	@Query(value = "update student set group_division =:groupDivision where user_name =:userName",nativeQuery=true)
	public int updateBatchGroupCode(@Param("userName") String userName, @Param("groupDivision") String groupDivision);

        public boolean existsStudentByUserNameIgnoreCase(String userName);

}
