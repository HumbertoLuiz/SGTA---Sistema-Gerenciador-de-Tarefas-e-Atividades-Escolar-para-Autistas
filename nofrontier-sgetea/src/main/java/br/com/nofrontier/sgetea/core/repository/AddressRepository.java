package br.com.nofrontier.sgetea.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nofrontier.sgetea.core.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	//public List<Address> findByStudent(Student student);

//	@Query("SELECT a FROM Address a WHERE a.id = ?1 AND a.student.id = ?2")
//	public Address findByIdAndStudent(Long addressId, Long studentId);
//
//	@Query("DELETE FROM Address a WHERE a.id = ?1 AND a.student.id = ?2")
//	@Modifying
//	public void deleteByIdAndStudent(Long addressId, Long studentId);
//	
//	@Query("UPDATE Address a SET a.defaultForShipping = true WHERE a.id = ?1")
//	@Modifying
//	public void setDefaultAddress(Long id);
//
//	@Query("UPDATE Address a SET a.defaultForShipping = false "
//			+ "WHERE a.id != ?1 AND a.student.id = ?2")
//	@Modifying
//	public void setNonDefaultForOthers(Long defaultAddressId, Long studentId);
//	
//	@Query("SELECT a FROM Address a WHERE a.student.id = ?1 AND a.defaultForShipping = true")
//	public Address findDefaultByStudent(Long studentId);
}
