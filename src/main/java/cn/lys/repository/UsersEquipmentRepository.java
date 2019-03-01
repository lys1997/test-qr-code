package cn.lys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.lys.entity.UsersEquipment;

public interface UsersEquipmentRepository extends JpaRepository<UsersEquipment,String>{

	public UsersEquipment findByEquipmentId(String equipmentId);
	
}
