package cn.lys.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_equipment")
public class UsersEquipment {
	@Id
	private String id;
	
	private String userId;
	
	private String equipmentId;

	public UsersEquipment(String id, String userId, String equipmentId) {
		super();
		this.id = id;
		this.userId = userId;
		this.equipmentId = equipmentId;
	}

	public UsersEquipment() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	
}
