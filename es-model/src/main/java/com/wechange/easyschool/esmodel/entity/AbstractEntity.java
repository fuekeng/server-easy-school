package com.wechange.easyschool.esmodel.entity;



import java.io.Serializable;
import java.util.Date;

public abstract class AbstractEntity implements Serializable {
    private String idCreator;
    private Date createdAt;;
    private Date updatedAt;
    private String idUpdatedBy;
    private boolean deleted=false;
    private Date deletedAt;

	public String getIdCreator() {
		return idCreator;
	}
	/**
	 * @param idCreator the idCreator to set
	 */
	public void setIdCreator(String idCreator) {
		this.idCreator = idCreator;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the idUpdatedBy
	 */
	public String getIdUpdatedBy() {
		return idUpdatedBy;
	}
	/**
	 * @param idUpdatedBy the idUpdatedBy to set
	 */
	public void setIdUpdatedBy(String idUpdatedBy) {
		this.idUpdatedBy = idUpdatedBy;
	}
	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return the deletedAt
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}
	/**
	 * @param deletedAt the deletedAt to set
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
    
    
}
